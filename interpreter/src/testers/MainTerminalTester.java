package testers;

import java.util.ArrayList;

import lib_TEA.*;

public class MainTerminalTester {
	
	public static void main(String[] args) {
		MainTerminal mainTerm = MainTerminal.get();
		mainTerm.cd(new Path("../"));
		ArrayList<String> responce1 = mainTerm.execute("rm --help");
		for(String s:responce1){
			System.out.println(s);
		}
	}
	
}
