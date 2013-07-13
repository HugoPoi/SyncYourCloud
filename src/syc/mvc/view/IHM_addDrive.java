package syc.mvc.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import syc.mvc.model.Model;


public class IHM_addDrive extends IHM_SYC
{
	private JComboBox jCbx_DriveList = new JComboBox(new String[] {"DropBox", "GoogleDrive", "SkyDrive","OwnDrive"});
	
	private JButton jBt_Cancel = new JButton("Annuler"); 
	private  JButton jBt_AddCloudDrive = new JButton("Ajouter Drive"); 
	
	private JLabel jLab_info = new JLabel("Veuillez selectionner un type de Drive pour procéder a son ajout :");
	
	public IHM_addDrive(Model aModel_SYC) 
	{
		super(aModel_SYC);
		
		this.jLab_Welcome.setText("Selection du type de Drive");
		this.setTitle("Page de selection de CloudDrives");
		
		jBt_AddCloudDrive.setBackground(Color.WHITE);
			
		jPan3.add(jBt_AddCloudDrive);
		jPan3.add(jBt_Cancel);
		
		JPanel jPan4a = new JPanel();
        jPan4a.setBackground(Color.WHITE);
        jPan4a.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        jPan4a.add(jLab_info);
        gBC_gBLay_Level_2.gridx = 0;
		gBC_gBLay_Level_2.gridy = 0;
		gBC_gBLay_Level_2.gridwidth = 1;
		gBC_gBLay_Level_2.gridheight = 1;
		gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
		gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
		jLab_info.setHorizontalAlignment(JLabel.CENTER);
		jPan4.add(jPan4a, gBC_gBLay_Level_2);

		
		JPanel jPan4b = new JPanel();
        jPan4b.setBackground(Color.WHITE);
        jPan4b.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        jCbx_DriveList.setPreferredSize(new Dimension(350,30));
        jPan4b.add(jCbx_DriveList);
		gBC_gBLay_Level_2.gridx = 0;
		gBC_gBLay_Level_2.gridy = 1;
		gBC_gBLay_Level_2.gridwidth = 1;
		gBC_gBLay_Level_2.gridheight = 1;
		gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
		gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
		jPan4.add(jPan4b, gBC_gBLay_Level_2);
	}
	
	public void displayIHM_addDrive(boolean displayed)
	{	
		this.setVisible(displayed);
	}

	public JComboBox getjCbx_DriveList() {
		return jCbx_DriveList;
	}

	public void setjCbx_DriveList(JComboBox jCbx_DriveList) {
		this.jCbx_DriveList = jCbx_DriveList;
	}

	public JButton getjBt_Cancel() {
		return jBt_Cancel;
	}

	public void setjBt_Cancel(JButton jBt_Cancel) {
		this.jBt_Cancel = jBt_Cancel;
	}

	public JButton getjBt_AddCloudDrive() {
		return jBt_AddCloudDrive;
	}

	public void setjBt_AddCloudDrive(JButton jBt_AddCloudDrive) {
		this.jBt_AddCloudDrive = jBt_AddCloudDrive;
	}
	
	public void update(Observable arg0, Object arg1) 
	{
		super.update(arg0, arg1);
		displayIHM_addDrive(model_SYC.getDisplay_addDrive());
	}
}