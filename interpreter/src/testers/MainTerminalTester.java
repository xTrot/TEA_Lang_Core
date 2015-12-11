package testers;

import java.util.ArrayList;

import lib_TEA.*;

public class MainTerminalTester {
	
	public static void main(String[] args) {
		MainTerminal mainTerm = MainTerminal.get();
		mainTerm.cd(new Path("../"));
		ArrayList<String> responce = mainTerm.execute("ls");
		for(String s:responce){
			System.out.println(s);
		}
	}
	
}
