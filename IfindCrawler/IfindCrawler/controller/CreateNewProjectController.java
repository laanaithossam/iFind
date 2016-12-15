package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.DialogBoxNewProjectView;
import view.MenuView;

/**
 * Creates a new project dialog box
 */
public class CreateNewProjectController implements ActionListener{
	private MenuView myMenu;

	/**
	 * Constructor needs parents
	 * @param myMenu
	 * 		parent menu
	 * @see MenuView
	 */
	public CreateNewProjectController(MenuView myMenu){
		this.myMenu = myMenu;
	}
	/**
	 * Creates a new project dialog box
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		DialogBoxNewProjectView mdnp = new DialogBoxNewProjectView(myMenu.parent);
		mdnp.setVisible(true);
	}
}
