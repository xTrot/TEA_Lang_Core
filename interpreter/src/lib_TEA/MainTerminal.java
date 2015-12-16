package lib_TEA;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainTerminal {
	private static final String BASH = "/bin/bash";
	private static MainTerminal singleton;
	private ProcessBuilder processBuilder;
	private Process currentProcess;
	private Path currentPath;
	
	private MainTerminal(){
		
	}
	
	/**
	 * Creates a singleton to a Main Terminal
	 *  
	 * @return		singleton to a Main Terminal
	 */
	public static MainTerminal get(){
		
		if(singleton==null){
			singleton = new MainTerminal();
		}
		
		return singleton;
		
	}

	/**
	 * Starts the Main Terminal
	 * 
	 * @param command	command to execute when starting the Main Terminal.
	 */
	public void start(String command){
		try{
			this.processBuilder = new ProcessBuilder(BASH,"-c",
					command);
			this.processBuilder.directory(new File(currentPath.toString()));
			this.currentProcess = this.processBuilder.start();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Wait for a response from the Main Terminal.
	 */
	public void waitForResponse(){
		if(this.currentProcess!=null)
			try {
				this.currentProcess.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Change the working directory of Main Terminal to dir
	 * 
	 * @param dir	working directory. Must be an absolute path.
	 */
	public void cd(Path dir) {
		this.currentPath = new Path(dir.toString());
	}

	/**
	 * Execute command on the Main Terminal.
	 * 
	 * @param command	command to execute.
	 * @return			response from Main Terminal after execution.
	 */
	public String execute(String command){
		this.start(command);
		this.waitForResponse();
		return this.response();
		
	}

	/**
	 * 	Gather and returns the response from Main Terminal.
	 * 
	 * @return response from Main Terminal.
	 */
	public String response(){
		InputStream inStream = this.currentProcess.getInputStream();
		return nextLine(inStream);
	}

	/**
	 * Read next line from input stream.
	 * 
	 * @param inStream
	 * @return
	 */
	private String nextLine(InputStream inStream){
		int i;
		String line = "";
		try{
			while((i=inStream.read())!=-1){
				line+=(char)i;
			}
		}catch (IOException e){
			e.printStackTrace();
		}
		return line==""?null:line;
	}
	
}
