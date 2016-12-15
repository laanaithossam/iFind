package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.DialogBoxNewProjectView;

public class CancelNewProjectController implements ActionListener{
	/**
	 * dialog box
	 * @see DialogBoxNewProjectView
	 */
	private DialogBoxNewProjectView dialog;
	
	/**
	 * Constructor
	 * @param dialog
	 * 		the dialog box
	 * 
	 * @see DialogBoxNewProjectView
	 */
	public CancelNewProjectController(DialogBoxNewProjectView dialog){
		this.dialog = dialog;
	}

	/**
	 * Hide the dialog box
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.dialog.setVisible(false);
	}
}
