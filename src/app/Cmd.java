package app;

import java.io.IOException;
import java.util.ArrayList;

import api.*;

public class Cmd {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManageDrive driveManagement = new ManageDrive();
		ArrayList<IntDrive> drives = driveManagement.loadDrives("test.json");
		
		/** Exemple add a dropbox **/
		DriveDropBox addDrobox = new DriveDropBox();
		System.out.println(addDrobox.authUrl);
        try {
            while (System.in.read() != '\n');
        }
        catch (IOException ex) {
            
        }
		addDrobox.validateToken();
		/** End add a dropbox **/
		
	}

}
