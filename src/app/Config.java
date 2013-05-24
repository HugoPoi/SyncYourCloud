package app;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;


//Doc : https://code.google.com/p/json-simple/wiki/DecodingExamples

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.dropbox.client2.jsonextract.JsonMap;


public class Config {
	
	public static final String CONF_FILE = "config.json";
	
	// TODO store conf
	//private static conf;
	
    public static void load()
    {
        JSONArray j;
        try {
            FileReader fin = new FileReader(CONF_FILE);
            try {
                j = (JSONArray) new JSONParser().parse(fin);
            } catch (ParseException ex) {
                throw Error.die("ERROR: State file \"" + CONF_FILE + "\" isn't valid JSON: " + ex.getMessage());
            } finally {
                fin.close();
            }
        }
        catch (IOException ex) {
            throw Error.die("ERROR: unable to load state file \"" + CONF_FILE + "\": " + ex.getMessage());
        }

        /*try {
            JsonMap jm = j.expectMap();

            JsonList japp = jm.get("app_key").expectList();
            AppKeyPair appKey = new AppKeyPair(japp.get(0).expectString(), japp.get(1).expectString());
            State state = new State(appKey);

            JsonMap jlinks = jm.get("links").expectMap();
            for (Map.Entry<String,JsonThing> jlink : jlinks) {
                JsonList jaccess = jlink.getValue().expectList();
                AccessTokenPair access = new AccessTokenPair(jaccess.get(0).expectString(), jaccess.get(1).expectString());
                state.links.put(jlink.getKey(), access);
            }

            return state;
        }
        catch (JsonExtractionException ex) {
            throw die ("ERROR: State file has incorrect structure: " + ex.getMessage());
        }*/
    }

}
