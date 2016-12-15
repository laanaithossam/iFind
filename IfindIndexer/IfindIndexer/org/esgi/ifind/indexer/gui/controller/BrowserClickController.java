package org.esgi.ifind.indexer.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import org.esgi.ifind.indexer.gui.view.PathTabView;

/**
 * The Class BrowserClickController.
 */
public class BrowserClickController implements ActionListener {
	
	/** The parent. */
	PathTabView parent;
	
	/** The id. */
	int id;
	
	/**
	 * Instantiates a new browser click controller.
	 *
	 * @param parent the parent
	 * @param id the id
	 */
	public BrowserClickController(PathTabView parent,int id){
		this.parent=parent;
		this.id=id;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser c = new JFileChooser();
		c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int result = c.showOpenDialog(parent);
		if(result==JFileChooser.APPROVE_OPTION) {
			if(id==1)
				parent.setTextIndexerPathName((c.getSelectedFile().getAbsolutePath())+ "\\");
			else
				parent.setTextDataPathName((c.getSelectedFile().getAbsolutePath())+"\\");
		}
	}
	
}
