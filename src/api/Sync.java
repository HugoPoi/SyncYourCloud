package api;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class Sync {
	
	private String localStateHash;
	private String localpath;
	private IntDrive driveToSync;
	
	public Sync(IntDrive driveToSync, String localpath, String localStateHash){
		this.localStateHash = localStateHash;
		this.driveToSync = driveToSync;
		this.localpath = localpath;
	}
	
	public void start() {
		sync("/");
	}
	
	public void sync(String path){
		File local;
		ArrayList<Entry> rootEntries = driveToSync.getEntries("/");
		Iterator<Entry> itTree = rootEntries.iterator();
		while(itTree.hasNext()){
			Entry next = itTree.next();
			local = new File(localpath+next.path);
			if(local.exists()){
				if(new Date(local.lastModified()).compareTo(next.modificationDate) != 0){
					next.download(localpath+next.path);
				}
			}else{
				next.download(localpath+next.path);
			}
		}
	}

	public String getLocalStateHash() {
		return localStateHash;
	}

	public String getLocalpath() {
		return localpath;
	}

	public void setLocalpath(String localpath) {
		this.localpath = localpath;
	}
	
}
