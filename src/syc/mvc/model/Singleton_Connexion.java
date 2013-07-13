package syc.mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singleton_Connexion 
{
	private static String connexionUrl ;/*"driver:base://localhost:port"*/
	private static String login;
	private static String password ;
	private static boolean connexionState ;
	
	private static Connection lonelyConnexion;
	
	public static void Singleton_Connexion() 
	{
		try 
		{
			lonelyConnexion = DriverManager.getConnection(connexionUrl, login, password);
	  	} 
		catch (SQLException e) 
	  	{
		    e.printStackTrace();
		    lonelyConnexion = null;
	  	}
		finally
		{
			connexionState = lonelyConnexion==null ? false : true;
		}
	}
	
	public String getLogin() 
	{
		return login;
	}

	public void setLogin(String _login) 
	{
		login = _login;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String _password) 
	{
		password = _password;
	}

	public boolean isConnexionState() 
	{
		return connexionState;
	}

	public static Connection getInstance()
	{
		  if(lonelyConnexion == null)
		  {
			  new Singleton_Connexion();
		  }
		  return lonelyConnexion;   
	} 
	
	public static void KillInstance()
	{
		  if(lonelyConnexion != null)
		  {
			  lonelyConnexion=null;
		  }   
	}
}
