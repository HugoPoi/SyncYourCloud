package syc.mvc.controller;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.google.api.services.drive.model.File;

import syc.mvc.model.Model;
import syc.mvc.view.IHM_editDrive;
import syc.mvc.view.IHM_home;

public class Controller_editDrive implements ActionListener
{
	private Model model_SYC;
    private IHM_editDrive view_editDrive;
    
    public Controller_editDrive(Model aModel_SYC, IHM_editDrive aView_editDrive) 
    {
		this.model_SYC = aModel_SYC;
		this.view_editDrive = aView_editDrive;
		this.ControllerActionListenerForComponent(view_editDrive.getContentPane());
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
		if(e.getSource()==this.view_editDrive.getjBt_BackToDrives())
		{
			//back to Drives
			model_SYC.init();
			model_SYC.setDisplay_drives(true);
		}	
		if(e.getSource()==this.view_editDrive.getjBt_EditDrive())
		{
			int dialogResult = JOptionPane.showConfirmDialog (this.view_editDrive,
					"Confirmez la ou les modifications apportée(s)","SYC message",JOptionPane.OK_CANCEL_OPTION);
			
			if(dialogResult == JOptionPane.OK_OPTION)
			{
				//apply the new modification
				
				//then go back back to Drives
				model_SYC.init();
				model_SYC.setDisplay_drives(true);
			}
		}	
		if(e.getSource()==this.view_editDrive.getjBt_Browse())
		{
			JPanel jpan_Browse= new JPanel();
			this.view_editDrive.getJFC_Browse().setPreferredSize(new Dimension(500,270));	
			this.view_editDrive.getJFC_Browse().setDialogTitle("Selection de l'emplacement Local");
			this.view_editDrive.getJFC_Browse().setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			this.view_editDrive.getJFC_Browse().setApproveButtonText("Choisir dossier");
			this.view_editDrive.getJFC_Browse().setAcceptAllFileFilterUsed(false);
			
			
			this.view_editDrive.getJFC_Browse().setCurrentDirectory(new java.io.File(model_SYC.getTxt_LocalLocation_editDrive()));
			
			int dialogResult = this.view_editDrive.getJFC_Browse().showOpenDialog(jpan_Browse);
			 
            if (dialogResult == JFileChooser.APPROVE_OPTION)
            {
               if(this.view_editDrive.getJFC_Browse().getSelectedFile() != null)
            	model_SYC.setTxt_LocalLocation_editDrive(this.view_editDrive.getJFC_Browse().getSelectedFile().getAbsolutePath());
               
               //move the folder to the new place selected
            }
		}	
	}
}
