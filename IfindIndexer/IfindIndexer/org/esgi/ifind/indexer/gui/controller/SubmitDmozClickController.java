package org.esgi.ifind.indexer.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.esgi.ifind.indexer.gui.view.DmozTabView;

/**
 * The Class SubmitDmozClickController.
 */
public class SubmitDmozClickController implements ActionListener {

	/** The parent. */
	DmozTabView parent;
	
	/**
	 * Instantiates a new submit dmoz click controller.
	 *
	 * @param parent the parent
	 */
	public SubmitDmozClickController(DmozTabView parent){
		this.parent=parent;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		parent.save();
	}

}
