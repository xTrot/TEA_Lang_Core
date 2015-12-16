package interpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import lib_TEA.Path;

public class CheckParserSuccess {
	private static File file;

	public static boolean check(Path filePath) {
		// TODO Traverse the file for the "No errors." Line.
		file = new File(filePath.toString());
		if(filePath.exists())
			try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			    for(String line; (line = br.readLine()) != null; ) {
			        if(line.contains("----EOF--")){
			        	return true;
			        }
			    }
			} catch (Exception e) {
				e.printStackTrace();
			}
		return false;
	}

}
