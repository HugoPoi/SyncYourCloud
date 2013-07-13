package syc.mvc.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.json.simple.parser.JSONParser;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class Connexion {

	public static String fileConf;
	
	public static final String FILE = "Identifiants.json";
	
	public static boolean CheckLogin(String login){
		try{
			if(!new File(Connexion.FILE).exists())
				return true;
			
			JSONParser jp = new JSONParser();
			JSONArray jsa = (JSONArray) JSONSerializer.toJSON(IOUtils.toString(new FileReader(FILE)));
			
			for(Object o : jsa){
				JSONObject js = (JSONObject) o;
				if(js.getString("Login").equals(login))
					return false;	
			}
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	public static boolean Exist(String id, String pwd){
		try{
			
			JSONParser jp = new JSONParser();
			JSONArray jsa = (JSONArray) JSONSerializer.toJSON(IOUtils.toString(new FileReader(FILE)));
			
			for(Object o : jsa){
				JSONObject js = (JSONObject) o;
				
				if(js.getString("Login").equals(id) && Decrypt(js.getString("Password")).equals((pwd).toString())){
					fileConf = js.getString("File");
					return true;	
				}
			}
			
			return false;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean CreateIdentifiant(String id,String pwd){
		try {
			JSONObject js = new JSONObject();
			js.put("Login",id);
			js.put("Password", Encrypt(pwd).toString());
			js.put("File",id+".json");
			
			fileConf = id+".json";
			System.out.println(FILE);
			if(!new File(FILE).exists()){
				PrintWriter fileOut = new PrintWriter (new BufferedWriter (new FileWriter (FILE)));
				JSONArray array = new JSONArray();
				array.add(js);
				fileOut.write(array.toString());
				fileOut.close();
			}
			else
			{
				
			JSONArray jsa = (JSONArray) JSONSerializer.toJSON(IOUtils.toString(new FileReader(FILE)));
			jsa.add(js);
			
			PrintWriter fileOut2 = new PrintWriter (new BufferedWriter (new FileWriter (FILE))); 
			fileOut2.write(jsa.toString());
			fileOut2.close();
			}
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}		
	}
	
	private static String Encrypt(String passwd){
		
		try {
			byte[] encoded = Base64.encodeBase64(passwd.getBytes());
			return new String(encoded);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static String Decrypt(String passwd){
		
		try {
			return new String(Base64.decodeBase64(passwd.getBytes()),"UTF-8");
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
