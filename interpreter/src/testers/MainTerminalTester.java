package testers;

import java.io.File;

import lib_TEA.MainTerminal;
import lib_TEA.Path;

public class MainTerminalTester {
	
	public static void main(String[] args) {
		MainTerminal mainTerm = MainTerminal.get();
		mainTerm.cd(new Path("../"));
		String responce1 = mainTerm.execute("rm --help");
		System.out.println(responce1);
		
		Path p = new Path("/home/jonathan/Desktop/folder/");
		
		p.create(false);
		
	}
	
}
