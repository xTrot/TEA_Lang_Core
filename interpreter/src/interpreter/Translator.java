package interpreter;

public class Translator {
	// TODO Initialize Keyword Array
	private static Keyword[] kwArray = {
	    new Keyword("mainTerm") {
		
		@Override
		public String replace(String containedHere) {
			return containedHere.replace(value, "MainTerminal.get()");
		}
	}};

	public static void main(String[] args) {
		// TODO Add the import for lib_TEA.*
		
		// TODO Find the start of main function.
		
		// TODO Line by line Find all keywords 
		// and replace them with the lib_TEA 
		// equivalent.

	}
	
	private static String getLine(){
		return null;
	}

}
