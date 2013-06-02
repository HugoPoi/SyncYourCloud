package app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.ExampleMode;
import org.kohsuke.args4j.Option;

import api.*;

public class Cmd {

	ManageDrive driveManagement;
	private ArrayList<IntDrive> drives;
	
	@Option(name = "--listdrive",usage="List all drives connected.")
	private Boolean listDrives = false;
	
	@Option(name = "--add",usage="add a drive. <dropbox,skydrive,googledrive>",metaVar="<type>")
	private String addDrive;
	
	@Option(name = "--download",usage="Download a file.")
	private Boolean download = false;
	
	@Argument
	private List<String> targets;
	
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
		
		if(listDrives){		
			drives = driveManagement.loadDrives("test.json");
			list();
		}
		
		if(addDrive != null){
			drives = driveManagement.loadDrives("test.json");
			add();
		}

		if(targets != null && targets.size() == 2 && !download){
			drives = driveManagement.loadDrives("test.json");
			ls(Integer.parseInt(targets.get(0)), targets.get(1));
		}
		
		if(targets != null && targets.size() == 2 && download){
			drives = driveManagement.loadDrives("test.json");
			dl(Integer.parseInt(targets.get(0)), targets.get(1),"./test");
		}
		
		System.exit(0);
	}
	
	public void add(){
		switch (addDrive) {
		case "dropbox":
			
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
			
			break;
		default: System.err.println("Api "+addDrive+" isn't implemented yet !");
			break;
		}
	}
	
	public void ls(int selectedDrive,String path){
		ArrayList<Entry> rootEntries = drives.get(selectedDrive).getEntries(path);
		
		Iterator<Entry> itFiles = rootEntries.iterator();
		while(itFiles.hasNext()){
			Entry file = itFiles.next();
			System.out.println(file.getName());
		}
	}
	public void dl(int selectedDrive,String path, String where){
		ArrayList<Entry> rootEntries = drives.get(selectedDrive).getEntries(path);
		
		Iterator<Entry> itFiles = rootEntries.iterator();
		while(itFiles.hasNext()){
			Entry file = itFiles.next();
			file.download(where + File.separator +file.getName());
		}
	}
	
	public void list(){
		Iterator<IntDrive> itDrive = drives.iterator();
		int i = 0;
		while (itDrive.hasNext()) {
			IntDrive intDrive = (IntDrive) itDrive.next();
			System.out.println( i++ +" "+intDrive.toString());
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Cmd().run(args);
	}

}
