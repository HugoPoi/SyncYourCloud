package api;

import com.dropbox.client2.jsonextract.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
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
	public JsonList dropbox_app_key;
	
	public Config(String configPath) {
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
					dropbox_app_key = conf.get("dropbox_app_key").expectList();
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
		conf.put("dropbox_app_key", this.dropbox_app_key);
		conf.put("drives", jsondrives);
		StringWriter out = new StringWriter();
		try {
			JSONValue.writeJSONString(conf, out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("JSON :" + out.toString());
	}
}
