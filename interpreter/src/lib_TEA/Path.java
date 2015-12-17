package lib_TEA;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//Immutable Path Class for TEA.
/**
 * <h1>Path</h1>
 * The <b>Path</b> class includes all the methods to manage paths
 * within the TEA language. 
 * 
 * TEA Development Team
 * @author Enddy Y. Gonzales Figueroa
 * @author Jonathan A. Roman Morell
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
		this.path = path;
		this.elements = new ArrayList<String>();
		this.isAbsolute = (this.path.charAt(0) == '/');
		
		String[] directories = this.path.split("/");
		
		for(String s: directories)
		{
			if(!s.equals(""))
				this.elements.add(s);
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
	 * @return		TRUE if Path is an absolute path, FALSE otherwise
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
	 * @return		TRUE if Path is an absolute path that exist on disk, FALSE otherwise
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
		
		if(Math.abs(index) >= 0 && Math.abs(index) < lenght)
		{
			if(index < 0)
				result = this.elements.get(this.length() + index);
			else
				result = this.elements.get(index);
		}
		return result;
	}
	
	/**
	 * Returns the name of a File. Path must be absolute, the last element must be
	 * a file name and the file must exist, otherwise returns null.
	 * 
	 * @return		Name of File including extension or null if file does not exist.
	 */
	public String getFileName() {
		
		String result = null;
		
		if(this.exists() && !this.isDirectory())
			result = this.get(this.lenght - 1);
		
		return result;
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
	public boolean create(boolean isFile) {
		
		boolean result = false;
		
		if(!isFile)
		{
			File temp = new File(this.path);
			temp.mkdirs();
			result = true;
		}

		else
		{
			File temp = new File(this.path);
			String folderContainingFile = "/";
			
			for(int i = 0; i <= this.lenght - 2; i++)
			{
				folderContainingFile += this.get(i) + "/";
			}
			
			new File(folderContainingFile).mkdirs();
			
			try {
				temp.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			result = true;
		}
		
		return result;
	}
	
	/**
	 * Creates a new path by appending a name or a relative path to an existing
	 * (base) path.
	 * 
	 * @param	relativePath	name or relative path is the new path component 
	 * 							to be appended to the base path. If relativePath
	 * 							is empty this function returns the base path. If
	 * 							relativePath is absolute this function returns null.
	 * @return					Resulting path.
	 */ 
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
	 * Deletes the directory or file in Path. Path must be absolute.
	 * 
	 * @return		TRUE if successful, FALSE otherwise.
	 */
	public boolean remove() {
		
		MainTerminal terminal = MainTerminal.get();
		if(this.exists())
		{
			if(this.isDirectory())
				terminal.execute("rmdir " + this.path);
			else
				terminal.execute("rm --force " + path);
		}
		
		return false;
	}
	
	/**
	 * Returns a subset of Path with index elements, starting from an end point.
	 * If index >= 0, returns the first index elements. If index < 0, it returns
	 * the last index elements.
	 * 
	 * @param index	
	 * @return		Subset of Path
	 * @see select(int start, int end)
	 */
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
	 * Path path1 = dirA/dirB/dirC/file.name
	 * 
	 * Calling path1.length() returns 3.
	 * 
	 * @return		Number of elements in Path.
	 */
	private int length() {
		return this.lenght;
	}

	/**
	 * Returns a subset of Path. start and end are the indexes of the beginning and
	 * finish of the subset, respectively. start must be less than end
	 * (start < end) otherwise the function returns null.
	 * 
	 * @param start	beginning of subset of Path 
	 * @param end	finish of subset of Path
	 * @return		subset of Path; null if subset cannot be obtained.
	 * @see	select(int index)
	 */
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
	 * Returns the extension of the last element in Path, if it exist.
	 * 
	 * @return		extension of the last element in Path, if it exist;
	 * 				null otherwise
	 */
	public String getExtension() {
		
		String result = null;
		
		if(this.get(this.lenght - 1).contains("."))
		{
			String temp  = this.get(this.lenght - 1);
			
			String[] parts = temp.split("\\.");
			
			result = parts[parts.length - 1];
		}
		else
			result = "";
		

		return result;
	}
}
