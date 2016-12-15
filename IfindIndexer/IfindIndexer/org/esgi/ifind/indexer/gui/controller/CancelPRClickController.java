package org.esgi.ifind.indexer.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.esgi.ifind.indexer.gui.view.PageRankTabView;

/**
 * The Class CancelPRClickController.
 */
public class CancelPRClickController implements ActionListener {

	/** The parent. */
	PageRankTabView parent;
	
	/**
	 * Instantiates a new cancel pr click controller.
	 *
	 * @param parent the parent
	 */
	public CancelPRClickController(PageRankTabView parent){
		this.parent=parent;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		parent.razTextPath();
	}

}
