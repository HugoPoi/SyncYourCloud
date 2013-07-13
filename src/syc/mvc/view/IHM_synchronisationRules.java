package syc.mvc.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import syc.mvc.model.Model;

public class IHM_synchronisationRules extends IHM_SYC
{
	//a supp
	private  JButton jBt_Next = new JButton("<<<>>>"); 
	
	private JButton jBt_BackToDrives = new JButton("Retour au Drive"); 
	private  JButton jBt_AddRulesSYC = new JButton("Ajouter une Règle"); 
	private JButton jBt_Remove = new JButton("Supprimer"); 
	
	//private JLabel jLab_Rules_a = new JLabel("Rayane.rahmani/doc ");
	//private JLabel jLab_Rules_b = new JLabel("Rayane.rahm/document/");
	
	//private JLabel jLab_IconeRules_a = new JLabel("Drive ");
	//private JLabel jLab_IconeRules_b = new JLabel("skyDrive");
	
	public IHM_synchronisationRules(Model aModel_SYC) 
	{
		super(aModel_SYC);
		
		this.jLab_Welcome.setText("Liste des règles de synchronisation");
		this.setTitle("Page des règles de Synchronisation");
		
		jPan3.add(jBt_AddRulesSYC);
		jPan3.add(jBt_Remove);
		jPan3.add(jBt_BackToDrives);	

		JScrollPane jScrollPane = new JScrollPane(jPan4);
		//jScrollPane.getViewport().add(jPan4, null);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    jScrollPane.setViewportBorder(new LineBorder(Color.BLACK));
	    jPan1.add(jScrollPane);
	    
		
		int i=0;
	    int numberOfDrive = 8;
		for(i=1 ;i<numberOfDrive ;i++)
	    {
			String[] s = new String[]{"Drive - ","Rayane.rahm/document"};//Sera un parametre du model via le controlleur
			String[] s1 = new String[]{"SkyDrive - ","Rayane.rahm/document"};//Sera un parametre du model via le controlleur
		      
		    JPanel jp = createJPanel(Color.WHITE, true);		       
		    jp = defineJPanelLayoutManager(jp);
		    jp=addElement(s,jp);
		    jp.add(createJButton("<<<>>>"));
		    jp=addElement(s1,jp);
		    gBC_gBLay_Level_2.fill=GridBagConstraints.HORIZONTAL;
		    gBC_gBLay_Level_2.gridx = 0;
		    gBC_gBLay_Level_2.gridy = i;
			gBC_gBLay_Level_2.gridwidth = 5;
			gBC_gBLay_Level_2.gridheight = 1;
			gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
			gBC_gBLay_Level_2.insets = new Insets(4, 0, 4, 0);
			jPan4.add(jp, gBC_gBLay_Level_2);
	    } 
	}
		
	public void displayIHM_synchronisationRules(boolean displayed)
	{	
		this.setVisible(displayed);
	}

	public JButton getjBt_BackToDrives() {
		return jBt_BackToDrives;
	}

	public void setjBt_BackToDrives(JButton jBt_BackToDrives) {
		this.jBt_BackToDrives = jBt_BackToDrives;
	}

	public JButton getjBt_AddRulesSYC() {
		return jBt_AddRulesSYC;
	}

	public void setjBt_AddRulesSYC(JButton jBt_AddRulesSYC) {
		this.jBt_AddRulesSYC = jBt_AddRulesSYC;
	}

	public JButton getjBt_Remove() {
		return jBt_Remove;
	}

	public void setjBt_Remove(JButton jBt_Remove) {
		this.jBt_Remove = jBt_Remove;
	}

	private JLabel createJLabel(String s) //trouver solution indexer bt ou les nommer
	{
		JLabel l = new JLabel(s);
		//l.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
		l.setHorizontalAlignment(JLabel.CENTER);
		 return l;
	}
	
	private JButton createJButton(String s) 
	{
		JButton jb = new JButton(s);
		jb.setHorizontalAlignment(JLabel.CENTER); 
		jb.setPreferredSize(new Dimension(70,25));
		return jb;
	}
	
	private JPanel createJPanel(Color colorPanel, Boolean autoSize) 
	{
		JPanel jp = new JPanel();
		jp.setBackground(colorPanel);
		jp.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
		if(autoSize)
	    {
			//jp.setMaximumSize(new Dimension(480,30));
			jp.setMinimumSize(new Dimension(480,30));
	    	//jp.setPreferredSize(new Dimension(480,30));
	    }
	    return jp;
	}
	
	private JPanel defineJPanelLayoutManager(JPanel jp) 
	{
		BoxLayout bxLay_buttonGroup = new BoxLayout(jp,BoxLayout.X_AXIS);
		jp.setLayout(bxLay_buttonGroup);
		return jp;
	}
	
	private JPanel addElement(String[] s, JPanel jp)
	{
		if(s.length>2)
		{
			return jp;
		}
		int i = 0;
		for(i=0;i<s.length;i++)
		{
			jp.add(createJLabel(s[i].toString()));
		}
		return jp;
	}
	
	public void update(Observable arg0, Object arg1) 
	{
		super.update(arg0, arg1);
		displayIHM_synchronisationRules(model_SYC.getDisplay_synchronisationRules());
	}
}

