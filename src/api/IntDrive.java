package api;

public interface IntDrive {
	
	//array of files
	public String getFile(String dir);
	
	public void addDrive();
	
	public void removeDrive();
	
	public IntDrive listDrive();

}
