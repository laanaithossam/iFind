package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import bo.ProjectParamsBO;
import model.Model;
import tools.ProjectParamsSerializer;
import view.MenuView;

/**
 * saving the project
 */
public class SaveProjectController implements ActionListener{
	/**
	 * the menu
	 */
	private MenuView myMenu;
	/**
	 * urls queued list
	 */
	private ArrayList<String> queueUrlsList;
	/**
	 * urls completed list
	 */
	private ArrayList<String> completedUrlsList;
	
	/**
	 * @param myMenu
	 * 		project menu 
	 */
	public SaveProjectController(MenuView myMenu){
		this.myMenu = myMenu;
	}

	/**
	 * actions to save a project
	 * 
	 * @see ProjectParamsBO
	 * @see ProjectParamsSerializer
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Save the project in the corresponding file
		int selectedIndex = this.myMenu.parent.getJtb().getSelectedIndex();
		if(Model.mainViewList.get(selectedIndex).stop){
			queueUrlsList=new ArrayList<String>();
			completedUrlsList=new ArrayList<String>();
			ProjectParamsBO pp = new ProjectParamsBO();
			pp.setProjectName(Model.mainViewList.get(selectedIndex).getTextProjectName());
			pp.setProjectWorkspace(Model.mainViewList.get(selectedIndex).getTextPathName());
			pp.setUrlToGrab(Model.mainViewList.get(selectedIndex).getTextUrl());
			String pathToSave = Model.mainViewList.get(selectedIndex).getTextPathName()+"/"+pp.getProjectName()+".grab";
			ProjectParamsSerializer pps = new ProjectParamsSerializer(pathToSave);
			
			Model.mainViewList.get(selectedIndex).getTextAreaDiscovery().AddUrls(queueUrlsList);
			Model.mainViewList.get(selectedIndex).getTextAreaCompleted().AddUrls(completedUrlsList);
			
			if(!queueUrlsList.isEmpty()){
				if (!queueUrlsList.get(0).isEmpty())
					pp.setAlreadyStarted(true);
				else
					pp.setAlreadyStarted(false);
			}
			else
				pp.setAlreadyStarted(false);
			
			pp.setQueueUrlsList(queueUrlsList);
			pp.setCompletedUrlsList(completedUrlsList);
			pp.setErrorUrlsList(Model.mainViewList.get(selectedIndex).tc.getErrorUrlsList());
			pp.setSize(Model.mainViewList.get(selectedIndex).tc.getCountSize());
			pps.serialize(pp);
			this.myMenu.parent.updateTree();
		}
	}
}
