import lib_TEA.*;

public class FileWriter {

	public static void main(String[] args) {

		Path path = new Path("exampleFile1.txt");
		
		path.create(true);
		
		String text = "\"Whatever you want to write to the text file!!\"";
		
		String command = "echo " + text + " > " + path;
		
		MainTerminal.get().execute(command);
	}

}
