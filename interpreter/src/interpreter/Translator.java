package interpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Formatter;

import lib_TEA.MainTerminal;
import lib_TEA.Path;

public class Translator {
	
	private static String[] arguments;
	private static Formatter newFile;
	private static Path java;
	
	private static Keyword[] kwArray = {
	    new Keyword("mainTerm","MainTerminal.get()"),
	    new Keyword("filter","Functions.filter"),
	    new Keyword("print", "System.out.println")
	    };

	
	private static final String ROOT= "";
	private static final Path LEXER_OUT= new Path(ROOT + "TEA_Lexer/lexer-output.txt");
	private static final String TEA_IMPORT = "import lib_TEA.*;";
	private static final Path MFTemplate = new Path(ROOT + "TEA_Lexer/MFTemplate");
	private static final Path MAKEFILE = new Path(ROOT + "TEA_Lexer/Makefile");
	
	public static void main(String[] args) {
		arguments = args;
		
		Path tea = getTeaFile(args);
		
		if(tea==null)
			return;
		
		java = translateToJava(tea);
		
		MainTerminal mainTerm = MainTerminal.get();
		
		mainTerm.cd(new Path(ROOT + "TEA_Lang_Core/TEA_Lexer/"));
		
		fixMakeFile(tea);
		
		Path fileRoot = new Path(ROOT+java.getFileName().split("\\.")[0]+"/");
		mainTerm.execute("mkdir ../"+fileRoot);
		String response = mainTerm.execute("make");
		
		System.out.println(response);
		if(makeErrorLookup(response))
			return;
		
		if(!CheckParserSuccess.check(LEXER_OUT)){
			System.out.println("lexer-output.txt is missing or the syntax is incorrect.");
			return;
		}
		
		Path javaClass = new Path(ROOT+"TEA_Lexer/"+java.getFileName().split("\\.")[0]+".class");
		copyFile(java,fileRoot);
		copyFile(javaClass,fileRoot);
		mainTerm.execute("rm --force "+java.getFileName());
		mainTerm.execute("rm --force "+javaClass.getFileName());
		mainTerm.execute("rm --force "+MAKEFILE.getFileName());
		mainTerm.execute("rm --force "+LEXER_OUT.getFileName());
		
	}
	
	private static boolean makeErrorLookup(String response) {
		if(response.contains("recipe for target 'compile' failed")){
			System.out.println("Syntax errors within target TEA File.");
			String command = "gnome-terminal -x sh -c "
					+ "\"javac -cp .:lib_TEA.jar:java-cup-11b.jar "
					+ "JavaParser.java "
					+ java.getFileName()
					+ " ; read -p 'Press Enter to continue...' continue\" & disown";
			MainTerminal.get().execute(command);
			System.out.println(command);
			return true;
		}
		if(response.contains("recipe for target 'run' failed")){
			System.out.println("Run of the compiled file failed.\n"
					+ "Commonly happens due to wrong arguments.");
			return true;
		}
		return false;
	}

	private static void copyFile(Path file, Path directory) {
		File file1 = new File(file.toString());
		
		try{
			newFile = new Formatter(directory+"/"+file.getFileName());
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		try(BufferedReader br = new BufferedReader(new FileReader(file1))) {
		    for(String line; (line = br.readLine()) != null; ) {
		       
		    	printToFile(line);
		    }
		    
		    newFile.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void fixMakeFile(Path tea) {
		File file = new File(MFTemplate.toString());
		String fileName = tea.getFileName().split("\\.")[0];
		
		try{
			newFile = new Formatter(MAKEFILE.toString());
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		String args = "";
		if(arguments.length>1)
		for(int i=1;i<arguments.length;i++)
			args += arguments[i]+" ";
		args += "> ../$(FILE_COMP)/$(FILE_COMP)_output.txt";
		
		printToFile("FILE       = "+ fileName + ".java");
		printToFile("FILE_COMP  = "+ fileName );
		printToFile("ARGS       = "+ args);
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    for(String line; (line = br.readLine()) != null; ) {
		       
		    	printToFile(line);
		    }
		    
		    newFile.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Path translateToJava(Path tea) {
		String fileName = tea.getFileName().split("\\.")[0];
		
		File file = new File(tea.toString());
		
		try{
			newFile = new Formatter(ROOT+"TEA_Lexer/"+fileName+".java");
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		printToFile(TEA_IMPORT);
		
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
		return new Path(ROOT + "TEA_Lexer/"+fileName+".java");
	}

	private static Path getTeaFile(String[] args) {
		Path teaFile=null;
		if(args.length!=0){
			if((teaFile=new Path(args[0])).getExtension()
					.toLowerCase().equals("tea"))
				System.out.println("File: "+teaFile);
		}else
			System.out.println("File not found or incompatible.");
		return teaFile;
	}

	private static void printToFile(String resultLine) {
		newFile.format("%s", resultLine + "\n");
	}

}
