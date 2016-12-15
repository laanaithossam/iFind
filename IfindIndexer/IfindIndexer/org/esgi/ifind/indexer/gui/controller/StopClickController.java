package org.esgi.ifind.indexer.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.esgi.ifind.indexer.gui.SystemTrayView;
import org.esgi.ifind.indexer.gui.view.MonitoringView;

/**
 * The Class StopClickController.
 */
public class StopClickController implements ActionListener {
	
	/** The parent. */
	SystemTrayView parent;
	
	/**
	 * Instantiates a new stop click controller.
	 *
	 * @param parent the parent
	 */
	public StopClickController(SystemTrayView parent){
		this.parent=parent;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		parent.getIndexerService().stopIndexer();
		parent.updateGui(false);
		//Monitor
		MonitoringView.getInstance().setTextAreaMonitoring(" +Indexation stoped.");
	}

}