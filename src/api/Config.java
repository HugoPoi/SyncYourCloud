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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Config {
	
	private JsonThing data;
	
	public Config(String configPath) {
		try {
			FileReader file = new FileReader(configPath);
			try {
				data = new JsonThing(new JSONParser().parse(file));
			} catch (ParseException e) {
				e.printStackTrace();
				System.exit(1);
			}
			finally{
				System.out.println(data.toString());
				try {
					JsonMap tab = data.expectMap();
					Iterator<Entry<String, JsonThing>> i = tab.iterator();
					while(i.hasNext()){
						Entry<String, JsonThing> ele = i.next();
						System.out.println(ele.getKey());
						JsonMap entry = ele.getValue().expectMap();
						System.out.println("_name:"+entry.get("login").expectString());
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
