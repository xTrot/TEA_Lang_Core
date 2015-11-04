package interpreter;

public abstract class Keyword {
	protected String value;
	
	public Keyword(String keyword){
		this.value = keyword;
	}
	
	public abstract String replace(String containedHere);

}
