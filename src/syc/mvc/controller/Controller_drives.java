package syc.mvc.controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.apache.commons.collections.iterators.ArrayListIterator;

import api.ManageDrive;

import syc.mvc.model.Model;
import syc.mvc.view.IHM_drives;
import syc.mvc.view.IHM_home;

public class Controller_drives implements ActionListener 
{
	private Model model_SYC;
    private IHM_drives view_drives;
    
    public Controller_drives(Model aModel_SYC, IHM_drives aView_drives) 
    {
		this.model_SYC = aModel_SYC;
		this.view_drives = aView_drives;
		aView_drives.setControllerOfThis(this);
		this.ControllerActionListenerForComponent(view_drives.getContentPane());
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
						
				if(cmp_temps instanceof JButton)
				{	
					((JButton) cmp_temps).addActionListener(this);
				}
				if(cmp_temps instanceof JTextField)
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
		if(e.getSource()==this.view_drives.getjBt_Deconnexion())
		{
			//back to IHM_home
			model_SYC.init();
			model_SYC.setDisplay_home(true);
			
		}
		
		if(e.getSource()==this.view_drives.getjBt_RulesSYC()) 
		{
			//go to IHM_synchronisationRules
			model_SYC.init();
			model_SYC.setDisplay_synchronisationRules(true);
		}
		
		if(e.getSource()==this.view_drives.getjBt_AddCloudDrive())
		{
			//go to IHM_AddDrive
			model_SYC.init();
			model_SYC.setDisplay_addDrive(true);	
		}
		
		int i;
		for(i = 0; i < model_SYC.drives.size();i++)
		{
			if(e.getSource()==this.view_drives.getTabBt_Edit().get(i))
			{
				//Select the Drive to edit for the next page
				model_SYC.setIndexIntDriveSelectedOnDrivePage(i);

				//go to IHM_editDrive
				model_SYC.init();
				model_SYC.setDisplay_editDrive(true);
			}

			if(e.getSource()==this.view_drives.getTabBt_SeeFile().get(i))
			{
				model_SYC.setIndexIntDriveSelectedOnDrivePage(i);
			}
			
			if(e.getSource()==this.view_drives.getTabBt_Remove().get(i))
			{
				model_SYC.setIndexIntDriveSelectedOnDrivePage(i);
				int dialogResult = JOptionPane.showConfirmDialog (this.view_drives, "Êtes vous sûr de vouloir supprimer ce drive?","Warning", 1);
				if(dialogResult == JOptionPane.YES_OPTION)
					model_SYC.removeDrive(i);
			}

		}
	}
}