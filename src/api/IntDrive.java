package api;

import java.io.OutputStream;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public interface IntDrive {
	
	public Sync getSync();
	
	public void setSync(String localpath);
	
	public void uploadFile(String path,OutputStream file);
	
	public Entry getEntryInfo(String path);
	
	public ArrayList<Entry> getEntries(String path);
	
	public void addDrive();
	
	public void removeDrive();
	
	public IntDrive listDrive();
	
	public JSONObject savedState();
	
	public String toString();
	
	public String getNiceName();
	
	public String getNiceSize();
	
	public String getId();

}
