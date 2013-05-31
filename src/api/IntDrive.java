package api;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public interface IntDrive {
	
	public InputStream downloadFile(String path);
	
	public void uploadFile(String path,OutputStream file);
	
	public Entry getEntryInfo(String path);
	
	public ArrayList<Entry> getEntries(String dir);
	
	public void addDrive();
	
	public void removeDrive();
	
	public IntDrive listDrive();
	
	public JSONObject savedState();

}
