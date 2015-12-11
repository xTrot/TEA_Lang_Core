package lib_TEA;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainTerminal {
	private static final String BASH = "/bin/bash";
	private static MainTerminal singleton;
	private ProcessBuilder processBuilder;
	private Process currentProcess;
	private Path currentPath;
	
	private MainTerminal(){
		
	}
	
	public static MainTerminal get(){
		
		if(singleton==null){
			singleton = new MainTerminal();
		}
		
		return singleton;
		
	}

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
	
	public void waitForResponse(){
		if(this.currentProcess!=null)
			try {
				this.currentProcess.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	public void cd(Path dir) {
		this.currentPath = new Path(dir.toString());
	}

	public ArrayList<String> execute(String command){
		this.start(command);
		this.waitForResponse();
		return this.response();
		
	}

	public ArrayList<String> response(){
		InputStream inStream = this.currentProcess.getInputStream();
		String line;
		ArrayList<String> results = new ArrayList<String>() ;
		while((line = nextLine(inStream))!=null){
			results.add(line);
		}
		return results;
	}

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
