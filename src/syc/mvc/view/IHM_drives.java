package syc.mvc.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import api.DriveDropBox;
import api.DriveGoogle;
import api.DriveSkyDrive;
import api.IntDrive;

import syc.mvc.controller.Controller_drives;
import syc.mvc.model.Model;
import syc.mvc.model.Singleton_Connexion;

public class IHM_drives extends IHM_SYC 
{
	private JButton jBt_Deconnexion = new JButton("Deconnexion"); 
	private  JButton jBt_AddCloudDrive = new JButton("Ajouter un CloudDrive"); 
	private JButton jBt_RulesSYC = new JButton("Regles de Synchronisations"); 
	
	private ArrayList<JButton> tabBt_SeeFile = new  ArrayList<JButton>(); //model_SYC.drives.size()
	private ArrayList<JButton> tabBt_Remove = new  ArrayList<JButton>(); //model_SYC.drives.size()
	private ArrayList<JButton> tabBt_Edit = new  ArrayList<JButton>(); //model_SYC.drives.size()
	
	//private JLabel jLab_DriveBrand = new JLabel("Drive Icone ");
	//private JLabel jLab_DriveOwner = new JLabel(" Propi�taire ");
	//private JLabel jLab_DriveMemoryUsed = new JLabel(" Espace Utilis� ");
	//private JLabel jLab_DriveMemoryCapacity = new JLabel("Capacit� totale");
	
	private ImageIcon logoDropBox = null;
	private ImageIcon logoSkyDrive = null;
	private ImageIcon logoGoogleDrive = null;
	
	private Controller_drives controllerOfThis;
	
	public void setControllerOfThis(Controller_drives controllerOfThis) {
		this.controllerOfThis = controllerOfThis;
	}

	public IHM_drives(Model aModel_SYC)
	{
		super(aModel_SYC);
		init_icon();
		
		this.jLab_Welcome.setText("Liste de vos drives");
		this.setTitle("Page des CloudDrives");
		
		jPan3.add(jBt_AddCloudDrive);
		jPan3.add(jBt_RulesSYC);
		jPan3.add(jBt_Deconnexion);
		
		JScrollPane jScrollPane = new JScrollPane(jPan4);
		//jScrollPane.getViewport().add(jPan4, null);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    jScrollPane.setViewportBorder(new LineBorder(Color.BLACK));
	    jPan1.add(jScrollPane);
		
		JPanel jPan4a = new JPanel();
        jPan4a.setBackground(Color.WHITE);
        jPan4a.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        //jPan4a.add(jLab_DriveBrand);
        gBC_gBLay_Level_2.gridx = 0;
		gBC_gBLay_Level_2.gridy = 0;
		gBC_gBLay_Level_2.gridwidth = 1;
		gBC_gBLay_Level_2.gridheight = 1;
		gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
		gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
		//jLab_DriveBrand.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		//jLab_DriveBrand.setHorizontalAlignment(JLabel.CENTER);
		//jPan4.add(jPan4a, gBC_gBLay_Level_2);
		
		JPanel jPan4b = new JPanel();
        jPan4b.setBackground(Color.WHITE);
        jPan4b.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        //jPan4b.add(jLab_DriveOwner);
        gBC_gBLay_Level_2.gridx = 1;
		gBC_gBLay_Level_2.gridy = 0;
		gBC_gBLay_Level_2.gridwidth = 1;
		gBC_gBLay_Level_2.gridheight = 1;
		gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
		gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
		//jLab_DriveOwner.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        //jLab_DriveOwner.setHorizontalAlignment(JLabel.CENTER);
		//jPan4.add(jPan4b, gBC_gBLay_Level_2);
		
		JPanel jPan4c = new JPanel();
		jPan4c.setBackground(Color.WHITE);
        jPan4c.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        //jPan4c.add(jLab_DriveMemoryUsed);
        gBC_gBLay_Level_2.gridx = 2;
        gBC_gBLay_Level_2.gridy = 0;
        gBC_gBLay_Level_2.gridwidth = 1;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        //jLab_DriveMemoryUsed.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        //jLab_DriveMemoryUsed.setHorizontalAlignment(JLabel.CENTER);
        //jPan4.add(jPan4c, gBC_gBLay_Level_2);
        
        //gBC_gBLay_Level_2.fill=GridBagConstraints.HORIZONTAL;
        JPanel jPan4d = new JPanel();
		jPan4d.setBackground(Color.WHITE);
        jPan4d.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        //jPan4d.add(jLab_DriveMemoryCapacity);
        gBC_gBLay_Level_2.gridx = 3;
        gBC_gBLay_Level_2.gridy = 0;
        gBC_gBLay_Level_2.gridwidth = 1;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        //jLab_DriveMemoryCapacity.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        //jLab_DriveMemoryCapacity.setHorizontalAlignment(JLabel.CENTER);
        //jPan4.add(jPan4d, gBC_gBLay_Level_2);
        
       //=================ModifierApres========================================
       
	}
	
	public void displayIHM_drives(boolean displayed)
	{	
		if(displayed)
		{	
			this.jPan4.removeAll();
			this.repaint();
			int i=0;
			tabBt_SeeFile.clear();
			tabBt_Edit.clear();
			tabBt_Remove.clear();
		       Iterator<IntDrive> itDrive = model_SYC.drives.iterator();	
		       while (itDrive.hasNext()) 
		       {
					IntDrive intDrive = (IntDrive) itDrive.next();
					System.out.println( i +" "+intDrive.toString());
			       
			       JPanel jp = createJPanel(Color.WHITE,true);
			       jp.setLayout(new BorderLayout());
			       if(intDrive instanceof DriveDropBox) jp.add(new JLabel(logoDropBox),BorderLayout.WEST);
			       if(intDrive instanceof DriveSkyDrive) jp.add(new JLabel(logoSkyDrive),BorderLayout.WEST);
			       if(intDrive instanceof DriveGoogle) jp.add(new JLabel(logoGoogleDrive),BorderLayout.WEST);
			       
			       jp.add(new JLabel(intDrive.getId()+" "+intDrive.getNiceName()+" "+intDrive.getNiceSize()),BorderLayout.EAST);
			       
			       gBC_gBLay_Level_2.ipadx = 0;
			       gBC_gBLay_Level_2.ipady = 0;
			       gBC_gBLay_Level_2.fill=GridBagConstraints.HORIZONTAL;
			       gBC_gBLay_Level_2.gridx = 0;
			       gBC_gBLay_Level_2.gridy = i;
			       gBC_gBLay_Level_2.gridwidth = 4;
			       gBC_gBLay_Level_2.gridheight = 1;
			       gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
			       gBC_gBLay_Level_2.insets = new Insets(0, 0, 0, 0);
			       jPan4.add(jp, gBC_gBLay_Level_2);
			       
			       JPanel jp1=createJPanel(Color.LIGHT_GRAY,false);
			       jp1=defineJPanelLayoutManager(jp1);
			       //gBC_gBLay_Level_2.fill=GridBagConstraints.BOTH;
			       gBC_gBLay_Level_2.gridx = 4;
			       gBC_gBLay_Level_2.gridy = i;
			       gBC_gBLay_Level_2.gridwidth = GridBagConstraints.REMAINDER;
			       gBC_gBLay_Level_2.gridheight = 1;
			       //gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_END;
			       gBC_gBLay_Level_2.insets = new Insets(1, 1, 1, 1);
			       jPan4.add(jp1, gBC_gBLay_Level_2);
			       jp1.setPreferredSize(new Dimension(100,80));
			       gBC_gBLay_Level_2.fill=GridBagConstraints.HORIZONTAL;
			   	
			       JButton jb = createJButton("Voir fichier");
			       //jb.setFont(policeSmall);
			       tabBt_SeeFile.add(jb);
			       jp1.add(jb);
			       
			       jb = createJButton("modifier");
			       //jb.setFont(policeSmall);
			       tabBt_Edit.add(jb);
			       jp1.add(jb);
			       
			       jb = createJButton("Supprimer");
			       //jb.setFont(policeSmall);
			       tabBt_Remove.add(jb);
			       jp1.add(jb);
			       
			       i++;
		       }
		      controllerOfThis.ControllerActionListenerForComponent(this.jPan4); 
		}
		this.setVisible(displayed);
	}
		
	public ArrayList<JButton> getTabBt_SeeFile() {
		return tabBt_SeeFile;
	}

	public void setTabBt_SeeFile(ArrayList<JButton> tabBt_SeeFile) {
		this.tabBt_SeeFile = tabBt_SeeFile;
	}

	public ArrayList<JButton> getTabBt_Remove() {
		return tabBt_Remove;
	}

	public void setTabBt_Remove(ArrayList<JButton> tabBt_Remove) {
		this.tabBt_Remove = tabBt_Remove;
	}

	public ArrayList<JButton> getTabBt_Edit() {
		return tabBt_Edit;
	}

	public void setTabBt_Edit(ArrayList<JButton> tabBt_Edit) {
		this.tabBt_Edit = tabBt_Edit;
	}

	public JButton getjBt_Deconnexion() {
		return jBt_Deconnexion;
	}

	public void setjBt_Deconnexion(JButton jBt_Deconnexion) {
		this.jBt_Deconnexion = jBt_Deconnexion;
	}

	public JButton getjBt_AddCloudDrive() {
		return jBt_AddCloudDrive;
	}

	public void setjBt_AddCloudDrive(JButton jBt_AddCloudDrive) {
		this.jBt_AddCloudDrive = jBt_AddCloudDrive;
	}

	public JButton getjBt_RulesSYC() {
		return jBt_RulesSYC;
	}

	public void setjBt_RulesSYC(JButton jBt_RulesSYC) {
		this.jBt_RulesSYC = jBt_RulesSYC;
	}

	private JButton createJButton(String s) 
	{
		JButton jb = new JButton(s);
		jb.setHorizontalAlignment(JLabel.CENTER); 
		jb.setPreferredSize(new Dimension(150,20));
		return jb;
	}
	
	private JPanel createJPanel(Color colorPanel, Boolean autoSize) 
	{
		JPanel jp = new JPanel();
		jp.setBackground(colorPanel);
		jp.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
	    if(autoSize)
	    {
	    	jp.setPreferredSize(new Dimension(400,80));
	    }
	    return jp;
	}
	
	private JPanel defineJPanelLayoutManager(JPanel jp) 
	{
		BoxLayout bxLay_buttonGroup = new BoxLayout(jp,BoxLayout.Y_AXIS);
		jp.setLayout(bxLay_buttonGroup);
		return jp;
	}
	
	
	public void update(Observable arg0, Object arg1) 
	{
		super.update(arg0, arg1);
		displayIHM_drives(model_SYC.getDisplay_drives());
	}
	
	private void init_icon(){
		try {
			logoDropBox = new ImageIcon(ImageIO.read(new File(Model.getPathImageSyc()+model_SYC.logo_Dropbox)));
			logoSkyDrive = new ImageIcon(ImageIO.read(new File(Model.getPathImageSyc()+model_SYC.logo_SkyDrive)));
			logoGoogleDrive = new ImageIcon(ImageIO.read(new File(Model.getPathImageSyc()+model_SYC.logo_Google)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
