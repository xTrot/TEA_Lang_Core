package interpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CheckParserSuccess {
	private static File file;

	public static boolean check(String filePath) {
		// TODO Traverse the file for the "No errors." Line.
		file = new File(filePath);
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        if(line.contains("No errors."))
		    		return true;
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
