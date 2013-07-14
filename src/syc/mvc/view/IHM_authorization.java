package syc.mvc.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.net.URI;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import api.DriveDropBox;

import syc.mvc.model.Model;

public class IHM_authorization extends IHM_SYC
{
	private JButton jBt_AddDriveAccount = new JButton("Ajouter Compte"); 
	private JButton jBt_Cancel = new JButton("Annuler");
	
	private JLabel jLab_info = null;
	private JLabel jLab_LiensURL = null; 
	private JLabel jLab_info1 = null;
	private JPanel jPan4b = null;
	
	
	public IHM_authorization(Model aModel_SYC) 
	{
		super(aModel_SYC);
		drawBase();
	}
	
	public void drawBase(){
		
		jBt_AddDriveAccount = new JButton("Ajouter Compte"); 
		jBt_Cancel = new JButton("Annuler");
		
		jLab_info = new JLabel("Pour ajouter le compte aller sur le lien ci dessous :");
		jLab_LiensURL = new JLabel(""); 
		jLab_info1 = new JLabel("Puis cliquer sur le bouton ajouter");
		
		
		jPan3.add(jBt_AddDriveAccount);
		jPan3.add(jBt_Cancel);
		jLab_LiensURL = new JLabel(model_SYC.getDrivelink());
		this.jLab_Welcome.setText("Proc�dure d'ajout de compte CloudDrive suivez les instructions");
		this.setTitle("Page d'authentification et d'autorisation");
		
		JPanel jPan4a = new JPanel();
        jPan4a.setBackground(Color.WHITE);
        jPan4a.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.DARK_GRAY));
        jPan4a.add(jLab_info);
        gBC_gBLay_Level_2.gridx = 0;
		gBC_gBLay_Level_2.gridy = 0;
		gBC_gBLay_Level_2.gridwidth = 1;
		gBC_gBLay_Level_2.gridheight = 1;
		gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
		gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
		jLab_LiensURL.setHorizontalAlignment(JLabel.CENTER);
		jPan4.add(jPan4a, gBC_gBLay_Level_2);
		
		jPan4b = new JPanel();
        jPan4b.setBackground(Color.WHITE);
        jLab_LiensURL.setForeground(Color.BLUE);
        jPan4b.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        jPan4b.add(jLab_LiensURL);
		gBC_gBLay_Level_2.gridx = 0;
		gBC_gBLay_Level_2.gridy = 1;
		gBC_gBLay_Level_2.gridwidth = 1;
		gBC_gBLay_Level_2.gridheight = 2;
		gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
		gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
		jPan4.add(jPan4b, gBC_gBLay_Level_2);

		JPanel jPan4c = new JPanel();
        jPan4c.setBackground(Color.WHITE);
        jPan4c.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.DARK_GRAY));
        jPan4c.add(jLab_info1);
		gBC_gBLay_Level_2.gridx = 0;
		gBC_gBLay_Level_2.gridy = 3;
		gBC_gBLay_Level_2.gridwidth = 1;
		gBC_gBLay_Level_2.gridheight = 1;
		gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
		gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
		jPan4.add(jPan4c, gBC_gBLay_Level_2);
	}
	public void displayIHM_authorization(boolean displayed)
	{	
		if(displayed){
			jLab_LiensURL.setText(model_SYC.getDrivelink());
		}
		this.setVisible(displayed);
	}
	
	public JButton getjBt_AddDriveAccount() {
		return jBt_AddDriveAccount;
	}

	public void setjBt_AddDriveAccount(JButton jBt_AddDriveAccount) {
		this.jBt_AddDriveAccount = jBt_AddDriveAccount;
	}

	public JButton getjBt_Cancel() {
		return jBt_Cancel;
	}

	public void setjBt_Cancel(JButton jBt_Cancel) {
		this.jBt_Cancel = jBt_Cancel;
	}

	public JLabel getjLab_LiensURL() {
		return jLab_LiensURL;
	}

	public void setjLab_LiensURL(JLabel jLab_LiensURL) {
		this.jLab_LiensURL = jLab_LiensURL;
	}

	public void update(Observable arg0, Object arg1) 
	{
		super.update(arg0, arg1);
		displayIHM_authorization(model_SYC.getDisplay_authorization());
	}
}