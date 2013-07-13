package api;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.dropbox.client2.jsonextract.JsonExtractionException;
import com.dropbox.client2.jsonextract.JsonMap;
import com.dropbox.client2.session.AppKeyPair;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import org.json.simple.JSONObject;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class DriveSkyDrive implements IntDrive{
	
	
	private static AppKeyPair appKey;
	private String token;
    
    public static void init(String key, String secret) {
    	appKey = new AppKeyPair(key,secret);
    }
    
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
    
    public static String GetUrlForToken(){
    	return String.format("https://login.live.com/oauth20_authorize.srf?client_id={0}&scope" +
    			"=wl.skydrive&response_type=token&redirect_uri=https://login.live.com/oauth20_desktop.srf", DriveSkyDrive.appKey.key);
    }
    
	@Override
	public void uploadFile(String path, OutputStream file) {
		// TODO Auto-generated method stub
	}

	@Override
	public Entry getEntryInfo(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Entry> getEntries(String path) {
		ArrayList<api.Entry> myEntries = new ArrayList<>();
		try{
			Client client = Client.create();
			
			WebResource wr = client.resource(String.format("https://apis.live.net/v5.0/{0}?access_token={1}",path,this.token));
			
			ClientResponse cr = wr.accept("application/json").get(ClientResponse.class);
			
			// A voir si on met en place un logger
			if(cr.getStatus() != 200)
				return null;
			
			String output = cr.getEntity(String.class);
			
			JSONObject obj = (JSONObject) JSONSerializer.toJSON(output);
			JSONArray ids = (JSONArray) obj.get("data");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mi:ss");
			
			for(int i=0;i<ids.size();i++){
				net.sf.json.JSONObject tmp = ids.getJSONObject(i);
				myEntries.add(new EntrySkyDrive(this,tmp.getString("name"),tmp.getString("upload_location"),sdf.parse(tmp.getString("updated_time")),sdf.parse(tmp.getString("created_time")),tmp.getString("id").startsWith("folder"),tmp.getLong("size"),null));
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public IntDrive listDrive() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public JSONObject savedState() {
		JSONObject save = new JSONObject();
		save.put("type", "skydrive");
		save.put("token", this.token);
		return save;
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

	@Override
	public String getNiceName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNiceSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
    
	
}
