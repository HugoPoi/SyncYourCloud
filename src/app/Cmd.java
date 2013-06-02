package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.ExampleMode;
import org.kohsuke.args4j.Option;

import api.*;

public class Cmd {

	ManageDrive driveManagement;
	ArrayList<IntDrive> drives;
	
	@Option(name = "listdrive",usage="List all drives connected.")
	public Boolean listDrives;
	
	@Option(name = "add",usage="add <dropbox,googledrive,skydrive>.")
	public String type;
	
	@Option(name = "list",usage="<path> list files.")
	public String path;
	
	public void run(String[] args){
		CmdLineParser parser = new CmdLineParser(this);
		try {
			parser.parseArgument(args);
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
			parser.printUsage(System.err);
			parser.printExample(ExampleMode.ALL);
			return;
		}
		
		driveManagement = new ManageDrive();
		drives = driveManagement.loadDrives("test.json");
		
		if(listDrives != null){
			this.list();
		}
		return;
	}
	
	public void add(){
		DriveDropBox addDrobox = new DriveDropBox();
		System.out.println(addDrobox.authUrl);
        try {
            while (System.in.read() != '\n');
        }
        catch (IOException ex) {
            
        }
		if(addDrobox.validateToken()){ System.out.println("Dropbox Successful Added"); };
		drives.add(addDrobox);
		driveManagement.currentconf.save(drives);
	}
	
	public void ls(){
		ArrayList<Entry> rootEntries = drives.get(0).getEntries(path);
		
		Iterator<Entry> itFiles = rootEntries.iterator();
		while(itFiles.hasNext()){
			Entry file = itFiles.next();
			System.out.println(file.getName());
		}
	}
	
	public void list(){
		Iterator<IntDrive> itDrive = drives.iterator();
		while (itDrive.hasNext()) {
			IntDrive intDrive = (IntDrive) itDrive.next();
			System.out.println(intDrive.toString());
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Cmd().run(args);
	}

}
