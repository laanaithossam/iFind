package view;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import bo.ProjectParamsBO;
import model.Model;
import controller.ExitProjectController;
import controller.CreateNewProjectController;
import controller.OpenProjectController;
import controller.SaveProjectController;

/**
 * Contains the menu
 */
public class MenuView extends JMenu{
	
	//Attributes
	private static final long serialVersionUID = 1L;
	/**
	 * the parent frame
	 * 
	 * @see RootView
	 */
	public RootView parent;
	/**
	 * creates the menu
	 * 
	 * @param parent
	 * 		parent frame
	 */
	public MenuView(RootView parent){
		// Initialization of the menu
		super("Fichier");
		this.parent = parent;
		
		/**
		 * Menu item which creates a new project
		 * 
		 * @see controller.CreateNewProjectCont#CreateNewProjectController(MenuView)
		 */
		JMenuItem newProject = new JMenuItem("Nouveau",new ImageIcon("img/new.png"));
		/**
		 * Menu item which opens a project
		 * 
		 * @see controller.OpenProjectControlle#OpenProjectController(MenuView)
		 */
		JMenuItem openProject = new JMenuItem("Ouvrir",new ImageIcon("img/open.png"));
		/**
		 * Menu item which saves a project
		 */
		JMenuItem saveProject = new JMenuItem("Enregistrer",new ImageIcon("img/save.png"));
		/**
		 * Menu item which closes a project
		 */
		JMenuItem exitProject = new JMenuItem("Quitter",new ImageIcon("img/quit.png"));
		
		//Affect the button's actions when receive an event
		newProject.addActionListener(new CreateNewProjectController(this));
		openProject.addActionListener(new OpenProjectController(this));
		saveProject.addActionListener(new SaveProjectController(this));
		exitProject.addActionListener(new ExitProjectController());
		
		//Add buttons to the menuView
		this.add(newProject);
		this.add(openProject);
		this.add(saveProject);
		this.add(exitProject);
	}
	
	/**
	 * Call the updateMainView method to refresh the main view
	 * 
	 * @param pathName
	 * 		Local path of the project
	 * @param projectName
	 * 		Name of the project
	 * @param url
	 * 		Main url of the project
	 * 
	 * @see RootView#updateMainView(String, String, String, int)
	 * @see MenuView#getPosition(String, ArrayList)
	 */
	public void refreshParent(String pathName,String projectName,String url){
		parent.updateMainView(pathName, projectName, url,getPosition(projectName,Model.projectTabID));
	}
	
	/**
	 * Creates a new project view,
	 * a new tab linked to it,
	 * and selects this new tab
	 * 
	 * @param pp
	 * 		a project
	 * 
	 * @see ProjectParamsBO
	 * @see RootView#createNewView(ProjectParamsBO)
	 * @see RootView#createNewTab(String, int)
	 * @see ProjectParamsBO#getProjectName()
	 * @see MenuView#getPosition(String, ArrayList)
	 * @see Model
	 * @see RootView#selectCorrespondingTab(int)
	 */
	public void createNewView(ProjectParamsBO pp){
		//Create a new view
		parent.createNewView(pp);
		//Add this view to the tabPanelWithCloseIcons
		parent.createNewTab(pp.getProjectName(), getPosition(pp.getProjectName(),Model.projectTabID));
		//Select the last tab opened
		parent.selectCorrespondingTab(Model.projectTabID.size()-1);
	}
	
	/**
	 * Returns the position of a String key in String list
	 * 
	 * @param key
	 * 		String key to search
	 * @param list
	 * 		lit of Strings
	 * @return position of a String key in String list else -1
	 */
	public int getPosition(String key,ArrayList<String> list) {
		if (list.contains(key))
		{
			for (int i=0;i<list.size();i++)
			{
				if (list.get(i).equals(key))
					return i;
			}
		}
		return -1;
	}
	
	/**
	 * Check if a view already exists
	 * 
	 * @param projectName
	 * 		the project name
	 * @return true if the view project exists, false if not
	 * 
	 * @see MainView#getTextProjectName()
	 */
	public boolean listViewContains(String projectName){
		boolean result = false;
		for (int i=0; i<Model.mainViewList.size();i++){
			if (Model.mainViewList.get(i).getTextProjectName().equals(projectName))
				result=true;
		}
		return result;
	}
}
