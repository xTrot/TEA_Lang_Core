package lib_TEA;

//Immutable Path Class for TEA.
/**
 * @author Enrique
 *
 */
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
	
	/**
	 * @author 
	 * @return Returns true if it is an absolute path.
	 * This method verifies if it is an absolute path.
	 */
	public boolean isAbsolute() {
		return false;
	}
	
	public boolean exists() {
		return false;
	}
	
	public String get() {
		return null;
	}
	
	public String getFileName() {
		return null;
	}
	
	public boolean create() {
		return false;
	}
	
	// If parameter is an absolute Path, there must be an error
	public Path concat(Path relativePath) {
		return null;
	}
	
	public boolean remove() {
		return false;
	}
	
	public Path select(int index)
	{
		return null;
	}
	
	public Path select(int start, int end)
	{
		return null;
	}

	public String getExtension() {
		// TODO Auto-generated method stub
		return null;
	}
}
