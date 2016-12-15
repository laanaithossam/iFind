package org.esgi.ifind.indexer.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.esgi.ifind.indexer.gui.SystemTrayView;
import org.esgi.ifind.indexer.gui.view.ConfigurationView;
import org.esgi.ifind.indexer.gui.view.MonitoringView;

/**
 * The Class ConfigClickController.
 */
public class ConfigClickController implements ActionListener {
	
	/** The parent. */
	SystemTrayView parent;
	
	/** The configuration view. */
	ConfigurationView configurationView;
	
	/**
	 * Instantiates a new config click controller.
	 *
	 * @param parent the parent
	 */
	public ConfigClickController(SystemTrayView parent){
		this.parent=parent;
		this.configurationView=ConfigurationView.getInstance();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if(null!=parent.getIndexerService()){
					parent.getIndexerService().stopIndexer();
					parent.updateGui(false);
					MonitoringView.getInstance().setTextAreaMonitoring(" +Indexation stoped.");
				}
				configurationView.setVisible(true);				
			}
		});
	}

}
