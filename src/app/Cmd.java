package app;

import api.*;

public class Cmd {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		api.Config loadcfg = new api.Config("test.json");
		DriveDropBox.init(loadcfg.dropbox_app_key);
		DriveDropBox db1 = new DriveDropBox(loadcfg.dropboxs.get(0));
		db1.getFile("t");
	}

}
