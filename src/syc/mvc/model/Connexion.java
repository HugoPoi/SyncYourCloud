package syc.mvc.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
public class Connexion {

	public static String fileConf;
	
	private static String PATH_ACCOUNT = System.getProperty("user.dir" ).toString()+File.separator + "account" + File.separator;
	
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
				if(js.getString("Login").equals(id) && js.getString("Password").equals((Encrypt(pwd)).toString())){
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
			
			File f;
			if(!(f = new File(PATH_ACCOUNT)).exists())
				f.mkdir();
				
			fileConf = PATH_ACCOUNT + id + ".json";
			JSONObject js = new JSONObject();
			js.put("Login",id);
			js.put("Password", Encrypt(pwd).toString());
			js.put("File",fileConf);
			
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
			byte[] bytesOfMessage = passwd.getBytes("UTF-8");

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < thedigest.length; ++i) {
				String hex = Integer.toHexString(thedigest[i]);
				if (hex.length() == 1) {
					sb.append(0);
					sb.append(hex.charAt(hex.length() - 1));
				} else 
					sb.append(hex.substring(hex.length() - 2));				
			}
			return sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
