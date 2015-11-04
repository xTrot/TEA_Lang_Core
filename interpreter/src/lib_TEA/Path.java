package lib_TEA;

//Immutable Path Class for TEA.
public class Path {
	private String path;
	
	public Path(String path) {
		//TODO Check if Path is valid!!
		this.path = path;
	}
	
	@Override
	public String toString() {
		return path;
		
	}

	public String getExtension() {
		// TODO Auto-generated method stub
		return null;
	}
}
