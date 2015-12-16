package testers;

import interpreter.CheckParserSuccess;
import lib_TEA.Path;

public class CheckParserSuccessTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(CheckParserSuccess.check(new Path("../TEA_Lexer/lexer-output.txt")));
	}

}
