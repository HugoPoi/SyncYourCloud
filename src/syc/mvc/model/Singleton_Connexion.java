package syc.mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singleton_Connexion 
{
	private String connexionUrl ;/*"driver:base://localhost:port"*/
	private String login;
	private String password ;
	private boolean connexionState ;
	
	private static Connection lonelyConnexion;
	
	public Singleton_Connexion() 
	{
		try 
		{
			lonelyConnexion = DriverManager.getConnection(connexionUrl, login, password);
	  	} 
		catch (SQLException e) 
	  	{
		    e.printStackTrace();
	  	}
		finally
		{
			if(lonelyConnexion==null)
			{
				this.connexionState=false;
			}
			else
			{
				this.connexionState=true;
			}
		}
	}
	
	public String getLogin() 
	{
		return login;
	}

	public void setLogin(String login) 
	{
		this.login = login;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
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
