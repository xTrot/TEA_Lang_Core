package testers;

import lib_TEA.MainTerminal;
import lib_TEA.Path;

public class FileWriter {

	public static void main(String[] args) {

		Path path = new Path("/home/jonathan/Desktop/folder/file1.txt");
		
		path.create(true);
		
		MainTerminal.get().cd(new Path("../"));
		
		String text = "\"Whatever you want to write to the text file!!\"";
		
		String command = "echo " + text + " > " +path;
		
		MainTerminal.get().execute(command);
	}

}
