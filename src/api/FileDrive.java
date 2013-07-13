package api;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FileDrive {
	private String name;
	private String path;
	private Date modificationDate;
	private Date creationDate;
	
	// Constructeur
	public FileDrive(String _name,String _path,Date _modificationDate,Date _creationDate){
		name = _name;
		path = _path;
		modificationDate = _modificationDate;
		creationDate = _creationDate;
	}	
	
	// Accesseurs/Mutateurs
	public String getName(){
		return name;
	}
	
	public void setName(String value){
		name = value;
	}
	
	public String getPath(){
		return path;
	}
	
	public void setPath(String value){
		path = value;
	}
	
	public Date getModifDate(){
		return modificationDate;
	}
	
	public void setModifDate(String value){
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm");
			modificationDate = formatter.parse(value);
		}
		catch(ParseException e)
		{
			System.out.println("Error while getting modification date");
		}
	}
	
	public Date getCreateDate(){
		return creationDate;
	}
	
	public void setCreateDate(String value){
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm");
			creationDate = formatter.parse(value);
		}
		catch(ParseException e)
		{
			System.out.println("Error while getting creation date");
		}
	}
	
	/*public int compareTo(FileDrive f){
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;
		
		int nameCompare = name.compareTo(f.getName());
		int pathCompare = path.compareTo(f.getPath());
		git 
		
	}*/
	
	// Méthode Equals redefinition
	public boolean Equals(FileDrive f){
		return (f.getName() == name) && 
			    (f.getPath() == path) && 
			    (f.getCreateDate() == creationDate) && 
			    (f.getModifDate() == modificationDate);
	}
}
