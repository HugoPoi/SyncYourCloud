package api;

import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.Session;
import com.dropbox.client2.session.WebAuthSession;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.DropboxAPI;

import com.dropbox.client2.jsonextract.*;
import com.sun.security.auth.SolarisPrincipal;

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
	
	private String key;
	private String secret;
	private String uid;
	
	private static AppKeyPair appKey;
	
	public static void init(JsonList inAppKey){
		try {
			appKey = new AppKeyPair(inAppKey.get(0).expectString(), inAppKey.get(1).expectString());
		} catch (JsonExtractionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DriveDropBox(String uid, String key, String secret){
		this.key = key;
		this.secret = secret;
		this.uid = uid;
	}
	public DriveDropBox(JsonMap config){
		try {
			this.uid = config.get("uid").expectString();
			this.key = config.get("key").expectString();
			this.secret = config.get("secret").expectString();
		} catch (JsonExtractionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String getFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDrive() {
		
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
