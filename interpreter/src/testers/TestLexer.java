package testers;

import lib_TEA.*;
import java.util.ArrayList;

//Author Enddy Gonzalez Figueroa 
// 
// This program counts how many files for each extension, 
//  are in a directory, for a linux environment. If the path  
// is incorrect the path class will detect it and throw an  
// exception. 
// 
// This is the first sample code for the Tea language. 
public class TestLexer {

	public static void main(String[] args) {

		// Native Path class object instance.
		Path dir = new Path(args[0]);
		
		// Simplified terminal command cd(Change Directory).
		MainTerminal.get().cd(dir);

		// Simple execution with a string argument. The execute
		// waits for the terminal to finish the execution.
		// The response can be accessed.
		String response = MainTerminal.get().execute("ls");

		// The Functions.filter method is able to use RegEx to find all
		// the matching Strings
		// This will match all files with extensions only.
		String[] fileNames = Functions.filter(response, "((\\w)+\\.)+(\\w)+");

		// Counting process.
		if (fileNames != null) {

			Path[] files = new Path[fileNames.length];

			for (int i = 0; i < fileNames.length; i++)
				files[i] = new Path(fileNames[i]);

			ArrayList<String> extensions = new ArrayList<String>();

			ArrayList<Integer> extCount = new ArrayList<Integer>();

			for (Path file : files) {

				String ext = file.getExtension();

				ext = ext.toLowerCase();

				if (extensions.contains(ext)){
					int i = extensions.indexOf(ext);
					extCount.set(i, extCount.get(i) + 1);
				}
				else {
					extensions.add(ext);
					extCount.add(1);
				}

			}

			for (String ext : extensions) {

				System.out.println(ext + ":" + extCount.get(extensions.indexOf(ext)));

			}

		}else{

		System.out.println("No matching files found.");
		
		}

	}
}
