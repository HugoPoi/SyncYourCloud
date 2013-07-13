package syc.mvc.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import syc.mvc.model.Model;


public class IHM_addSynchronisationRule extends IHM_SYC
{		
	private JComboBox jCbx_DriveOwner = new JComboBox(new String[] {"andy.mira","andy.mir"});
	private JComboBox jCbx_FolderSelection = new JComboBox(new String[] {"andy.mira/doc", "andy.mir/document"});
	private JComboBox jCbx_DriveOwner1 = new JComboBox(new String[] {"andy.mira","andy.mir"});
	private JComboBox jCbx_FolderSelection1 = new JComboBox(new String[] {"andy.mira/doc", "andy.mir/document"});
	
	private JButton jBt_BackToRules = new JButton("Retour au Règles"); 
	private JButton jBt_AddRulesSYC = new JButton("Ajouter"); 

	private JTextArea jTxta_info = new JTextArea("Selectionnez 2 comptes  et leurs dossiers respectifs."/*,JLabel.CENTER*/ ); 

	IHM_addSynchronisationRule(Model aModel_SYC) 
	{
		super(aModel_SYC);
		
		this.jLab_Welcome.setText("Ajoutez une règle de synchronisation");
		this.setTitle("Page de selection des règles de Synchronisation");
		
		jPan3.add(jBt_AddRulesSYC);
		jPan3.add(jBt_BackToRules);	
		
		
		JPanel jPan4a = new JPanel();
		jPan4a.setBackground(Color.WHITE);
		jPan4a.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
		jTxta_info.setBorder(null);
		//jTxta_info.setBackground(Color.BLACK);
		//jTxta_info.setForeground(Color.WHITE);
		jTxta_info.setWrapStyleWord(true);
		jTxta_info.setLineWrap(true);
		jTxta_info.setEditable(false);
		jPan4a.add(jTxta_info);
		
		gBC_gBLay_Level_2.fill=GridBagConstraints.BOTH;
		gBC_gBLay_Level_2.gridx = 0;
		gBC_gBLay_Level_2.gridy = 0;
		gBC_gBLay_Level_2.gridwidth = 1;
		gBC_gBLay_Level_2.gridheight = 2;
		gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_END;
		gBC_gBLay_Level_2.insets = new Insets(1, 1, 1, 1);
		jPan4.add(jPan4a, gBC_gBLay_Level_2);
		
		//==============================
		
		JPanel jPan4i = new JPanel();
		jPan4i.setBackground(Color.WHITE);
		jPan4i.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 0, Color.BLACK));
		jPan4i.setPreferredSize(new Dimension(300,75));
		jPan4i.add(jCbx_DriveOwner);
		
		gBC_gBLay_Level_2.gridx = 1;
		gBC_gBLay_Level_2.gridy = 0;
		gBC_gBLay_Level_2.gridwidth = 1;
		gBC_gBLay_Level_2.gridheight = 1;
		gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_END;
		gBC_gBLay_Level_2.insets = new Insets(1, 5, 5, 0);
		jPan4.add(jPan4i, gBC_gBLay_Level_2);
		  
		
		JPanel jPan4j = new JPanel();
		jPan4j.setBackground(Color.WHITE);
		jPan4j.setBorder(BorderFactory.createMatteBorder(3, 1, 3, 5, Color.BLACK));
		jPan4j.setPreferredSize(new Dimension(300,75));
		jPan4j.add(jCbx_FolderSelection);
					       
		gBC_gBLay_Level_2.gridx = 2;
		gBC_gBLay_Level_2.gridy = 0;
		gBC_gBLay_Level_2.gridwidth = 1;
		gBC_gBLay_Level_2.gridheight = 1;
		gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
		gBC_gBLay_Level_2.insets = new Insets(1, 0, 5, 1);
		jPan4.add(jPan4j, gBC_gBLay_Level_2);
		
		//============================================================
		
		jPan4i = new JPanel();
		jPan4i.setBackground(Color.WHITE);
		jPan4i.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 0, Color.BLACK));
		jPan4i.setPreferredSize(new Dimension(300,75));
		jPan4i.add(jCbx_DriveOwner1);
		
		gBC_gBLay_Level_2.gridx = 1;
		gBC_gBLay_Level_2.gridy = 1;
		gBC_gBLay_Level_2.gridwidth = 1;
		gBC_gBLay_Level_2.gridheight = 1;
		gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_END;
		gBC_gBLay_Level_2.insets = new Insets(5, 5, 1, 0);
		jPan4.add(jPan4i, gBC_gBLay_Level_2);
		  
		
		jPan4j = new JPanel();
		jPan4j.setBackground(Color.WHITE);
		jPan4j.setBorder(BorderFactory.createMatteBorder(3, 1, 3, 5, Color.BLACK));
		jPan4j.setPreferredSize(new Dimension(300,75));
		jPan4j.add(jCbx_FolderSelection1);
					       
		//gBC_gBLay_Level_2.fill=GridBagConstraints.HORIZONTAL;
		gBC_gBLay_Level_2.gridx = 2;
		gBC_gBLay_Level_2.gridy = 1;
		gBC_gBLay_Level_2.gridwidth = 1;
		gBC_gBLay_Level_2.gridheight = 1;
		gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
		gBC_gBLay_Level_2.insets = new Insets(5, 0, 1, 1);
		jPan4.add(jPan4j, gBC_gBLay_Level_2);			
	}
	
	public void displayIHM_addSynchronisationRule(boolean displayed)
	{	
		this.setVisible(displayed);
	}
	
	public JComboBox getjCbx_DriveOwner() {
		return jCbx_DriveOwner;
	}

	public void setjCbx_DriveOwner(JComboBox jCbx_DriveOwner) {
		this.jCbx_DriveOwner = jCbx_DriveOwner;
	}

	public JComboBox getjCbx_FolderSelection() {
		return jCbx_FolderSelection;
	}

	public void setjCbx_FolderSelection(JComboBox jCbx_FolderSelection) {
		this.jCbx_FolderSelection = jCbx_FolderSelection;
	}

	public JComboBox getjCbx_DriveOwner1() {
		return jCbx_DriveOwner1;
	}

	public void setjCbx_DriveOwner1(JComboBox jCbx_DriveOwner1) {
		this.jCbx_DriveOwner1 = jCbx_DriveOwner1;
	}

	public JComboBox getjCbx_FolderSelection1() {
		return jCbx_FolderSelection1;
	}

	public void setjCbx_FolderSelection1(JComboBox jCbx_FolderSelection1) {
		this.jCbx_FolderSelection1 = jCbx_FolderSelection1;
	}

	public JButton getjBt_BackToRules() {
		return jBt_BackToRules;
	}

	public void setjBt_BackToRules(JButton jBt_BackToRules) {
		this.jBt_BackToRules = jBt_BackToRules;
	}

	public JButton getjBt_AddRulesSYC() {
		return jBt_AddRulesSYC;
	}

	public void setjBt_AddRulesSYC(JButton jBt_AddRulesSYC) {
		this.jBt_AddRulesSYC = jBt_AddRulesSYC;
	}

	public void update(Observable arg0, Object arg1) 
	{
		super.update(arg0, arg1);
		displayIHM_addSynchronisationRule(model_SYC.getDisplay_addSynchronisationRule());
	}
}