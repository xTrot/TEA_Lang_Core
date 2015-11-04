package lib_TEA;

public class MainTerminal {
	private static MainTerminal singleton;
	
	private MainTerminal(){
		
	}
	
	public static MainTerminal get(){
		
		if(singleton==null){
			singleton = new MainTerminal();
		}
		
		return singleton;
		
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}

	public void cd(Path dir) {
		// TODO Auto-generated method stub
		
	}

	public void execute(String string) {
		// TODO Auto-generated method stub
		
	}

	public String response() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
