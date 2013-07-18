package syc.mvc.controller;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.EventObject;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.w3c.dom.events.MouseEvent;

import syc.mvc.model.Model;
import syc.mvc.view.IHM_addDrive;
import syc.mvc.view.IHM_authorization;

public class Controller_authorization implements ActionListener,MouseListener
{
	private Model model_SYC;
    private IHM_authorization view_authorization;
    
    public Controller_authorization(Model aModel_SYC, IHM_authorization aView_authorization) 
    {
		this.model_SYC = aModel_SYC;
		this.view_authorization = aView_authorization; 
		this.ControllerActionListenerForComponent(view_authorization.getContentPane());
    }
	
    public void ControllerActionListenerForComponent(Container cont_temps)
	{
		if(cont_temps instanceof Container)
		{
			int i;
			int j =cont_temps.getComponentCount();
			for(i =0; i< j ;i++)
			{
				Component cmp_temps = cont_temps.getComponents()[i];
				//System.out.println(j);
						
				if(cmp_temps instanceof JButton)
				{	
					((JButton) cmp_temps).addActionListener(this);
				}
				if(cmp_temps instanceof JTextField )
				{
					((JTextField) cmp_temps).addActionListener(this);
				}
				if(cmp_temps instanceof JPasswordField )
				{
					((JPasswordField) cmp_temps).addActionListener(this);
				}
				if(cmp_temps instanceof JComboBox)
				{
					ControllerActionListenerForComponent((JComboBox<String>) cmp_temps);
				}
				if(cmp_temps instanceof Container)
				{
					ControllerActionListenerForComponent((Container) cmp_temps); 
				}
				if(cmp_temps instanceof JLabel)
				{
					cmp_temps.addMouseListener(this);	
				}
			}
		}	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==this.view_authorization.getjBt_Cancel())
		{
			//Back to IHM_drives
			this.view_authorization.getjLab_LiensURL().setForeground(Color.blue);
			model_SYC.init();
			model_SYC.setDisplay_drives(true);
		}	
		
		if(e.getSource()==this.view_authorization.getjBt_AddDriveAccount())
		{
			if(this.model_SYC.getSelectedDriveType().equals("SkyDrive")){
				if(this.view_authorization.getJt_UrlSkyDrive().isEmpty())
				{
					JOptionPane.showMessageDialog (this.view_authorization,"Veuillez copier l'url retourné.","SYC message",2);
					return;
				}
			}
			//add the drive account
			/*if(this.view_authorization.getjLab_LiensURL().getForeground()!=Color.red)
			{
				JOptionPane.showMessageDialog (this.view_authorization,"Veuillez cliquer sur le lien","SYC message",1);				
			}
			else
			{*/
				//Back to IHM_drives
				this.view_authorization.getjLab_LiensURL().setForeground(Color.blue);
				boolean result = true;
				switch(model_SYC.getSelectedDriveType()){
				   case "DropBox":
						result = this.model_SYC.validateToken();
						break;
				   case "SkyDrive":
						result = this.model_SYC.validateToken(this.view_authorization.getJt_UrlSkyDrive());
						break;
				default:
					break;
				}
				
				if(!result)
				{
					JOptionPane.showMessageDialog (this.view_authorization,"Erreur lors de votre authentification.","SYC message",2);
					return;	
				}
				
				model_SYC.init();
				model_SYC.setDisplay_drives(true);
			//}	
		}	
	}
	
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) 
	{
		if (e.getSource()==this.view_authorization.getjLab_LiensURL())
		{
			if(Desktop.isDesktopSupported())
			{
				if(Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
				{
					URI uri;
					try 
					{
						uri = new URI(this.model_SYC.getDrivelink());
						Desktop.getDesktop().browse(uri);
					} 
					catch (URISyntaxException arg0) 
					{
						arg0.printStackTrace();
					} catch (IOException arg0) 
					{
						arg0.printStackTrace();
					}		
				}
        	}
    	}
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) 
	{
		if(e.getSource()==this.view_authorization.getjLab_LiensURL())
		{
		}
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) 
	{
		if(e.getSource()==this.view_authorization.getjLab_LiensURL())
		{
			this.view_authorization.getjLab_LiensURL().setText(model_SYC.getDrivelink());	
		}
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) 
	{
		if(e.getSource()==this.view_authorization.getjLab_LiensURL())
		{
			this.view_authorization.getjLab_LiensURL().setForeground(Color.red);
		}
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) 
	{
		
	}
}