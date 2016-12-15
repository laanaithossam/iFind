package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tools.ProjectParamsSerializer;
import bo.ProjectParamsBO;
import controller.BrowseNewProjectController;
import controller.CancelNewProjectController;
import controller.SubmitNewProjectController;
import model.Model;

public class DialogBoxNewProjectView extends JDialog{
	
	//Attributes
	private static final long serialVersionUID = 1L;
	/**
	 * label containing the project name
	 */
	private JLabel lblProjectName;
	/**
	 * label containing the project path
	 */
	private JLabel lblProjectPath;
	/**
	 * textfield containing the project name
	 */
	private JTextField textProjectName;
	/**
	 * textfield containing the path name
	 */
	private JTextField textPathName;
	/**
	 * cancel button
	 * 
	 * exit the dialog box
	 */
	private JButton cancelBouton;
	/**
	 * submit button
	 * 
	 * save and exit the dialog box
	 */
	private JButton submitBouton;
	/**
	 * browser button
	 * 
	 * reach a workspace
	 */
	private JButton browserBouton;
	/**
	 * registered pathname
	 */
	private String registerPathname;
	/**
	 * the parent frame
	 */
	public RootView parent;
	
	/**
	 * Construct a dialog box
	 * @param parent
	 * 		the parent frame
	 */
	public DialogBoxNewProjectView(RootView parent){
		
		//Initialisation of the dialog view
		this.setLocationRelativeTo(parent);
		this.setTitle("New Project");
		this.setSize(new Dimension(550,200));
		this.parent=parent;
		
		//Panel URL
		JPanel panProjectName = new JPanel(new GridLayout(2, 3));
		
		//Text ProjectName
		textProjectName = new JTextField();
		textProjectName.setText("MyProject");
		textProjectName.setPreferredSize(new Dimension(150, 25));
		
		//Text PathName
		textPathName = new JTextField();
		textPathName.setText("c:\\workspace");
		textPathName.setPreferredSize(new Dimension(150, 25));
		
		//Label ProjectName
		lblProjectName = new JLabel("Nom du projet : ");
		lblProjectPath = new JLabel("Chemin du projet : ");
		
		//Button cancel to exit the dialog box
		cancelBouton=new JButton("Annuler");
		cancelBouton.addActionListener(new CancelNewProjectController(this));
		
		//Button finish to save and exit the dialog box
		submitBouton=new JButton("Terminer");
		submitBouton.addActionListener(new SubmitNewProjectController(this));
		
		//Button Browse to reach a workspace
		browserBouton=new JButton("Parcourir");
		browserBouton.addActionListener(new BrowseNewProjectController(this));
		
		//Adding of the components to the  dialog box panel
		panProjectName.add(lblProjectName);
		panProjectName.add(textProjectName);
		panProjectName.add(new JLabel(""));
		panProjectName.add(lblProjectPath);
		panProjectName.add(textPathName);
		panProjectName.add(browserBouton);
		
		//Panel Control
		JPanel control=new JPanel();
		control.add(submitBouton);
		control.add(cancelBouton);
		
		//Panel Content
		JPanel content = new JPanel();
		content.setBorder(BorderFactory.createTitledBorder("Nouveau projet :"));
		content.add(panProjectName);
		
		//Separation of the buttons and the label fields/text fields
		this.getContentPane().add(content,BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}
	
	/**
	 * Updtates the root view
	 * 
	 * @param pp
	 * 		the project
	 * 
	 * @see RootView#createNewView(ProjectParamsBO)
	 * @see RootView#createNewTab(String, int)
	 * @see DialogBoxNewProjectView#getPosition(String, ArrayList)
	 * @see Model
	 * @see RootView#selectCorrespondingTab(int)
	 * @see RootView#updateMainView(String, String, String, int)
	 * @see RootView#getJtb()
	 * @see RootView#updateTree()
	 */
	public void updateParent(ProjectParamsBO pp){
		parent.createNewView(pp);
		//add the new view so created to the tabPaneWithCloseIcon
		parent.createNewTab(textProjectName.getText(),getPosition(pp.getProjectName(),Model.projectTabID));
		//Select the last tab opened
		parent.selectCorrespondingTab(Model.projectTabID.size()-1);
		//Update textfields corresponding to data from the project
		parent.updateMainView(textPathName.getText()+"/"+pp.getProjectName(), pp.getProjectName(), "", parent.getJtb().getSelectedIndex());
		//refresh tree
		parent.updateTree();
	}
	
	/**
	 * Save the project with data from the interface
	 * 
	 * @see ProjectParamsBO
	 * @see ProjectParamsBO#setProjectName(String)
	 * @see ProjectParamsBO#setProjectWorkspace(String)
	 * @see ProjectParamsBO#setUrlToGrab(String)
	 * @see ProjectParamsSerializer
	 * @see ProjectParamsSerializer#serialize(ProjectParamsBO)
	 */
	public void saveProject(){
		ProjectParamsBO pp = new ProjectParamsBO();
		pp.setProjectName(textProjectName.getText());
		pp.setProjectWorkspace(textPathName.getText()+"/Grab_"+ textProjectName.getText());
		pp.setUrlToGrab("");

		ProjectParamsSerializer pps = new ProjectParamsSerializer(textPathName.getText() + "/" + "Grab_" + textProjectName.getText()+"/"+textProjectName.getText()+".grab");
		pps.serialize(pp);
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
	 * gets the project name
	 * @return project name
	 */
	public JTextField getTextProjectName() {
		return textProjectName;
	}
	/**
	 * gets the project path
	 * @return project path
	 */
	public JTextField getTextPathName() {
		return textPathName;
	}
	/**
	 * gets register path name
	 * @return register path name
	 */
	public String getRegisterPathname() {
		return registerPathname;
	}
	
	/**
	 * sets the project name
	 * @param textProjectName
	 * 		the new project name
	 */
	public void setTextProjectName(JTextField textProjectName) {
		this.textProjectName = textProjectName;
	}
	/**
	 * sets the path name
	 * @param textPathName
	 * 		the new path name
	 */
	public void setTextPathName(JTextField textPathName) {
		this.textPathName = textPathName;
	}
	/**
	 * sets the register path name
	 * @param registerPathname
	 * 		the new register pathname
	 */
	public void setRegisterPathname(String registerPathname) {
		this.registerPathname = registerPathname;
	}
}
