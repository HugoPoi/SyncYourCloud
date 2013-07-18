package api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;

public class EntrySkyDrive extends Entry {

		DriveSkyDrive parentSkyDrive;
		String id;
		
		public EntrySkyDrive(IntDrive _parentDrive, String _id, String _name, String _path,
				Date _modificationDate, Date _creationDate, Boolean _isDir,
				long _size, String _sizeHumanReadable){
			super(_parentDrive, _name, _path, _modificationDate, _creationDate, _isDir, _size, _sizeHumanReadable);
			if(_parentDrive instanceof DriveSkyDrive) parentSkyDrive = (DriveSkyDrive) parentDrive;
			id = _id;
		}

		@Override
		public void download(String localFilePath) {
			System.out.println(localFilePath);
			if(!isDir)
			{
				try {
					WebClient webClient = new WebClient();
					WebRequest requestSettings = new WebRequest(new URL("https://apis.live.net/v5.0/" + this.id + "/content?access_token=" + this.parentSkyDrive.getToken()));
					
					Page page = webClient.getPage(requestSettings);				
					InputStream reponse = page.getWebResponse().getContentAsStream();
					FileOutputStream writer = new FileOutputStream(localFilePath);
					int x;
					while((x = reponse.read()) != -1 )
						writer.write(x);
					
					writer.close();
					
				} catch (FileNotFoundException e) {
					System.err.println("Le fichier n'existe pas.");
					e.printStackTrace();
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
			}
			else
			{
				new File(localFilePath).mkdir();
				ArrayList<Entry> tree = parentSkyDrive.getEntries(this.path);
				Iterator<Entry> itTree = tree.iterator();
				while(itTree.hasNext()){
					Entry next = itTree.next();
					next.download(localFilePath+File.separator+next.name);
				}
			}
		}
}
