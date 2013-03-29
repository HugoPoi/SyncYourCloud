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

public class DriveDropBox implements drive{
	
	public DriveDropBox(){
		
	}
	public String getFiles(){
		return "Files";
	}
    private static void doLink(String[] args)
            throws DropboxException
        {
            if (args.length != 1) {
                throw die("ERROR: \"link\" takes no arguments.");
            }

            // Load state.
            State state = State.load(STATE_FILE);

            WebAuthSession was = new WebAuthSession(state.appKey, Session.AccessType.APP_FOLDER);

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

            // This will fail if the user didn't visit the above URL and hit 'Allow'.
            String uid = was.retrieveWebAccessToken(info.requestTokenPair);
            AccessTokenPair accessToken = was.getAccessTokenPair();
            System.out.println("Link successful.");

            state.links.put(uid, accessToken);
            state.save(STATE_FILE);
        }
}
