package syc.mvc.model;


import api.DriveDropBox;
import api.IntDrive;
import api.ManageDrive;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable 
{	
	//a creer supprimer ou adapter ds la ou les classe concerner
	//================================================================================================================
	private String testText = "INITIAL";
	private String logo_SYC = "SYC_image.jpg";
	/*private String logo_Dropbox = "Dropbox-logo.png";
	private String logo_Google = "Google-logo.png";
	private String logo_Owncloud = "Owncloud-logo.png";
	private String logo_SkyDrive = "SkyDrive-logo.png";*/

	private String Drivelink = "---- Erreur : Pas de lien ----";
	
	private String currentConfFile = null;
	private ManageDrive driveManagement;
	public ArrayList<IntDrive> drives;
	
	private String selectedDriveType = null;
	private DriveDropBox addDropbox = null;
	
	public String getSelectedDriveType() {
		return selectedDriveType;
	}

	public void setSelectedDriveType(String selectedDriveType) {
		this.selectedDriveType = selectedDriveType;
		if(selectedDriveType.equals("DropBox")){
			this.addDropbox = new DriveDropBox();
			Drivelink = addDropbox.authUrl;
		}
		setChanged();
		notifyObservers();
	}

	public String getCurrentConfFile() {
		return currentConfFile;
	}

	public void setCurrentConfFile(String currentConfFile) {
		this.currentConfFile = currentConfFile;
		driveManagement = new ManageDrive();
		drives = driveManagement.loadDrives(this.currentConfFile);
		setChanged();
		notifyObservers();
	}

	private static final String INITIAL_Value="";
	public static String getInitialValue() {
		return INITIAL_Value;
	}
	
	private static final String PATH_IMAGE_SYC=System.getProperty("user.dir" ).toString()+"\\image\\";
	public static String getPathImageSyc() {
		return PATH_IMAGE_SYC;
	}

	private boolean display_account;
    private boolean display_addDrive;
    private boolean display_addSynchronisationRule;
    private boolean display_authorization;
    private boolean display_drives;
    private boolean display_editDrive;
    private boolean display_home;
    private boolean display_synchronisationRules;   
    
    public Model()
    {
    	
    }
    
    
    public void init()
    {
    	display_account=false;
    	display_addDrive=false;
    	display_addSynchronisationRule=false; 
    	display_authorization=false;
    	display_drives=false;
    	display_editDrive=false;
    	display_home=false;
    	display_synchronisationRules=false;
    }
    
    public String getDrivelink() {
		return Drivelink;
	}

	public void setDrivelink(String drivelink) {
		Drivelink = drivelink;
		setChanged();
		notifyObservers();
	}

	public String getLogo_SYC() {
		return logo_SYC;
	}

	public void setLogo_SYC(String logo_SYC) {
		this.logo_SYC = logo_SYC;
		setChanged();
		notifyObservers();
	}

	public void settestText(String aTestText) 
    {
		this.testText = aTestText;
		setChanged();
		notifyObservers();
    } 

    public String gettestText() 
    {
    	return testText;
    }  
    
    public void setDisplay_account(boolean aDisplay_account) 
    {
		this.display_account = aDisplay_account;
		setChanged();
		notifyObservers();
    } 

    public boolean getDisplay_account() 
    {
    	return display_account;
    }  
    
    public void setDisplay_addDrive(boolean aDisplay_addDrive) 
    {
		this.display_addDrive = aDisplay_addDrive;
		setChanged();
		notifyObservers();
    } 

    public boolean getDisplay_addDrive() 
    {
    	return display_addDrive;
    }   
    
    public void setDisplay_addSynchronisationRule(boolean aDisplay_addSynchronisationRule) 
    {
		this.display_addSynchronisationRule = aDisplay_addSynchronisationRule;
		setChanged();
		notifyObservers();
    } 

    public boolean getDisplay_addSynchronisationRule() 
    {
    	return display_addSynchronisationRule;
    }   
    
    public void setDisplay_authorization(boolean aDisplay_authorization) 
    {
		this.display_authorization = aDisplay_authorization;
		setChanged();
		notifyObservers();
    } 

    public boolean getDisplay_authorization() 
    {
    	return display_authorization;
    } 
    
    public void setDisplay_drives(boolean aDisplay_drives) 
    {
		this.display_drives = aDisplay_drives;
		setChanged();
		notifyObservers();
    } 

    public boolean getDisplay_drives() 
    {
    	return display_drives;
    }   
    
    public void setDisplay_editDrive(boolean aDisplay_editDrive) 
    {
		this.display_editDrive = aDisplay_editDrive;
		setChanged();
		notifyObservers();
    } 

    public boolean getDisplay_editDrive() 
    {
    	return display_editDrive;
    }   
    
    public void setDisplay_home(boolean aDisplay_home) 
    {
		this.display_home = aDisplay_home;
		setChanged();
		notifyObservers();
    } 

    public boolean getDisplay_home() 
    {
    	return display_home;
    }      
    
    public void setDisplay_synchronisationRules(boolean aDisplay_synchronisationRules) 
    {
		this.display_synchronisationRules = aDisplay_synchronisationRules;
		setChanged();
		notifyObservers();
    } 

    public boolean getDisplay_synchronisationRules() 
    {
    	return display_synchronisationRules;
    }   
}
