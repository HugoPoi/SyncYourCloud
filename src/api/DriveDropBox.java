package api;

import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.Session;
import com.dropbox.client2.session.WebAuthSession;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.DropboxAPI;

import com.dropbox.client2.jsonextract.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class DriveDropBox implements IntDrive{
	
	//Le status de connexion à l'API Dropbox est sauvegardé dans un fichier json
	//prévoir le stockage de toute la conf dans un seul fichier
	public static final String STATE_FILE = "dropbox_status.json";
	
	private static State state;
	
	public DriveDropBox() throws DropboxException{
		
		// Load state, apikey
		if(state == null) state = State.load(STATE_FILE);
	}
 
	// doLink add a dropbox
    private void doLink()
            throws DropboxException
        {

            WebAuthSession was = new WebAuthSession(state.appKey, Session.AccessType.DROPBOX);

            // Make the user log in and authorize us.
            WebAuthSession.WebAuthInfo info = was.getAuthInfo();
            System.out.println("1. Go to: " + info.url);
            System.out.println("2. Allow access to this app.");
            System.out.println("3. Press ENTER.");

            try {
                while (System.in.read() != '\n') {}
            }
            catch (IOException ex) {
                throw die("I/O error: " + ex.getMessage());
            }

            // TODO This will fail if the user didn't visit the above URL and hit 'Allow'.
            String uid = was.retrieveWebAccessToken(info.requestTokenPair);
            AccessTokenPair accessToken = was.getAccessTokenPair();
            System.out.println("Link successful.");

            state.links.put(uid, accessToken);
            state.save(STATE_FILE);
        }
   
	public void addDrive() {
		try {
			this.doLink();
		} catch (DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    // ------------------------------------------------------------------------
    // State model (load+save to JSON)

    public static final class State
    {
        public final AppKeyPair appKey;
        public final Map<String,AccessTokenPair> links = new HashMap<String,AccessTokenPair>();

        public State(AppKeyPair appKey)
        {
            this.appKey = appKey;
        }

        public void save(String fileName)
        {
            JSONObject jstate = new JSONObject();

            // Convert app key
            JSONArray japp = new JSONArray();
            japp.add(appKey.key);
            japp.add(appKey.secret);
            jstate.put("app_key", japp);

            // Convert 'Link' objects (uid -> access token)
            JSONObject jlinks = new JSONObject();
            for (Map.Entry<String,AccessTokenPair> link : links.entrySet()) {
                String uid = link.getKey();
                AccessTokenPair access = link.getValue();
                JSONArray jaccess = new JSONArray();
                jaccess.add(access.key);
                jaccess.add(access.secret);
                jlinks.put(uid, jaccess);
            }
            jstate.put("links", jlinks);

            try {
                FileWriter fout = new FileWriter(fileName);
                try {
                    jstate.writeJSONString(fout);
                }
                finally {
                    fout.close();
                }
            }
            catch (IOException ex) {
                throw die("ERROR: unable to save to state file \"" + fileName + "\": " + ex.getMessage());
            }
        }

        public static State load(String fileName)
        {
            JsonThing j;
            try {
                FileReader fin = new FileReader(fileName);
                try {
                    j = new JsonThing(new JSONParser().parse(fin));
                } catch (ParseException ex) {
                    throw die("ERROR: State file \"" + fileName + "\" isn't valid JSON: " + ex.getMessage());
                } finally {
                    fin.close();
                }
            }
            catch (IOException ex) {
                throw die("ERROR: unable to load state file \"" + fileName + "\": " + ex.getMessage());
            }

            try {
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
            }
        }
    }

    public static final class Link
    {
        public final String uid;
        public final AccessTokenPair accessToken;

        public Link(String uid, AccessTokenPair accessToken)
        {
            this.uid = uid;
            this.accessToken = accessToken;
        }
    }
	
    public String getFiles(){
		return "Files";
	}

	@Override
	public void removeDrive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IntDrive listDrive() {
		// TODO Auto-generated method stub
		return null;
	}
}
