package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import bo.ProjectParamsBO;
import model.Model;
import tools.ProjectParamsSerializer;
import tools.TreeGenerator;
import view.RootView;

/**
 * right click tree menu
 */
public class TreeClickController implements MouseListener{
	//Attributes
	private JTree triggerTree;
	private RootView rootFrame;
	
	//Constructor needs RootView and the tree which dispached the event
	public TreeClickController(RootView rootFrame,JTree triggerTree) {
		this.rootFrame =  rootFrame;
		this.triggerTree = triggerTree;
	}

	/**
	 * if double click
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		//If double clicked
		if (e.getClickCount() == 2){
			//getting the node selected
			DefaultMutableTreeNode dmt = (DefaultMutableTreeNode)triggerTree.getLastSelectedPathComponent();
			//If it's not the root(Liste des project ouverts)
			if((dmt != null)&&(dmt.getParent()!=null)){
				//If the item selected exists in the grabMapFiles map
				if(Model.grabMapFiles.containsKey(dmt.toString())){
					//And if the extension of this item is not .grab
					if(!isGrabExtension(dmt.toString())){
						//that means that it's a file and it must be open in the corresponding application
						try {
							Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+Model.grabMapFiles.get(dmt.toString()));
						} catch (Exception ex) {
								String message = ex.getMessage();
								System.out.println(message);
						}
					//Else it's a grab file (our project file format)
					} else {
						//If the tab is already open we select the last one
						if (tabContains(dmt.toString().substring(0, dmt.toString().length()-5)))
							rootFrame.selectCorrespondingTab(getPosition(dmt.toString().substring(0, dmt.toString().length()-5),Model.projectTabID));
						//Else we open or refresh the project and create the corresponding tab
						else {
							ProjectParamsSerializer pps = new ProjectParamsSerializer(Model.grabMapFiles.get(dmt.toString()));
							ProjectParamsBO pp = pps.unSerialize();
							TreeGenerator.addProject(pp.getProjectWorkspace());
							createNewView(pp);
							refreshParent(pp.getProjectWorkspace(),pp.getProjectName(),pp.getUrlToGrab());
							rootFrame.selectCorrespondingTab(Model.projectTabID.size()-1);
						}
					}
				} 
			}
		}
	}
	
	/**
	 * Create a new view and insert it in a new tab
	 * @param pp
	 * 		project
	 * 
	 * @see ProjectParamsBO
	 */
	public void createNewView(ProjectParamsBO pp){
		rootFrame.createNewView(pp);
		rootFrame.createNewTab(pp.getProjectName(), getPosition(pp.getProjectName(),Model.projectTabID));
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
	 * Check if the tab is already created
	 * @param title
	 * 		tab title
	 * @return
	 * 		true if it is created
	 */
	public boolean tabContains(String title) {
		int size = rootFrame.getJtb().getComponentCount()-1;
		boolean result = false;
		if(size>0){
		for (int i=0;i<size;i++){
			if(rootFrame.getJtb().getTitleAt(i).equals(title))
			result = true;
			}
		}
		return result;
	}
	
	/**
	 * Call the main refresh method
	 * @param pathName
	 * 		path name project
	 * @param projectName
	 * 		project name
	 * @param url
	 * 		project url
	 */
	public void refreshParent(String pathName,String projectName,String url){
		rootFrame.updateMainView(pathName, projectName, url,getPosition(projectName,Model.projectTabID));
	}
	
	/**
	 * Check if the file .grab type
	 * @param filename
	 * 		the file name
	 * @return true if the file is .grab type
	 */
	public boolean isGrabExtension(String filename){
		if(filename.substring(filename.length()-5, filename.length()).equals(".grab")){
			return true;
		} else return false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * if right click
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
      	if(e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
			//If it's not the root(Liste des project ouverts)
	      	JPopupMenu popup = new JPopupMenu();
	      	JMenuItem newProject = new JMenuItem("Nouveau projet",new ImageIcon("img/new.png"));
	      	newProject.addActionListener(new CreateNewProjectController(rootFrame.getMenuView()));
			popup.add(newProject);
			JMenuItem openProject = new JMenuItem("Ouvrir projet",new ImageIcon("img/open.png"));
			openProject.addActionListener(new OpenProjectController(rootFrame.getMenuView()));
			popup.add(openProject);
			JMenuItem closeProject = new JMenuItem("Fermer projet",new ImageIcon("img/close.png")); 
			closeProject.addActionListener(new DeleteProjectFromTreeController(triggerTree));
			popup.add(closeProject);
			JMenuItem deleteProject = new JMenuItem("Supprimer projet",new ImageIcon("img/delete.png")); 
			deleteProject.addActionListener(new DeleteProjectFromDiskController(triggerTree));
			popup.add(deleteProject);
			popup.addSeparator();
			JMenuItem quitItem = new JMenuItem("Quitter",new ImageIcon("img/quit.png"));
			quitItem.addActionListener(new ExitProjectController());
			popup.add(quitItem);
		    popup.show(triggerTree, e.getX(), e.getY());
		    TreePath selPath = triggerTree.getPathForLocation(e.getX(), e.getY());
		    if (selPath != null) {
		        	triggerTree.setSelectionPath(selPath);
		    }
		    DefaultMutableTreeNode dmt = (DefaultMutableTreeNode)triggerTree.getLastSelectedPathComponent();
			if((dmt.getParent()!=null)){
				newProject.setEnabled(false);
				openProject.setEnabled(false);
				//closeProject.setEnabled(true);
				if(!Model.grabMapDir.containsKey(dmt.toString())){
					closeProject.setEnabled(false);
					deleteProject.setEnabled(false);
				} 
			} else {
				newProject.setEnabled(true);
				openProject.setEnabled(true);
				closeProject.setEnabled(false);
				deleteProject.setEnabled(false);
			}
      	}
	}
}
