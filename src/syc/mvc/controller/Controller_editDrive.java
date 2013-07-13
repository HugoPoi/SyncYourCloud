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
			//back to Drive
		}	
		if(e.getSource()==this.view_editDrive.getjBt_EditDrive())
		{
			
		}	
		if(e.getSource()==this.view_editDrive.getjBt_Browse())
		{
			int dialogResult = this.view_editDrive.getJFC_Browse().showOpenDialog(new JPanel());
			 
            if (dialogResult == JFileChooser.APPROVE_OPTION) 
            {
               java.io.File file = this.view_editDrive.getJFC_Browse().getSelectedFile();
               //System.out.println(file.getName());
            }
		}	
	}
}
