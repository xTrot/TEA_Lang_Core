import java.util.ArrayList;

//Author Enddy Gonzalez Figueroa 
// 
// This program counts how many files for each extension, 
//  are in a directory, for a linux environment. If the path  
// is incorrect the path class will detect it and throw an  
// exception. 
// 
// This is the first sample code for the Tea language. 
public class test {

	void main(String[] args) {

		// Native Path class object instance.
		Path dir = new Path(args[0]);
		
		// Native terminal start. Starts a new terminal communication.
		mainTerm.start();

		// Simplified terminal command cd(Change Directory).
		mainTerm.cd(dir);

		// Simple execution with a string argument. The execute
		// waits for the terminal to finish the execution.
		mainTerm.execute("ls");

		// The response can be accessed.
		String response = mainTerm.response();

		// The filter method is able to use RegEx to find all
		// the matching Strings
		// This will match all files with extensions only.
		String[] fileNames = filter(response, "(\\w.)+\\w");

		// Counting process.
		if (fileNames != null) {

			Path[] files = new Path[fileNames.length];

			for (int i = 0; i < fileNames.length; i++)
				files[i] = new Path(fileNames[i]);

			ArrayList<String> extensions = new ArrayList<String>();

			ArrayList<Integer> extCount = new ArrayList<Integer>();

			for (Path file : files) {

				String ext = file.getExtension();

				ext = ext.lowerCase();

				if (extensions.contains(ext)){
					int i = extentions.indexOf(ext);
					extCount.set(i, extCount.get(i) + 1);
				}
				else {
					extensions.add(ext);
					extCount.add(1);
				}

			}

			for (String ext : extensions) {

				print(ext + ":" + extCount.get(extensions.indexOf(ext)));

			}

		}

		print("No matching files found.");

	}
}
