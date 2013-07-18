package api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.dropbox.client2.exception.DropboxException;

public class EntryDropBox extends Entry{
	
	DriveDropBox parentDropBox;
	private String hash;

	public EntryDropBox(IntDrive _parentDrive, String _name, String _path,
			Date _modificationDate, Date _creationDate, Boolean _isDir,
			long _size, String _sizeHumanReadable, String hash) {
		super(_parentDrive, _name, _path, _modificationDate, _creationDate, _isDir,
				_size, _sizeHumanReadable);
		this.hash = hash;
		if(_parentDrive instanceof DriveDropBox) parentDropBox = (DriveDropBox) parentDrive;
	}
	
	public String getHash() {
		return hash;
	}

	@Override
	public void download(String localPath) {
		if(!this.isDir){
			try {
				FileOutputStream localFile = new FileOutputStream(localPath);
				parentDropBox.api.getFile(this.path,null,localFile,new CProgressListener());
				localFile.close();
				System.out.println("100");
			} catch (DropboxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			new File(localPath).mkdir();
			ArrayList<Entry> tree = parentDropBox.getEntries(this.path);
			Iterator<Entry> itTree = tree.iterator();
			while(itTree.hasNext()){
				Entry next = itTree.next();
				next.download(localPath+File.separator+next.name);
			}
			
		}
	}
	/*public void upload(FileInputStream file){
		
	}*/
}
