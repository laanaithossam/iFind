package org.esgi.ifind.indexer.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.esgi.ifind.indexer.gui.SystemTrayView;

/**
 * The Class ExitClickController.
 */
public class ExitClickController implements ActionListener {
	
	/** The parent. */
	SystemTrayView parent;
	
	/**
	 * Instantiates a new exit click controller.
	 *
	 * @param parent the parent
	 */
	public ExitClickController(SystemTrayView parent){
		this.parent=parent;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
	}

}
