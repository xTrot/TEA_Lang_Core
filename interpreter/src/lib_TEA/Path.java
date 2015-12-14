package lib_TEA;

import java.io.File;
import java.util.ArrayList;

//Immutable Path Class for TEA.
/**
 * <h1>Path</h1>
 * The <b>Path</b> class includes all the methods to manage paths
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
	
	/**
	 * Creates Path object.
	 * 
	 * @param path	Path 
	 */
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
		
		File temp = new File(this.path);
		return temp.exists();
	}
	
	/**
	 * Returns the element (directory or file) of the Path at index. For example,
	 * if:
	 * 
	 * Path path1 = dirA/dirB/dirC/file.name
	 * 
	 * Calling path1.get(2) returns dirB
	 * 
	 * index can be negative. In such case get function start counting from the
	 * right.
	 * 
	 * @param index	index of the element in Path to get. If index is out of range
	 * 				(>=|N|, where N is the number of elements in Path), the function
	 * 				returns null. index can be negative; in this case the function
	 * 				starts counting from the right.
	 * @return		Element in Path at index. null if not found.
	 */
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
	 * Creates the appropriate directory specified by Path. Path must be absolute.
	 * <p>
	 * This function creates all the directories that do not already exist in the
	 * specified path. If the directory already exist at the specified location,
	 * the function returns Path without overwriting the existing directory.
	 * 
	 * @return		TRUE if directory is created or already exist. FALSE otherwise
	 */
	public boolean create() {
		
		
		return false;
	}
	
	/**
	 * Creates a new path by appending a name or a relative path to an existing
	 * (base) path.
	 * 
	 * @param	relativePath	name or relative path is the new path component 
	 * 							to be appended to the base path. If relativePath
	 * 							is empty this function returns the base path.
	 * @return					Resulting path.
	 */
	// If parameter is an absolute Path, there must be an error
	// 
	public Path concat(Path relativePath) {
		
		Path result;
		
		if(relativePath.isAbsolute())
		{
			result = null;
		}
		else
		{
			String resultPath = null;
			
			if(!this.path.endsWith("/"))
				this.path += "/";
			
			resultPath = this.path + relativePath.toString();
			
			result = new Path(resultPath);
		}
		
		return result;
	}
	
	/**
	 * Removes the last element from Path.
	 * 
	 * @return		TRUE if succesful, FALSE otherwise.
	 */
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
	
	/**
	 * Returns the number of elements in Path. For example:
	 * 
	 * dirA/dirB/dirC/
	 * 
	 * has 3 elements.
	 * 
	 * @return		Number of elements in Path.
	 */
	private int length() {
		return this.lenght;
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
	
	/**
	 * Return TRUE if Path is an existing directory. Path must be absolute.
	 * 
	 * @return		TRUE if Path is an existing directory, FALSE otherwise.
	 */
	public boolean isDirectory()
	{
		File temp = new File(this.path);
		return temp.isDirectory();
	}

	/**
	 * Returns the extension of the last element in Path, if exist.
	 * 
	 * @return		extension of the last element in Path, if exist; null otherwise
	 */
	public String getExtension() {

		return null;
	}
}
