package syc.mvc.controller;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import syc.mvc.model.Model;
import syc.mvc.view.IHM_addDrive;
import syc.mvc.view.IHM_synchronisationRules;

public class Controller_synchronisationRules implements ActionListener
{
	private Model model_SYC;
    private IHM_synchronisationRules view_synchronisationRules;
    
    public Controller_synchronisationRules(Model aModel_SYC, IHM_synchronisationRules aView_synchronisationRules) 
    {
		this.model_SYC = aModel_SYC;
		this.view_synchronisationRules = aView_synchronisationRules;
		this.ControllerActionListenerForComponent(view_synchronisationRules.getContentPane());
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
		if(e.getSource()==this.view_synchronisationRules.getjBt_BackToDrives())
		{
			//Back to IHM_drives
			model_SYC.init();
			model_SYC.setDisplay_drives(true);
		}	
		
		//if(e.getSource()==this.view_synchronisationRules.getjBt_Next())
		//{
			//nothing to do
		//}
		
		if(e.getSource()==this.view_synchronisationRules.getjBt_AddRulesSYC())
		{
			//go to IHM_AddRulesSYC
			model_SYC.init();
			model_SYC.setDisplay_addSynchronisationRule(true);
		}	
		
		if(e.getSource()==this.view_synchronisationRules.getjBt_Remove())
		{
			//if a rules is selected 
			
			int dialogResult = JOptionPane.showConfirmDialog (this.view_synchronisationRules,
					"Etes vous sur de vouloir suprrimer cette Règle","SYC message",JOptionPane.OK_CANCEL_OPTION);
			
			if(dialogResult == JOptionPane.OK_OPTION)
			{
				//delete rules
				
				//Then back to IHM_drives
				model_SYC.init();
				model_SYC.setDisplay_drives(true);
			}
		}	
	}
}

