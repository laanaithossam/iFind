package org.esgi.ifind.indexer.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.esgi.ifind.indexer.gui.SystemTrayView;
import org.esgi.ifind.indexer.gui.view.MonitoringView;

/**
 * The Class MonitorClickController.
 */
public class MonitorClickController implements ActionListener {
	
	/** The parent. */
	SystemTrayView parent;
	
	/** The monitoring view. */
	MonitoringView monitoringView;
	
	/**
	 * Instantiates a new monitor click controller.
	 *
	 * @param parent the parent
	 */
	public MonitorClickController(SystemTrayView parent){
		this.parent=parent;
		this.monitoringView=MonitoringView.getInstance();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		monitoringView.setVisible(true);
	}

}
