package api;

import com.dropbox.client2.jsonextract.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Config {
	
	private JsonThing data;
	
	public ArrayList<JsonMap> drives;
	public String dropboxAppKey;
	public String dropboxAppSecret;
	public String configPath;
	
	public Config(String _configPath) {
		configPath = _configPath;
		drives = new ArrayList<JsonMap>();
		try {
			FileReader file = new FileReader(configPath);
			BufferedReader filebuf = new BufferedReader(file);
			
			try {
				data = new JsonThing(new JSONParser().parse(filebuf));
			} catch (ParseException e) {
				e.printStackTrace();
				System.exit(1);
			}
			finally{
				try {
					JsonMap conf = data.expectMap();
					dropboxAppKey = conf.get("dropbox_app_key").expectList().get(0).expectString();
					dropboxAppSecret = conf.get("dropbox_app_key").expectList().get(1).expectString();
					JsonList dropboxs = conf.get("drives").expectList();
					Iterator<JsonThing> dropboxIterator = dropboxs.iterator();
					while(dropboxIterator.hasNext()){
						this.drives.add(dropboxIterator.next().expectMap());
					}
					
				} catch (JsonExtractionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			filebuf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void save(ArrayList<IntDrive> drives){
		JSONArray jsondrives = new JSONArray();
		Iterator<IntDrive> drivesIterator = drives.iterator();
		while(drivesIterator.hasNext()){
			jsondrives.add(drivesIterator.next().savedState());
		}
		JSONObject conf = new JSONObject();
		JSONArray dropbox_app_keys = new JSONArray();
		dropbox_app_keys.add(dropboxAppKey);
		dropbox_app_keys.add(dropboxAppSecret);
		conf.put("dropbox_app_key", dropbox_app_keys);
		conf.put("drives", jsondrives);
		try {
			FileWriter saved = new FileWriter(configPath);
			JSONValue.writeJSONString(conf, saved);
			saved.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
