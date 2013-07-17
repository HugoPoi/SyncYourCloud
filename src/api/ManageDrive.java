package api;

import java.util.ArrayList;
import java.util.Iterator;

import com.dropbox.client2.jsonextract.JsonExtractionException;
import com.dropbox.client2.jsonextract.JsonMap;

public class ManageDrive {
	
	public static Config currentconf;
	
	public static final String appConfigPath = "appconf.json";
	
	public ArrayList<IntDrive> loadDrives(String userConfPath){
		ArrayList<IntDrive> drives = new ArrayList<IntDrive>();
		currentconf = new api.Config(appConfigPath,userConfPath);
		DriveDropBox.init(currentconf.dropboxAppKey,currentconf.dropboxAppSecret);
		DriveSkyDrive.init(currentconf.skydriveAppKey, currentconf.skydriveAppSecret);
		Iterator<JsonMap> drivesConfIterator = currentconf.drives.iterator();
		while(drivesConfIterator.hasNext()){
			JsonMap confDrive = drivesConfIterator.next();
			try {
				switch (confDrive.get("type").expectString()) {
				case "dropbox": 
					drives.add(new DriveDropBox(confDrive));
					break;
				case "skydrive":
					drives.add(new DriveSkyDrive(confDrive));
					break;
				case "googledrive":
					drives.add(new DriveGoogle(confDrive));
				default:
					break;
				}
			} catch (JsonExtractionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return drives;
	}

}
