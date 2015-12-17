import lib_TEA.*;
import java.util.ArrayList;

public class FileEraser {

	public static void main(String[] args) {

		
		String response = MainTerminal.get().execute("ls");
		
		String[] files = Functions.filter(response, "((\\w)+\\.)+(\\w)+");
		
		ArrayList<Path> paths = new ArrayList<Path>();
		
		for(String s: files)
		{
			paths.add(new Path(s));
		}
		
		System.out.println("Removing:");
		for(Path p: paths)
		{
			if (p.getExtension().equals("txt")){
				System.out.println(p.toString());
				p.remove();
				}
		}
	}

}
