package org.esgi.ifind.indexer.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.esgi.ifind.indexer.gui.view.DmozTabView;

/**
 * The Class CancelDmozClickController.
 */
public class CancelDmozClickController implements ActionListener {

	/** The parent. */
	DmozTabView parent;
	
	/**
	 * Instantiates a new cancel dmoz click controller.
	 *
	 * @param parent the parent
	 */
	public CancelDmozClickController(DmozTabView parent){
		this.parent=parent;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		parent.raz();
	}

}
