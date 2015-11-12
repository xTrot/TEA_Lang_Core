package interpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Formatter;

public class Translator {
	
	private static Formatter newFile;
	
	// TODO Initialize Keyword Array
	private static Keyword[] kwArray = {
	    new Keyword("mainTerm","MainTerminal.get()"),
	    new Keyword("filter","Functions.filter"),
	    new Keyword("print", "System.out.println")
	    };
	
	private static final String LEXER_OUT= "../TEA_Lexer/lexer-output.txt";
	private static final String TEST_FILE= "../TEA_Lexer/TestLexer.java";
	private static final String TEA_IMPORT = "import lib_TEA.*";
	
	public static void main(String[] args) {
		if(!CheckParserSuccess.check(LEXER_OUT)){
			System.out.println("lexer-output.txt is missing or the syntax is incorrect.");
			return;
		}
		
		File file = new File(TEST_FILE);
		
		try{
			
			newFile = new Formatter("../TEA_Lexer/IntermidiateCode.java");
		
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		// TODO Add the import for lib_TEA.*
		printToFile(TEA_IMPORT);
		
		// TODO Line by line Find all keywords 
		// and replace them with the lib_TEA 
		// equivalent.
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        String resultLine=line;
		    	
		        for(Keyword k:kwArray){
		        	if(resultLine.contains(k.toString()))
		        		resultLine=k.replace(line);
		        }
		        
		    	printToFile(resultLine);
		    }
		    
		    newFile.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void printToFile(String resultLine) {
		// TODO Actually print to a file.
		System.out.println(resultLine);
		newFile.format("%s", resultLine + "\n");
		
	}

}
