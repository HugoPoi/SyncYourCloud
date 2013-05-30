package app;

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
		DriveDropBox addDrobox = new DriveDropBox();
		
	}

}
