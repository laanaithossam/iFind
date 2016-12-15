package org.esgi.ifind.indexer.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.esgi.ifind.indexer.gui.view.PageRankTabView;

/**
 * The Class SubmitPRClickController.
 */
public class SubmitPRClickController implements ActionListener {

	/** The parent. */
	PageRankTabView parent;
	
	/**
	 * Instantiates a new submit pr click controller.
	 *
	 * @param parent the parent
	 */
	public SubmitPRClickController(PageRankTabView parent){
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
