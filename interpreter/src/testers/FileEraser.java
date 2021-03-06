package testers;

import java.util.ArrayList;

import lib_TEA.Functions;
import lib_TEA.MainTerminal;
import lib_TEA.Path;



public class FileEraser {

	public static void main(String[] args) {

		
		String response = MainTerminal.get().execute("ls");
		
		String[] files = Functions.filter(response, "((\\w)+\\.)+(\\w)+");
		
		ArrayList<Path> paths = new ArrayList<Path>();
		
		for(String s: files)
		{
			paths.add(new Path(s));
		}
		
		for(Path p: paths)
		{
			if (p.getExtension().equals("txt"))
				p.remove();
		}
	}

}
