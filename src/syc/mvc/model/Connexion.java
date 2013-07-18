package syc.mvc.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
public class Connexion {

	public static String fileConf;
	
	private static String PATH_ACCOUNT = System.getProperty("user.dir" ).toString()+"\\account\\";
	
	public static final String FILE = "Identifiants.json";
	
	// Verifie si le login existe déja
	public static boolean CheckLogin(String login){
		try{
			if(!new File(FILE).exists())
				return true;
			
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
	
	// Verifie si login et mdp correspondent à un compte
	public static boolean Exist(String id, String pwd){
		try{
			
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
	
	// Creer un identifiant dans le fichier json
	public static boolean CreateIdentifiant(String id,String pwd){
		try {
			fileConf = PATH_ACCOUNT + id + ".json";
			JSONObject js = new JSONObject();
			js.put("Login",id);
			js.put("Password", Encrypt(pwd).toString());
			js.put("File",fileConf);
			
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
	
	// Crypte le mot de passe
	private static String Encrypt(String passwd){
		
		try {
			byte[] encoded = Base64.encodeBase64(passwd.getBytes());
			return new String(encoded);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Decrypte le mot de passe
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
