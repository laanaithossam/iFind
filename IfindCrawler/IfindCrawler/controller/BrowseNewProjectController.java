package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import view.DialogBoxNewProjectView;

public class BrowseNewProjectController implements ActionListener{
	/**
	 * Attributes
	 * 
	 * @see DialogBoxNewProjectView
	 */
	private DialogBoxNewProjectView dialog;
	
	/**
	 * Constructor needs parents
	 * @param dialog
	 * 		dialog box
	 */
	public BrowseNewProjectController(DialogBoxNewProjectView dialog){
		this.dialog = dialog;
	}

	/**
	 * dialog box permiting to open a project and updates the pathname
	 * 
	 * @see DialogBoxNewProjectView#getTextPathName()
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser c = new JFileChooser();
		c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int result = c.showOpenDialog(dialog);
		if(result==JFileChooser.APPROVE_OPTION) {
			//Update the textPathname
			dialog.getTextPathName().setText(c.getSelectedFile().getAbsolutePath());
		}
		else if (result == JFileChooser.CANCEL_OPTION) {
		}
	}
}
