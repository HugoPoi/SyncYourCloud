package api;

import org.json.simple.JSONObject;

public interface IntDrive {
	
	//array of files
	public String getFile(String dir);
	
	public void addDrive();
	
	public void removeDrive();
	
	public IntDrive listDrive();
	
	public JSONObject savedState();

}
