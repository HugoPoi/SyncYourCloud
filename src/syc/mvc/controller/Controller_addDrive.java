package syc.mvc.controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import syc.mvc.model.Model;
import syc.mvc.view.IHM_addDrive;
import syc.mvc.view.IHM_drives;


public class Controller_addDrive implements ActionListener 
{
	private Model model_SYC;
    private IHM_addDrive view_addDrive;
    
    public Controller_addDrive(Model aModel_SYC, IHM_addDrive aView_addDrive) 
    {
		this.model_SYC = aModel_SYC;
		this.view_addDrive = aView_addDrive;
		this.ControllerActionListenerForComponent(view_addDrive.getContentPane());
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
		if(e.getSource()==this.view_addDrive.getjBt_Cancel())
		{
			//Back to IHM_drives
			model_SYC.init();
			model_SYC.setDisplay_drives(true);
		}	
		
		if(e.getSource()==this.view_addDrive.getjBt_AddCloudDrive())
		{
			if(this.view_addDrive.getjCbx_DriveList().getSelectedItem()==null)
			{
				JOptionPane.showMessageDialog (this.view_addDrive,"Selectionnez un drive !","SYC message",1);				
			}
			else
			{
				//add the selected Drive
				model_SYC.setSelectedDriveType(this.view_addDrive.getjCbx_DriveList().getSelectedItem().toString());
				//then second step go to IHM_authorization
				model_SYC.init();
				model_SYC.setDisplay_authorization(true);
			}	
		}	
		
		if(e.getSource()==this.view_addDrive.getjCbx_DriveList())
		{
			//a drive has been selected
			JOptionPane.showMessageDialog (this.view_addDrive,"Validez votre choix en cliquant sur ajouter Drive","SYC message",1);
		}
	}
}