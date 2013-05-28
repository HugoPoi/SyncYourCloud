package api;

public interface IntDrive {
	
	//array of files
	public String getFiles(String dir);
	
	public void addDrive();
	
	public void removeDrive();
	
	public IntDrive listDrive();

}
