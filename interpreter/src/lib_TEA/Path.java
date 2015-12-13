package lib_TEA;

import java.util.ArrayList;

//Immutable Path Class for TEA.
/**
 * @author Enrique
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
	 * @author 
	 * @return Returns true if it is an absolute path.
	 * This method verifies if it is an absolute path.
	 */
	public boolean isAbsolute() {
		return isAbsolute;
	}
	
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
