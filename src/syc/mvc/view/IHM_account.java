package syc.mvc.view;
import syc.mvc.model.Model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class IHM_account extends IHM_SYC
{	
	private  JButton jBt_Account = new JButton("Confirmer"); 
	private JButton jBt_Cancel = new JButton("Annuler");
	
	private TextField txt_Login = new TextField();
	private JPasswordField txt_Password = new JPasswordField();
	private JPasswordField txt_PasswordBis = new JPasswordField();
	
	private JLabel jLab_Login = new JLabel("Nom d'utilisateur : ");
	private JLabel jLab_Password = new JLabel("Mot de passe : ");
	private JLabel jLab_PasswordBis = new JLabel("Vérification du mot de passe : ");
	
	public IHM_account(Model aModel_SYC)
	{
		super(aModel_SYC);
		
		this.jLab_Welcome.setText("Entrez les informations demandees pour obtenir un compte SYC");
		this.setTitle("Page de creation de compte");
		
		jPan3.add(getjBt_Cancel());
		jPan3.add(getjBt_Account());
		
		JPanel jPan4a = new JPanel();
        jPan4a.setBackground(Color.WHITE);
        jPan4a.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        jPan4a.setPreferredSize(new Dimension(170,32));
        jPan4a.add(jLab_Login);
		gBC_gBLay_Level_2.gridx = 0;
		gBC_gBLay_Level_2.gridy = 0;
		gBC_gBLay_Level_2.gridwidth = 1;
		gBC_gBLay_Level_2.gridheight = 1;
		gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
		gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
		jPan4.add(jPan4a, gBC_gBLay_Level_2);
         
        gBC_gBLay_Level_2.gridx = 1;
        gBC_gBLay_Level_2.gridy = 0;
        gBC_gBLay_Level_2.gridwidth = 2;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(getTxt_Login(), gBC_gBLay_Level_2);
        
        JPanel jPan4b = new JPanel();
        jPan4b.setBackground(Color.WHITE);
        jPan4b.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        jPan4b.setPreferredSize(new Dimension(170,32));
        jPan4b.add(jLab_Password);
        gBC_gBLay_Level_2.gridx = 0;
        gBC_gBLay_Level_2.gridy = 1;
        gBC_gBLay_Level_2.gridwidth = 1;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(jPan4b, gBC_gBLay_Level_2);
         
        gBC_gBLay_Level_2.gridx = 1;
        gBC_gBLay_Level_2.gridy = 1;
        gBC_gBLay_Level_2.gridwidth = 2;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(getTxt_Password(), gBC_gBLay_Level_2);   
        
        JPanel jPan4c = new JPanel();
        jPan4c.setBackground(Color.WHITE);
        jPan4c.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        jPan4c.setPreferredSize(new Dimension(170,32));
        jPan4c.add(jLab_PasswordBis);
        gBC_gBLay_Level_2.gridx = 0;
        gBC_gBLay_Level_2.gridy = 2;
        gBC_gBLay_Level_2.gridwidth = 1;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(jPan4c, gBC_gBLay_Level_2);
         
        gBC_gBLay_Level_2.gridx = 1;
        gBC_gBLay_Level_2.gridy = 2;
        gBC_gBLay_Level_2.gridwidth = 2;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(getTxt_PasswordBis(), gBC_gBLay_Level_2); 
        
        txt_Login.setPreferredSize(new Dimension(125,25));
		txt_Password.setPreferredSize(new Dimension(125,30));
		txt_PasswordBis.setPreferredSize(new Dimension(125,30));
		
		txt_Login.setMinimumSize(new Dimension(125,25));
		txt_Password.setMinimumSize(new Dimension(125,30));
		txt_PasswordBis.setMinimumSize(new Dimension(125,30));
		
		txt_Login.setMaximumSize(new Dimension(125,25));
		txt_Password.setMaximumSize(new Dimension(125,30));
		txt_PasswordBis.setMaximumSize(new Dimension(125,30));
		
		txt_Login.setFont(police);
		txt_Password.setFont(police);
		txt_PasswordBis.setFont(police);
		
		jLab_Login.setFont(police);
		jLab_Password.setFont(police);
		jLab_PasswordBis.setFont(police);
		
		jBt_Account.setFont(police);
		jBt_Cancel.setFont(police);
	}
	
	public void displayIHM_account(boolean displayed)
	{	
		this.setVisible(displayed);
	}
	
	public JButton getjBt_Account() 
	{
		return jBt_Account;
	}

	public void setjBt_Account(JButton jBt_Account) 
	{
		this.jBt_Account = jBt_Account;
	}

	public TextField getTxt_Login() 
	{
		return txt_Login;
	}

	public void setTxt_Login(TextField txt_Login) 
	{
		this.txt_Login = txt_Login;
	}

	public JPasswordField getTxt_PasswordBis() 
	{
		return txt_PasswordBis;
	}

	public void setTxt_PasswordBis(JPasswordField txt_PasswordBis) 
	{
		this.txt_PasswordBis = txt_PasswordBis;
	}

	public JPasswordField getTxt_Password() 
	{
		return txt_Password;
	}

	public void setTxt_Password(JPasswordField txt_Password) 
	{
		this.txt_Password = txt_Password;
	}

	public JButton getjBt_Cancel() 
	{
		return jBt_Cancel;
	}

	public void setjBt_Cancel(JButton jBt_Cancel) 
	{
		this.jBt_Cancel = jBt_Cancel;
	}

	public void update(Observable arg0, Object arg1) 
	{
		super.update(arg0, arg1);
		displayIHM_account(model_SYC.getDisplay_account());
	}
}