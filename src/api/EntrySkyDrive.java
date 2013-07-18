package api;

import java.util.Date;

public class EntrySkyDrive extends Entry {

		DriveSkyDrive parentSkyDrive;
		
		public EntrySkyDrive(IntDrive _parentDrive, String id, String _name, String _path,
				Date _modificationDate, Date _creationDate, Boolean _isDir,
				long _size, String _sizeHumanReadable){
			super(_parentDrive, _name, _path, _modificationDate, _creationDate, _isDir, _size, _sizeHumanReadable);
			if(_parentDrive instanceof DriveSkyDrive) parentSkyDrive = (DriveSkyDrive) parentDrive;
		}

		@Override
		public void download(String localFilePath) {
			// TODO Auto-generated method stub
			
		}
}
