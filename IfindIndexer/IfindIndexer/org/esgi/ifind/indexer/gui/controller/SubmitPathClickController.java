package org.esgi.ifind.indexer.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.esgi.ifind.indexer.gui.view.PathTabView;

/**
 * The Class SubmitPathClickController.
 */
public class SubmitPathClickController implements ActionListener {

	/** The parent. */
	PathTabView parent;
	
	/**
	 * Instantiates a new submit path click controller.
	 *
	 * @param parent the parent
	 */
	public SubmitPathClickController(PathTabView parent){
		this.parent=parent;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		parent.saveTextPath();
	}

}
