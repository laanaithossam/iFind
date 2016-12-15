package org.esgi.ifind.indexer.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.esgi.ifind.indexer.gui.SystemTrayView;
import org.esgi.ifind.indexer.gui.view.MonitoringView;
import org.esgi.ifind.indexer.pagerank.PageRankService;

/**
 * The Class IfindDanceClickController.
 */
public class IfindDanceClickController implements ActionListener {

	/** The parent. */
	SystemTrayView parent;
	
	/** The page rank service. */
	PageRankService pageRankService;
	
	/**
	 * Instantiates a new ifind dance click controller.
	 *
	 * @param parent the parent
	 */
	public IfindDanceClickController(SystemTrayView parent){
		this.parent=parent;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(null!=parent.getIndexerService()){
			parent.getIndexerService().stopIndexer();
			parent.updateGui(false);
			MonitoringView.getInstance().setTextAreaMonitoring(" +Indexation stoped.");
		}
		pageRankService=new PageRankService(parent);
		pageRankService.start();

	}

}
