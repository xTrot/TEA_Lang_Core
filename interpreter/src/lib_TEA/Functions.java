package lib_TEA;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Functions {

	public static String[] filter(String response, String regEx) {
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = null;
		String[] lines = response.split("\n");
		ArrayList<String> result = new ArrayList<>(); 
		for(String line:lines){
			matcher = pattern.matcher(line);
			if(matcher.lookingAt()){
				result.add(line);
			}
		}
		return result.toArray(new String[result.size()]);
	}

}
