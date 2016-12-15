package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JSplitPane;

import bo.ProjectParamsBO;
import model.Model;
import tools.JTabbedPaneWithCloseIcons;

/**
 * The root view contains all the base elements of the graphical interface :
 * <ul>
 * 		<li>the menu</li>
 * 		<li>the tree view</li>
 * 		<li>the main view</li>
 * 		<li>the tabs containing all the opened projects</li>
 * </ul>
 */
public class RootView extends JFrame{
	
	//Attributes
	private static final long serialVersionUID = 1L;
	/**
	 * A separator
	 */
	public JSplitPane split;
	/**
	 * The tree view
	 * 
	 * @see TreeView
	 * @see RootView#updateTree()
	 */
	private TreeView treeView;
	/**
	 * A new tab
	 * 
	 * @see JTabbedPaneWithCloseIcons
	 * @see RootView#createNewTab(String, int)
	 * @see RootView#selectCorrespondingTab(int)
	 * @see RootView#getJtb()
	 */
	private JTabbedPaneWithCloseIcons jtb;
	/**
	 * The menu
	 * 
	 * @see MenuView
	 * @see RootView#initMenu()
	 * @see RootView#getMenuView()
	 */
	private MenuView menuView;
	
	//Trivial constructor
	public RootView(){
		// Initialization of the mainVIew 
		super("Xtrem Grabber");
		menuView = new MenuView(this);
		//Onglet
		jtb = new JTabbedPaneWithCloseIcons();
		//Mainview
		Model.mainViewList = new ArrayList<MainView>();
		//Treeview project
	    treeView = new TreeView(this);
		this.setTitle("Ultra Grabber");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jtb.setPreferredSize(new Dimension(300, 300));
	    split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeView, jtb);
		this.getContentPane().add(split, BorderLayout.CENTER);
        this.setVisible(true);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.initMenu();
	}
	
	/**
	 * Initialization of the menuBar
	 */
	public void initMenu() {
		/**
		 * the menu bar
		 */
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menuView);
		setJMenuBar(menuBar);
		//this.setVisible(true);
	}
	
	/**
	 * Returns a menu
	 * 
	 * @return the menu
	 */
	public MenuView getMenuView() {
		return menuView;
	}

	/**
	 * Call the methods updateTree() from treeView class to update the display of the navigation tree
	 * 
	 * @see TreeView#updateTree()
	 */
	public void updateTree() {
		treeView.updateTree();
	}
	
	/**
	 * Refresh the main view
	 * 
	 * @param pathName
	 * 		Local path of the project
	 * @param projectName
	 * 		Name of the project
	 * @param url
	 * 		Main url of the project
	 * @param i
	 * 		
	 */
	public void updateMainView(String pathName,String projectName,String url,int i) {
		Model.mainViewList.get(i).setTextPathName(pathName);
		Model.mainViewList.get(i).setTextProjectName(projectName);
		Model.mainViewList.get(i).settextUrl(url);
	}
	
	/**
	 * Add a new tab in the TabPanelWIthCloseIcon at some position
	 * 
	 * @param title
	 * 		tab's title
	 * @param pos
	 * 		tab's position
	 */
	public void createNewTab(String title,int pos) {
		jtb.addTab(title, Model.mainViewList.get(pos), pos);
	}
	
	/**
	 * Update a tab with a project
	 * 
	 * @param sPathname
	 * 		the project's path name
	 * @param i
	 * 		tab's id
	 */
	public void reloadGrab(String sPathname,int i){
		Model.mainViewList.get(i).reloadGrab(sPathname);
	}
	
	/**
	 * Add a new view to mainViewList model
	 * 
	 * @param pp
	 * 		The project to add
	 */
	public void createNewView(ProjectParamsBO pp) {
		Model.mainViewList.add(new MainView(pp));
	}
	
	/**
	 * Select the tab which position on TabPanelWithCloseIcons is given 
	 * 
	 * @param pos
	 * 		position of the tab to select
	 */
	public void selectCorrespondingTab(int pos) {
		jtb.setSelectedIndex(pos);
	}
	
	/**
	 * Returns a tab
	 * 
	 * @return a tab
	 */
	public JTabbedPaneWithCloseIcons getJtb(){
		return this.jtb;
	}
}
