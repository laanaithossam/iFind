package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import bo.ProjectParamsBO;

import tools.TreeGenerator;
import view.DialogBoxNewProjectView;

/**
 * creates the project workspace if not exists,
 * then we create the project directory
 * we add the new project to the tree
 * 
 * @see ProjectParamsBO
 * @see DialogBoxNewProjectView
 */
public class SubmitNewProjectController implements ActionListener{
	private DialogBoxNewProjectView dialog;
	
	public SubmitNewProjectController(DialogBoxNewProjectView dialog){
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//If the workspace does not exists we create it
		ProjectParamsBO pp = new ProjectParamsBO();
		pp.setProjectWorkspace(dialog.getTextPathName().getText()+"/"+ dialog.getTextProjectName().getText());
		pp.setProjectName(dialog.getTextProjectName().getText());
		pp.setUrlToGrab("");
		pp.setAlreadyStarted(false);
		this.dialog.setRegisterPathname(dialog.getTextPathName().getText()+"/"+ pp.getProjectName());
		File workspace = new File(dialog.getTextPathName().getText());
		if(!workspace.exists())
		{
			workspace.mkdir();
		}
		//then we create the projectDirectory
		File projectPath = new File(this.dialog.getRegisterPathname());
		projectPath.mkdir();
		this.dialog.saveProject();
		//We add the new project to the tree
		TreeGenerator.addProject(this.dialog.getRegisterPathname());
		//And we update parent
		this.dialog.updateParent(pp);	
		this.dialog.setVisible(false);
	}
}
