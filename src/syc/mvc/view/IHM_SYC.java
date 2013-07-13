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
import java.awt.GridBagLayoutInfo;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import syc.mvc.model.Model;

public abstract class IHM_SYC extends JFrame implements Observer
{
	protected Model model_SYC;
	
	protected Picture icone_SYC = new Picture();

	protected JLabel jLab_Welcome = new JLabel("Description",JLabel.LEFT);
	protected JLabel jLabfootPage = new JLabel("Mirabel Andy - Mokhtar Rahmani Rayane - Poissonnet Hugo, 3AL - Copyright ©2013",JLabel.RIGHT); //alt 0169
	
	protected BorderLayout bLay_Level_0 = new BorderLayout();
	protected BorderLayout bLay_Level_1 = new BorderLayout();
	protected BorderLayout bLay_Level_2 = new BorderLayout();
	
	protected FlowLayout fLay_Level_2 = new FlowLayout();	
	
	protected GridBagLayout gBLay_Level_2 = new GridBagLayout();
	protected GridBagConstraints gBC_gBLay_Level_2 = new GridBagConstraints();
	
	protected JPanel jPan0 = new JPanel();
	protected JPanel jPan1 = new JPanel();
	protected JPanel jPan2 = new JPanel(); 
	protected JPanel jPan3 = new JPanel();
	protected JPanel jPan4 = new JPanel();
	protected JPanel jPanFoot = new JPanel();
	
	protected Font police = new Font("TimesRoman", Font.BOLD, 12);
	protected Font policeSmall = new Font("TimesRoman", Font.BOLD, 9);
	protected Font police1 = new Font("TimesRoman", Font.ITALIC, 10);
	
	public IHM_SYC(Model aModel_SYC) 
	{
		
		model_SYC = aModel_SYC;
		this.model_SYC.addObserver(this);
		
		this.setTitle("Page MERE ");
		this.setBackground(Color.BLUE);
		this.setSize(550,275);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.setContentPane(jPan0);
		jPan0.setLayout(bLay_Level_0);
		jPan0.add(jPan1,bLay_Level_0.CENTER);
		jPan0.add(jPanFoot,bLay_Level_0.SOUTH);
		
		jPanFoot.setBackground(Color.BLACK);
		jPanFoot.add(jLabfootPage);
		
		jPan1.setBackground(Color.LIGHT_GRAY);
		jPan1.setLayout(bLay_Level_1);
		jPan1.add(jPan2,bLay_Level_1.NORTH);
		jPan1.add(jPan4,bLay_Level_1.CENTER);
		jPan1.add(jPan3,bLay_Level_1.SOUTH);
		
		jPan2.setBackground(Color.WHITE);
		jPan2.setLayout(bLay_Level_2);
		jPan2.add(jLab_Welcome,bLay_Level_2.CENTER);
		jPan2.add(icone_SYC,bLay_Level_2.EAST);
		
		icone_SYC.setFichierImage( Picture.createFichierImage(model_SYC.getPathImageSyc(),model_SYC.getLogo_SYC()));
		icone_SYC.setPreferredSize(new Dimension(50,46));
		
		jPan3.setBackground(Color.DARK_GRAY);
		jPan3.setLayout(fLay_Level_2);
		fLay_Level_2.setAlignment(fLay_Level_2.CENTER);
		
		jPan4.setBackground(Color.LIGHT_GRAY);
		jPan4.setLayout(gBLay_Level_2);
		//gBLay_Level_2.layoutContainer(jPan4);
		jPan4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		jLab_Welcome.setFont(police);
		Border engraved = BorderFactory.createEtchedBorder(Color.WHITE, Color.BLACK);
		jLab_Welcome.setBorder(BorderFactory.createTitledBorder(engraved, "", TitledBorder.LEFT, TitledBorder.LEFT));
		//jLab_Welcome.setBorder(BorderFactory.createMatteBorder(2,1, 2, 1, Color.BLACK));
		
		jLabfootPage.setFont(police1);
		jLabfootPage.setForeground(Color.WHITE);
		
		this.setVisible(false);
	}

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
