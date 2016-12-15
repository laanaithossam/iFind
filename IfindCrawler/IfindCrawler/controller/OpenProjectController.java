package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import bo.ProjectParamsBO;
import tools.FilterExtension;
import tools.ProjectParamsSerializer;
import tools.TreeGenerator;
import view.MainView;
import view.MenuView;

/**
 * Opens a project from a grab file
 */
public class OpenProjectController implements ActionListener {
	//Attributes needs parents
	private MenuView myMenu;

	//Constructor needs parents
	public OpenProjectController(MenuView myMenu){
		this.myMenu = myMenu;
	}
	
	/**
	 * Creates the project's gui elements
	 * 
	 * @see ProjectParamsBO
	 * @see ProjectParamsSerializer
	 * @see MenuView
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Only displays  files with .grab extension
		// TODO Auto-generated method stub
		//Only displays  files with .grab extension
		FileFilter grabExtension = new FilterExtension("Fichiers Grab",".grab");
		JFileChooser c = new JFileChooser(".");
		c.addChoosableFileFilter(grabExtension);
		c.setAcceptAllFileFilterUsed(false);
		int result = c.showOpenDialog(myMenu);
		
		//Check result
		if(result==JFileChooser.APPROVE_OPTION) {		
			ProjectParamsBO pp = new ProjectParamsBO();
			ProjectParamsSerializer pps = new ProjectParamsSerializer(c.getSelectedFile().getAbsolutePath());
			pp = pps.unSerialize();
			TreeGenerator.addProject(pp.getProjectWorkspace());
			myMenu.parent.updateTree();
			if(!myMenu.listViewContains(pp.getProjectName()))
				myMenu.createNewView(pp);
			myMenu.refreshParent(pp.getProjectWorkspace(),pp.getProjectName(),pp.getUrlToGrab());
		}
		else if (result == JFileChooser.CANCEL_OPTION) {	
		}
	}
	
	/**
	 * returns the position of a given MainView in a list
	 * @param key
	 * 		MainView key to search
	 * @param list
	 * 		list of Mainview
	 * @return position of the MainView
	 */
	public int getPosition(String key,ArrayList<MainView> list) {
		for (int i=0; i<list.size();i++){
			if(list.get(i).getTextProjectName().equals(key)){
				return i;
			}
		}
		return -1;
	}
}

