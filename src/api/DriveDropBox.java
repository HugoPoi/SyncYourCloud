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

import sun.invoke.empty.Empty;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class DriveDropBox implements IntDrive{
	
	private String key;
	private String secret;
	private String uid;
	private WebAuthSession session;
	private AccessTokenPair access;
	private WebAuthSession.WebAuthInfo authInfo;
	
	private DropboxAPI<?> api;
	
	private static AppKeyPair appKey;
	
	public String authUrl;
	
	
	/*
	 * 
	 * You must call this method before any creation of DropBox*/
	public static void init(JsonList inAppKey){
		try {
			appKey = new AppKeyPair(inAppKey.get(0).expectString(), inAppKey.get(1).expectString());
		} catch (JsonExtractionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public DriveDropBox(){
        session = new WebAuthSession(DriveDropBox.appKey, Session.AccessType.DROPBOX);
        try {
			authInfo = session.getAuthInfo();
			authUrl = authInfo.url;
		} catch (DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Boolean validateToken(){
        try {
			uid = session.retrieveWebAccessToken(authInfo.requestTokenPair);
		} catch (DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return (uid.length() == 0);
	}
	public DriveDropBox(JsonMap config){
		try {
			this.uid = config.get("uid").expectString();
			access = new AccessTokenPair(config.get("key").expectString(),config.get("secret").expectString());
		} catch (JsonExtractionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session = new WebAuthSession(DriveDropBox.appKey, Session.AccessType.DROPBOX, access);
		this.api = new DropboxAPI<WebAuthSession>(session);
		try {
			System.out.println("Connected to : DB "+ this.api.accountInfo().uid +" "+this.api.accountInfo().displayName);
		} catch (DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getFile(String dir){
		DropboxAPI.Entry root;
		try {
			root = api.metadata("/", 0, null, true, null);
			System.out.println("Files and Dir : ");
			Iterator<DropboxAPI.Entry> rootIterator = root.contents.iterator();
			while(rootIterator.hasNext()){
				System.out.println(rootIterator.next().path);
				}
		} catch (DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	
	@SuppressWarnings("unchecked")
	public JSONObject savedState(){
		JSONObject save =new JSONObject();
		save.put("type", "dropbox");
		save.put("uid", this.uid);
		save.put("key", access.key);
		save.put("secret", access.secret);	
		return save;
	}
	
	
}
