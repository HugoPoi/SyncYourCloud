package syc.mvc.model;


import api.DriveDropBox;
import api.DriveSkyDrive;
import api.Entry;
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
	public String logo_Dropbox = "Dropbox-logo-mini.png";
	public String logo_Google = "Google-logo.png";
	public String logo_Owncloud = "Owncloud-logo.png";
	public String logo_SkyDrive = "SkyDrive-logo-mini.png";

	public String getLogo_SkyDrive() {
		return logo_SkyDrive;
	}

	public void setLogo_SkyDrive(String logo_SkyDrive) {
		this.logo_SkyDrive = logo_SkyDrive;
		setChanged();
		notifyObservers();
	}

	private String[] driveTypeList = new String[] {"DropBox", "GoogleDrive", "SkyDrive","OwnDrive"};
	private int indexIntDriveSelectedOnDrivePage=0;
	private int indexDriveSelectedOnAddDrivePage=1;
	private String Drivelink;
	
	private String currentConfFile = null;
	private ManageDrive driveManagement;
	public ArrayList<IntDrive> drives;
	
	private String selectedDriveType = "";
	private DriveDropBox addDropbox = null;
	
	public int getIndexIntDriveSelectedOnDrivePage() {
		return indexIntDriveSelectedOnDrivePage;
	}

	public void setIndexIntDriveSelectedOnDrivePage(
		int indexIntDriveSelectedOnDrivePage) {
		this.indexIntDriveSelectedOnDrivePage = indexIntDriveSelectedOnDrivePage;
		setChanged();
		notifyObservers();
	}

	public String getSelectedDriveType() {
		return selectedDriveType;
	}
	
	public boolean validateToken(){
		if(this.addDropbox.validateToken()){
			this.drives.add(addDropbox);
			driveManagement.currentconf.save(drives);
			return true;
		}
		else{
			System.err.println("Erreur de validation de token");
			return false;
		}
	}
	
	public boolean validateToken(String url){
		DriveSkyDrive addSD = DriveSkyDrive.validateToken(url);
		if(addSD != null)
		{	
			this.drives.add(addSD);
			ManageDrive.currentconf.save(drives);
			return true;
		}
		else{
			System.err.println("Erreur de validation de token");
			return false;
		}
	}
	
	public void removeDrive(int index){
		drives.remove(index);
		ManageDrive.currentconf.save(drives);
		setChanged();
		notifyObservers();
	}

	public void setSelectedDriveType(String selectedDriveType) {
		this.selectedDriveType = selectedDriveType;

		switch(selectedDriveType){
			case "DropBox":
				this.addDropbox = new DriveDropBox();
				Drivelink = addDropbox.authUrl;
				break;
			case "SkyDrive":
				Drivelink = DriveSkyDrive.GetUrlForToken();
				break;
			default:
				Drivelink = "---- Erreur : Pas de lien ----";
				break;
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
	
	private static final String PATH_IMAGE_SYC=System.getProperty("user.dir" ).toString()+"/image/";
	
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
  	private String txt_Account_editDrive ;		
  	private String txt_Login_editDrive;	
  	private String txt_LocalLocation_editDrive = ".";	
  	
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

	public String getTxt_Account_editDrive() {
		return txt_Account_editDrive;
	}

	public void setTxt_Account_editDrive(String txt_Account_editDrive) {
		this.txt_Account_editDrive = txt_Account_editDrive;
		setChanged();
		notifyObservers();
	}

	public String getTxt_Login_editDrive() {
		return txt_Login_editDrive;
	}

	public void setTxt_Login_editDrive(String txt_Login_editDrive) {
		this.txt_Login_editDrive = txt_Login_editDrive;
		setChanged();
		notifyObservers();
	}

	public String getTxt_LocalLocation_editDrive() {
		return txt_LocalLocation_editDrive;
	}

	public void setTxt_LocalLocation_editDrive(String txt_LocalLocation_editDrive) {
		this.txt_LocalLocation_editDrive = txt_LocalLocation_editDrive;
		setChanged();
		notifyObservers();
	}

	public int getIndexDriveSelectedOnAddDrivePage() {
		return indexDriveSelectedOnAddDrivePage;
	}

	public void setIndexDriveSelectedOnAddDrivePage(int indexDriveSelectedOnAddDrivePage) {
		this.indexDriveSelectedOnAddDrivePage = indexDriveSelectedOnAddDrivePage;
		setChanged();
		notifyObservers();
	}  
	
	public String[] getDriveTypeList() {
		return driveTypeList;
	}

	public void setDriveTypeList(String[] driveTypeList) {
		this.driveTypeList = driveTypeList;
		setChanged();
		notifyObservers();
	}
}
