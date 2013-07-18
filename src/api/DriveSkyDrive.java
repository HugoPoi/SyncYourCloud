package api;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.dropbox.client2.jsonextract.JsonExtractionException;
import com.dropbox.client2.jsonextract.JsonMap;
import com.dropbox.client2.session.AppKeyPair;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.google.gwt.dev.shell.remoteui.RemoteMessageProto.Message.Request;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import sun.awt.RequestFocusController;

import java.io.File;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class DriveSkyDrive implements IntDrive{
	
	
	private static AppKeyPair appKey;
	private String token;
	private final WebClient webClient = new WebClient();
	private WebRequest requestSettings;
    
    public static void init(String key, String secret) {
    	appKey = new AppKeyPair(key,secret);
    }
    
    public DriveSkyDrive(){}
    
    public DriveSkyDrive(JsonMap config){
    	try{
    		this.token = config.getOrNull("token").expectString();	
    	}
    	catch (JsonExtractionException e) {
			e.printStackTrace();
			this.token = null;
		}
    }
    
    // Pour la premiere initialisation de l'utilisateur
    public DriveSkyDrive(String newToken)
    {
    	this.token = newToken;
    }
    
    public static DriveSkyDrive validateToken(String url){
    	try{
	    	String token = url.split("#")[1].split("&")[0].substring(13);
	    	return new DriveSkyDrive(token);
    	}
    	catch(Exception e){
    		return null;
    	}
    }
    
    public static String GetUrlForToken(){
    	return String.format("https://login.live.com/oauth20_authorize.srf?client_id=%s&scope" +
    			"=wl.skydrive&response_type=token&redirect_uri=https://login.live.com/oauth20_desktop.srf", DriveSkyDrive.appKey.key);
    }
    
	@Override
	public void uploadFile(String path, OutputStream file) {
	}

	@Override
	public Entry getEntryInfo(String path) {
		return null;
	}
	
	public boolean deleteFile(String idFolder,File deleteFile){
		try {
		
		requestSettings = new WebRequest(new URL("https://apis.live.net/v5.0/" + idFolder + "/files?access_token=" + this.token));
		
		Page page = webClient.getPage(requestSettings);
		
		String reponse = page.getWebResponse().getContentAsString();
		
		JSONObject obj = (JSONObject) JSONSerializer.toJSON(reponse);
		JSONArray array = (JSONArray) obj.get("data");
		
		String id = null;
		
		for(int o = 0; o < array.size(); o++) {
			
			File tmp = new File(array.getJSONObject(0).getString("path"));
			String tmpid = array.getJSONObject(0).getString("id");
			
			if(tmp.getName().compareTo(deleteFile.getName()) == 0)
			{
				id = tmpid;
				break;
			}
		}
		
		if(id == null)
			return false;
		else{

			requestSettings = new WebRequest(new URL("https://apis.live.net/v5.0/" + id + "?access_token=" + this.token), HttpMethod.DELETE);
		
			webClient.loadWebResponse(requestSettings);
			
			deleteFile.delete();
			
			return true;
	
		}
		
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	
	 }
	
	public ArrayList<Entry> getRootEntries(){
		ArrayList<api.Entry> myEntries = new ArrayList<>();
		try {
			requestSettings = new WebRequest(new URL(String.format("https://apis.live.net/v5.0/me/skydrive/files?access_token=%s",this.token)),HttpMethod.GET);
			Page page = webClient.getPage(requestSettings);
			
			String reponse = page.getWebResponse().getContentAsString();
			JSONObject obj = (JSONObject)JSONSerializer.toJSON(reponse);
			JSONArray array = obj.getJSONArray("data");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy hh:mm:ss");
				
			for(int i=0;i<array.size();i++){
				JSONObject tmp = array.getJSONObject(i);
				String tmpPath = "/"+tmp.getString("name");
				
				if(tmp.getString("type")!="file")
					myEntries.addAll(getEntries2(tmp.getString("id"),tmpPath));
				
				myEntries.add(new EntrySkyDrive(this,tmp.getString("id"),tmp.getString("name"),tmpPath,sdf.parse(tmp.getString("updated_time").replace("T"," ")),sdf.parse(tmp.getString("created_time").replace("T"," ")),tmp.getString("type").equals("folder"),tmp.getLong("size"),null));
			}
			return myEntries;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Entry> getEntries2(String id,String path){
		try{
			ArrayList<api.Entry> myEntries = new ArrayList<>();
			try {
				requestSettings = new WebRequest(new URL(String.format("https://apis.live.net/v5.0/%s/files?access_token=%s",id,this.token)),HttpMethod.GET);
				Page page = webClient.getPage(requestSettings);
				
				String reponse = page.getWebResponse().getContentAsString();
				JSONObject obj = (JSONObject)JSONSerializer.toJSON(reponse);
				JSONArray array = obj.getJSONArray("data");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy hh:mm:ss");
					
				for(int i=0;i<array.size();i++){
					JSONObject tmp = array.getJSONObject(i);
					String tmpPath = path+"/"+tmp.getString("name");
					
					System.out.println(tmp.getString("type"));
					if(!tmp.getString("type").equals("file"))
						myEntries.addAll(getEntries2(tmp.getString("id"),tmpPath));
					
					myEntries.add(new EntrySkyDrive(this,tmp.getString("id"),tmp.getString("name"),tmpPath,sdf.parse(tmp.getString("updated_time").replace("T", " ")),sdf.parse(tmp.getString("created_time").replace("T", " ")),tmp.getString("type").equals("folder"),tmp.getLong("size"),null));
				}
				return myEntries;
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public ArrayList<Entry> getEntries(String id) {
		ArrayList<api.Entry> myEntries = new ArrayList<>();
		try{	
			requestSettings = new WebRequest(new URL(String.format("https://apis.live.net/v5.0/%s/files?access_token=%s",id,this.token)),HttpMethod.GET);
			Page page = webClient.getPage(requestSettings);
			
			String reponse = page.getWebResponse().getContentAsString();
			JSONArray ids = (JSONArray)JSONSerializer.toJSON(reponse);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mi:ss");
			
			for(int i=0;i<ids.size();i++){
				JSONObject tmp = ids.getJSONObject(i);
				myEntries.add(new EntrySkyDrive(this,tmp.getString("id"),tmp.getString("name"),tmp.getString("upload_location"),sdf.parse(tmp.getString("updated_time")),sdf.parse(tmp.getString("created_time")),tmp.getBoolean("type"),tmp.getLong("size"),null));
			}
			return myEntries;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void addDrive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeDrive() {
		
	}

	@Override
	public IntDrive listDrive() {
		return null;
	}

	@SuppressWarnings("unchecked")
	public org.json.simple.JSONObject savedState() {
		org.json.simple.JSONObject save = new org.json.simple.JSONObject();
		save.put("type", "skydrive");
		save.put("token", this.token);
		return save;
	}
	
	public boolean deleteFolder(File folder,String id) {		
		try {
			
			requestSettings = new WebRequest(new URL("https://apis.live.net/v5.0/" + id + "?access_token=" + this.token), HttpMethod.DELETE);
		
			webClient.loadWebResponse(requestSettings);
			
			suppRecurse(folder);
			
			return folder.delete();
			
		} catch (Exception e) {
			e.printStackTrace();		
			return false;
		} 
	}
    
	public boolean equals(Object obj){	
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DriveSkyDrive other = (DriveSkyDrive) obj;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}
	
	 public void suppRecurse(File r) {
		 
		    File [] fileList = r.listFiles();
		    
		    for(int i = 0;i<fileList.length;i++){
		    	
		      if(fileList[i].isDirectory() ){
		    	  suppRecurse(fileList[i]);
		        fileList[i].delete();
		      }else{
		        fileList[i].delete();
		      }
		      
		    }
	 }

	@Override
	public String getNiceName() {
		try {
			System.out.println(this.token);
			requestSettings = new WebRequest(new URL(String.format("https://apis.live.net/v5.0/me?access_token=%s",this.token)),HttpMethod.GET);
			Page page = webClient.getPage(requestSettings);
			
			String reponse = page.getWebResponse().getContentAsString();
			
			return ((JSONObject) JSONSerializer.toJSON(reponse)).getString("name");
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Erreur de nom";
		}
	}

	@Override
	public String getNiceSize() {
		return "0";
	}

	@Override
	public String getId() {
		try {
			requestSettings = new WebRequest(new URL(String.format("https://apis.live.net/v5.0/me?access_token=%s",this.token)),HttpMethod.GET);
			Page page = webClient.getPage(requestSettings);
			
			String reponse = page.getWebResponse().getContentAsString();
			
			return ((JSONObject) JSONSerializer.toJSON(reponse)).getString("id");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			return "Erreur de nom";
		}
	}

	@Override
	public Sync getSync() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSync(String localpath) {
		// TODO Auto-generated method stub
		
	}
    
	
}
