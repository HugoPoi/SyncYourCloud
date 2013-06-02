package api;

import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public abstract class Entry {
	
	private IntDrive parentDrive;
	private String name;
	private String path;
	private Boolean isDir;
	private Date modificationDate;
	private Date creationDate;
	private long size;
	private String sizeHumanReadable;	
	
	// Constructeur
	public Entry(IntDrive _parentDrive,String _name,String _path,Date _modificationDate,Date _creationDate,Boolean _isDir, long _size,String _sizeHumanReadable){
		parentDrive = _parentDrive;
		name = _name;
		path = _path;
		isDir = _isDir;
		modificationDate = _modificationDate;
		creationDate = _creationDate;
		size = _size;
		sizeHumanReadable = _sizeHumanReadable;
	}	
	
	public static Date convertDate(String value){
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			return formatter.parse(value);
		}
		catch(ParseException e)
		{
			System.out.println("Error while getting modification date");
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((isDir == null) ? 0 : isDir.hashCode());
		result = prime
				* result
				+ ((modificationDate == null) ? 0 : modificationDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((parentDrive == null) ? 0 : parentDrive.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + (int) (size ^ (size >>> 32));
		result = prime
				* result
				+ ((sizeHumanReadable == null) ? 0 : sizeHumanReadable
						.hashCode());
		return result;
	}
	
	public ArrayList<Entry> getContents(){
		if(this.isDir) return parentDrive.getEntries(this.path);
		else return null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entry other = (Entry) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (isDir == null) {
			if (other.isDir != null)
				return false;
		} else if (!isDir.equals(other.isDir))
			return false;
		if (modificationDate == null) {
			if (other.modificationDate != null)
				return false;
		} else if (!modificationDate.equals(other.modificationDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parentDrive == null) {
			if (other.parentDrive != null)
				return false;
		} else if (!parentDrive.equals(other.parentDrive))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public abstract void downloadFile(String path, OutputStream file);
}
