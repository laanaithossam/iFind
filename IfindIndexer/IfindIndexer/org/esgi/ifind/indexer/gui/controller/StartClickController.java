package org.esgi.ifind.indexer.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.esgi.ifind.indexer.gui.SystemTrayView;
import org.esgi.ifind.indexer.gui.view.MonitoringView;

/**
 * The Class StartClickController.
 */
public class StartClickController implements ActionListener {
	
	/** The parent. */
	SystemTrayView parent;
	
	/**
	 * Instantiates a new start click controller.
	 *
	 * @param parent the parent
	 */
	public StartClickController(SystemTrayView parent){
		this.parent=parent;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		parent.initIndexerService().start();
		parent.updateGui(true);
		//Monitor
		MonitoringView.getInstance().setTextAreaMonitoring(" +Indexation started...");
		
	}
}