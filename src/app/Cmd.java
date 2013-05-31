package app;

import java.util.ArrayList;
import java.util.Iterator;

import api.*;

public class Cmd {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManageDrive driveManagement = new ManageDrive();
		ArrayList<IntDrive> drives = driveManagement.loadDrives("test.json");
		
		/** Exemple add a dropbox **//*
		DriveDropBox addDrobox = new DriveDropBox();
		System.out.println(addDrobox.authUrl);
        try {
            while (System.in.read() != '\n');
        }
        catch (IOException ex) {
            
        }
		if(addDrobox.validateToken()){ System.out.println("Dropbox Successful Added"); };
		drives.add(addDrobox);
		driveManagement.currentconf.save(drives);*/
		/** End add a dropbox **/
		
		ArrayList<Entry> rootEntries = drives.get(0).getEntries("/");
		
		Iterator<Entry> itFiles = rootEntries.iterator();
		while(itFiles.hasNext()){
			Entry file = itFiles.next();
			System.out.println(file.getName());
		}
		
		driveManagement.currentconf.save(drives);
	}

}
