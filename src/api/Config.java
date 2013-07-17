package api;

import com.dropbox.client2.jsonextract.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Config {
	
	public ArrayList<JsonMap> drives;
	protected String dropboxAppKey;
	protected String dropboxAppSecret;
	protected String skydriveAppKey;
	protected String skydriveAppSecret;
	private String appConfigPath;
	private String userConfigPath;
	
	public Config(String _appConfigPath,String _userConfigPath) {
		appConfigPath = _appConfigPath;
		userConfigPath = _userConfigPath;
		drives = new ArrayList<JsonMap>();
		
		FileReader appConfigFile;
		BufferedReader appConfigBuffer = null;
		try {
			appConfigFile = new FileReader(appConfigPath);
			appConfigBuffer = new BufferedReader(appConfigFile);
		} catch (FileNotFoundException e1) {
			System.err.println("Application configuration '"+appConfigPath+"' file not found.");
			System.exit(1);
		}
		FileReader userConfigFile;
		BufferedReader userConfigBuffer = null;
		try {
			userConfigFile = new FileReader(userConfigPath);
			userConfigBuffer = new BufferedReader(userConfigFile);
		} catch (FileNotFoundException e1) {
			
		}
		
		
		try {
			
			JsonThing appConfigJson = new JsonThing(new JSONParser().parse(appConfigBuffer));
			JsonMap appConf = appConfigJson.expectMap();
			
			dropboxAppKey = appConf.get("dropbox_app_key").expectList().get(0).expectString();
			dropboxAppSecret = appConf.get("dropbox_app_key").expectList().get(1).expectString();
			skydriveAppKey = appConf.get("skydrive_app_key").expectList().get(0).expectString();
			skydriveAppSecret = appConf.get("skydrive_app_key").expectList().get(1).expectString();
			
			
		} catch (JsonExtractionException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (ParseException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		JsonThing userConfigJson;
		try {
			userConfigJson = new JsonThing(new JSONParser().parse(userConfigBuffer));
			JsonList clouds = userConfigJson.expectList();
			
			Iterator<JsonThing> cloudsIterator = clouds.iterator();
			while(cloudsIterator.hasNext()){
				this.drives.add(cloudsIterator.next().expectMap());
			}
		} catch (IOException e) {
		} catch (ParseException e) {
		} catch (JsonExtractionException e) {
		} catch (NullPointerException e) {
			System.err.println("First launch no drives !");
		}

		
		try {
			appConfigBuffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			userConfigBuffer.close();
		} catch (IOException e) {
		} catch (NullPointerException e) {
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void save(ArrayList<IntDrive> drives){
		JSONArray jsondrives = new JSONArray();
		Iterator<IntDrive> drivesIterator = drives.iterator();
		while(drivesIterator.hasNext()){
			jsondrives.add(drivesIterator.next().savedState());
		}
		try {
			FileWriter saved = new FileWriter(userConfigPath);
			JSONValue.writeJSONString(jsondrives, saved);
			saved.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
