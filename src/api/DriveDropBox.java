package api;

import com.dropbox.client2.DropboxAPI.Account;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.Session;
import com.dropbox.client2.session.WebAuthSession;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.DropboxFileInfo;
import com.dropbox.client2.DropboxAPI.DropboxInputStream;
import com.dropbox.client2.RESTUtility;

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
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class DriveDropBox implements IntDrive{
	
	private String uid;
	private WebAuthSession session;
	private AccessTokenPair access;
	private WebAuthSession.WebAuthInfo authInfo;
	
	protected DropboxAPI<?> api;
	private Account accountInfos;
	
	private static AppKeyPair appKey;
	
	public String authUrl;
	
	
	/*
	 * 
	 * You must call this method before any creation of DropBox*/
	public static void init(String key, String secret){
			appKey = new AppKeyPair(key,secret);
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
			access = session.getAccessTokenPair();
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
			accountInfos = this.api.accountInfo();
			System.out.println("Connected to : DB "+ accountInfos.uid +" "+accountInfos.displayName);
		} catch (DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		JSONObject save = new JSONObject();
		save.put("type", "dropbox");
		save.put("uid", this.uid);
		save.put("key", access.key);
		save.put("secret", access.secret);	
		return save;
	}

	@Override
	public void uploadFile(String path, OutputStream file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public api.Entry getEntryInfo(String path) {
		// TODO Auto-generated method stub
		DropboxAPI.Entry entryInfo;
		try {
			entryInfo = api.metadata(path, 0, null, true, null);
			return new api.EntryDropBox(this,entryInfo.fileName(),entryInfo.path,RESTUtility.parseDate(entryInfo.modified),RESTUtility.parseDate(entryInfo.modified),entryInfo.isDir,entryInfo.bytes,entryInfo.size);
		} catch (DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DriveDropBox other = (DriveDropBox) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

	@Override
	public ArrayList<api.Entry> getEntries(String dir) {
		DropboxAPI.Entry base;
		ArrayList<api.Entry> outEntries = new ArrayList<>();
		try {
			base = api.metadata(dir, 0, null, true, null);
			if(base.isDir){
			Iterator<com.dropbox.client2.DropboxAPI.Entry> it = base.contents.iterator();
			while(it.hasNext()){
				com.dropbox.client2.DropboxAPI.Entry curfile = it.next();
				outEntries.add(new api.EntryDropBox(this,curfile.fileName(),curfile.path,RESTUtility.parseDate(curfile.modified),RESTUtility.parseDate(curfile.modified),curfile.isDir,curfile.bytes,curfile.size));
			}
			return outEntries;
			}
		} catch (DropboxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	public String toString(){
		return "DP:"+ uid + " "+ accountInfos.displayName;
	}
}
