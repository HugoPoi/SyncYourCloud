package api;

import com.dropbox.client2.jsonextract.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Config {
	
	private JsonThing data;
	
	public ArrayList<JsonMap> dropboxs;
	public JsonList dropbox_app_key;
	
	public Config(String configPath) {
		dropboxs = new ArrayList<JsonMap>();
		try {
			FileReader file = new FileReader(configPath);
			try {
				data = new JsonThing(new JSONParser().parse(file));
			} catch (ParseException e) {
				e.printStackTrace();
				System.exit(1);
			}
			finally{
				try {
					JsonMap conf = data.expectMap();
					dropbox_app_key = conf.get("dropbox_app_key").expectList();
					JsonList dropboxs = conf.get("dropbox").expectList();
					Iterator<JsonThing> dropboxIterator = dropboxs.iterator();
					while(dropboxIterator.hasNext()){
						this.dropboxs.add(dropboxIterator.next().expectMap());
					}
					
				} catch (JsonExtractionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
}
