package api;

import java.io.OutputStream;
import java.util.Date;

import com.dropbox.client2.DropboxAPI.DropboxFileInfo;
import com.dropbox.client2.exception.DropboxException;

public class EntryDropBox extends Entry{
	
	DriveDropBox parentDropBox;

	public EntryDropBox(IntDrive _parentDrive, String _name, String _path,
			Date _modificationDate, Date _creationDate, Boolean _isDir,
			long _size, String _sizeHumanReadable) {
		super(_parentDrive, _name, _path, _modificationDate, _creationDate, _isDir,
				_size, _sizeHumanReadable);
		if(_parentDrive instanceof DriveDropBox) parentDropBox = (DriveDropBox) _parentDrive;
	}
	
	@Override
	public void downloadFile(String path, OutputStream file) {
		try {
			DropboxFileInfo fileinfo = parentDropBox.api.getFile(path,null,file,null);
		} catch (DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
