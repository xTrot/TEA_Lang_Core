package lib_TEA;

import java.util.ArrayList;

//Immutable Path Class for TEA.
/**
 * <h1>PATH</h1>
 * The <b>PATH</b> class includes all the methods to manage paths
 * within the TEA language. 
 * 
 * TEA Development Team
 * @author Enddy Y. Gonzales Figueroa
 * @author Jonathan A. Rom�n Morell
 * @author Enrique J. Vargas Figueroa
 *
 */
public class Path {
	
	private String path;
	private ArrayList<String> elements;
	private boolean isAbsolute;
	private int lenght;
	
	
	public Path(String path) {
		//TODO Check if Path is valid!!
		
		this.path = path;
		this.elements = new ArrayList<String>();
		this.isAbsolute = (this.path.charAt(0) == '/');
		
		String[] directories = this.path.split("/");
		
		for(int i = 0; i < directories.length; i++)
		{
			if(!(directories[i] == ""))
				this.elements.add(directories[i]);
		}
		
		this.lenght = this.elements.size();

	}
	
	@Override
	public String toString() {
		return path;
	}
	
	/**
	 * Returns TRUE if Path is an absolute path, FALSE otherwise.
	 * 
	 * @return		TRUE if Path is an absolute path.
	 */
	public boolean isAbsolute() {
		return isAbsolute;
	}
	
	/**
	 * Returns TRUE if Path is an absolute path that exist on disk, FALSE otherwise.
	 * <p>
	 * <b>Note:</b> If Path is an empty path, this method returns TRUE because:
	 * <ul>
	 * <li>(Windows) the empty path refers to the list of drives mapped to the computer.
	 * <li>(Mac OS X) the empty path refers to the mounted volumes.
	 * <li>(Linux) the empty path refers to the root directory.
	 * </ul>
	 * 
	 * @return		TRUE if Path is an absolute path that exist on disk.
	 */
	public boolean exists() {
		return false;
	}
	
	public String get(int index) {
		
		String result = null;
		
		if(Math.abs(index) > 0 && Math.abs(index) < lenght)
		{
			if(index < 0)
				result = this.elements.get(this.length() + index);
			else
				result = this.elements.get(index);
		}
		return result;
	}
	
	/**
	 * 
	 * 
	 * @return		
	 */
	public String getFileName() {
		
		return null;
	}
	
	/**
	 * Creates the appropriate file or folder specified by Path.
	 * <p>
	 * This function creates all folders that do not already exist in the
	 * specified path. If a file or folder already exists at the specified location, the function returns an error instead of overwriting the existing file or folder.
	 */
	public boolean create() {
		
		return false;
	}
	
	/**
	 * Creates a new path by appending a name or a relative path) to an existing
	 * (base) path.
	 * 
	 * @param	relativePath	name or relative path is the new path component 
	 * 							to be appended to the base path. If relativePath
	 * 							is empty or an invalid path, this function returns
	 * 							NULL. If the base path is an empty path then
	 * 							relativePath must be an absolute path.
	 * @return					Resulting path.
	 */
	// If parameter is an absolute Path, there must be an error
	public Path concat(Path relativePath) {
		
		return null;
	}
	
	public boolean remove() {
		return false;
	}
	
	public Path select(int index)
	{
		if(index < 0)
		{
			this.select(this.length() + index, this.length());
		}
		return this.select(0, index);
	}
	
	private int length() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Path select(int start, int end)
	{
		String newPath = "";
		
		
		if(this.isAbsolute() && start == 0){
			newPath = "/";
		}
		
		for(int i = start; i <= end; i++)
		{
			newPath += this.elements.get(i) + "/";
		}
		
		return new Path(newPath);
	}
	
	public boolean isFile()
	{
		return false;
	}

	public String getExtension() {
		// TODO Auto-generated method stub
		return null;
	}
}
