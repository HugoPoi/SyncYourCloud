package syc.mvc.controller;
import syc.mvc.view.IHM_account;
import syc.mvc.view.IHM_home;
import syc.mvc.model.Connexion;
import syc.mvc.model.Model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class Controller_account implements ActionListener
{	
	private Model model_SYC;
    private IHM_account view_account;
    
    public Controller_account(Model aModel_SYC, IHM_account aView_account) 
    {
		this.model_SYC = aModel_SYC;
		this.view_account = aView_account;
		this.ControllerActionListenerForComponent(view_account.getContentPane());
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
			}
		}	
	}
    
    @Override
	public void actionPerformed(ActionEvent e) 
	{
    	String login = this.view_account.getTxt_Login().getText();
    	String pwd = this.view_account.getTxt_Password().getText();
    	
		if(e.getSource()==this.view_account.getjBt_Account())
		{
			if(login.isEmpty() || pwd.isEmpty() || this.view_account.getTxt_PasswordBis().getText().isEmpty())
			{
				JOptionPane.showMessageDialog (this.view_account,"Renseignez les champs pour vous connecter","SYC message",2);
				return;
			}
			
			if(!pwd.equals(this.view_account.getTxt_PasswordBis().getText()))
			{
				JOptionPane.showMessageDialog (this.view_account,"Les mots de passes ne correspondent pas","SYC message",2);
				return;
			}
			
			if(!Connexion.CheckLogin(login))
			{
				JOptionPane.showMessageDialog (this.view_account,"Login existant","SYC message",2);
				return;
			}
			
			if(Connexion.CreateIdentifiant(login,pwd))
			{
				model_SYC.init();
				model_SYC.setCurrentConfFile(Connexion.fileConf);
				model_SYC.setDisplay_drives(true);
			}
			else
				JOptionPane.showMessageDialog (this.view_account,"Une erreur est intervenue. Contactez l'administrateur.","SYC message",2);
				
		}
			
		if(e.getSource()==this.view_account.getjBt_Cancel())
		{
			//Back to IHM_home			
			model_SYC.init();
			model_SYC.setDisplay_home(true);
		}	
	}
}