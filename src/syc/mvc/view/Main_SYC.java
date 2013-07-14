package syc.mvc.view;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import syc.mvc.controller.Controller_account;
import syc.mvc.controller.Controller_addDrive;
import syc.mvc.controller.Controller_addSynchronisationRule;
import syc.mvc.controller.Controller_authorization;
import syc.mvc.controller.Controller_drives;
import syc.mvc.controller.Controller_editDrive;
import syc.mvc.controller.Controller_home;
import syc.mvc.controller.Controller_synchronisationRules;
import syc.mvc.model.Model;

public class Main_SYC 
{
	public static void main(String[] args) /*theStyle*/ throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException 
	{
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
		{
	        if ("Nimbus".equals(info.getName())) 
	        {
	            UIManager.setLookAndFeel(info.getClassName());
	            break;
	        }
	    }
		
		SYC_run();	
	}
	
	private static void SYC_run()
	{

		Model model_SYC = new Model();		
		
		IHM_home view_home = new IHM_home(model_SYC); 
		Controller_home c_home =  new Controller_home(model_SYC, view_home);
		c_home.ControllerActionListenerForComponent(view_home.getContentPane());
		
		IHM_account  view_account = new IHM_account(model_SYC);
		Controller_account c_account =  new Controller_account(model_SYC, view_account);
		c_account.ControllerActionListenerForComponent(view_account.getContentPane());
		
		IHM_drives view_drives = new IHM_drives(model_SYC);
		Controller_drives c_drives =  new Controller_drives(model_SYC, view_drives);
		c_drives.ControllerActionListenerForComponent(view_drives.getContentPane());
		
		IHM_addDrive view_addDrive = new IHM_addDrive(model_SYC);
		Controller_addDrive c_addDrive =  new Controller_addDrive(model_SYC, view_addDrive);
		c_addDrive.ControllerActionListenerForComponent(view_addDrive.getContentPane());
		
		IHM_authorization view_authorization = new IHM_authorization(model_SYC);
		Controller_authorization c_authorization=  new Controller_authorization(model_SYC, view_authorization);
		c_authorization.ControllerActionListenerForComponent(view_authorization.getContentPane());
		
		IHM_synchronisationRules view_synchronisationRules = new IHM_synchronisationRules(model_SYC);
		Controller_synchronisationRules c_synchronisationRules = new Controller_synchronisationRules(model_SYC, view_synchronisationRules);
		c_synchronisationRules.ControllerActionListenerForComponent(view_synchronisationRules.getContentPane());
		
		IHM_addSynchronisationRule view_addSynchronisationRule = new IHM_addSynchronisationRule(model_SYC);
		Controller_addSynchronisationRule c_addSynchronisationRule = new Controller_addSynchronisationRule(model_SYC, view_addSynchronisationRule);
		c_addSynchronisationRule.ControllerActionListenerForComponent(view_addSynchronisationRule.getContentPane());
		
		IHM_editDrive view_editDrive = new IHM_editDrive(model_SYC);
		Controller_editDrive c_editDrive = new Controller_editDrive(model_SYC, view_editDrive);
		c_editDrive.ControllerActionListenerForComponent(view_editDrive.getContentPane());
		
		view_home.setVisible(true);
	}
}

//MemoMVC=========================================================
/*	ClassView cv = new ClassView(cm);
 *	ClassModel cm = new ClassModel();
 *	ClassController cc = new ClassController(cv,cm); 
 *	Enchainement du code cc.fonction().fonctionExemplecvSurcm()... etc
 */	