package interpreter;

public class Keyword {
	private String key;
	private String replaceTo;
	
	public Keyword(String keyword,String replaceTo){
		this.key = keyword;
		this.replaceTo = replaceTo;
		
	}
	
	@Override
	public String toString(){
		return key;
	}
	
	public String replace(String containedHere){
		return containedHere.replace(key, replaceTo);
	};

}
