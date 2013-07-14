package syc.mvc.view;

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
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.print.DocFlavor.STRING;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import syc.mvc.model.Model;

public class IHM_home extends IHM_SYC 
{	
	private JButton jBt_Account = new JButton("Creer Compte"); 
	private JButton jBt_Connexion = new JButton("Connexion");
	private JButton jBt_Exit = new JButton("Quitter"); 
	
	private TextField txt_Login = new TextField();
	private JPasswordField txt_Password = new JPasswordField('*');
	
	private JLabel jLab_Login = new JLabel("Login :"/*model_SYC.gettestText()*/);
	private JLabel jLab_Password = new JLabel("Mot de passe : ");
	
	public IHM_home(Model aModel_SYC)
	{
		super(aModel_SYC);		
		
		this.jLab_Welcome.setText("Bienvenue sur SyncYourCloud");
		this.setTitle("Page de démarrage");
		
		jPan3.add(getjBt_Connexion());
		jPan3.add(getjBt_Account());	
		jPan3.add(getjBt_Exit());
		
		JPanel jPan4a = new JPanel();
        jPan4a.setBackground(Color.WHITE);
        jPan4a.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        jPan4a.setPreferredSize(new Dimension(125,32));
        jPan4a.add(getjLab_Login());
		gBC_gBLay_Level_2.gridx = 0;
		gBC_gBLay_Level_2.gridy = 0;
		gBC_gBLay_Level_2.gridwidth = 1;
		gBC_gBLay_Level_2.gridheight = 1;
		gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
		gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);	//top,left,bottom,right
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
        jPan4b.setPreferredSize(new Dimension(125,32));
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
       
        txt_Login.setPreferredSize(new Dimension(125,25));
		txt_Password.setPreferredSize(new Dimension(125,30));
		txt_Login.setMinimumSize(new Dimension(125,25));
		txt_Password.setMinimumSize(new Dimension(125,30));
		txt_Login.setMaximumSize(new Dimension(125,25));
		txt_Password.setMaximumSize(new Dimension(125,30));
		
		txt_Login.setFont(police);
		txt_Password.setFont(police);

		jLab_Login.setFont(police);
		jLab_Password.setFont(police);
		
		jLab_Login.setMinimumSize(jLab_Password.getPreferredSize());
		
		jBt_Account.setFont(police);
		jBt_Connexion.setFont(police);
		jBt_Exit.setFont(police);
	}
	
	public void displayIHM_home(boolean displayed)
	{	
		this.setVisible(displayed);
	}
	
	public JPasswordField getTxt_Password() {
		return txt_Password;
	}

	public void setTxt_Password(JPasswordField txt_Password) {
		this.txt_Password = txt_Password;
	}

	public TextField getTxt_Login() {
		return txt_Login;
	}

	public void setTxt_Login(TextField txt_Login) {
		this.txt_Login = txt_Login;
	}
	
	public JButton getjBt_Connexion() {
		return jBt_Connexion;
	}

	public void setjBt_Connexion(JButton jBt_Connexion) {
		this.jBt_Connexion = jBt_Connexion;
	}

	public JButton getjBt_Account() {
		return jBt_Account;
	}

	public void setjBt_Account(JButton jBt_Account) {
		this.jBt_Account = jBt_Account;
	}

	public JButton getjBt_Exit() {
		return jBt_Exit;
	}

	public void setjBt_Exit(JButton jBt_Exit) {
		this.jBt_Exit = jBt_Exit;
	}
	
	public JLabel getjLab_Login() {
		return jLab_Login;
	}

	public void setjLab_Login(JLabel jLab_Login) {
		this.jLab_Login = jLab_Login;
	}
	
	public void update(Observable arg0, Object arg1) 
	{
		super.update(arg0, arg1);
		displayIHM_home(model_SYC.getDisplay_home());
	}	
}