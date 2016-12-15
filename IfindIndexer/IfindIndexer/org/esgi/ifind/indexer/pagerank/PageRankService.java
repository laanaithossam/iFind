package org.esgi.ifind.indexer.pagerank;

import java.io.IOException;
import java.util.HashMap;

import org.esgi.ifind.indexer.gui.SystemTrayView;
import org.esgi.ifind.indexer.gui.view.MonitoringView;
import org.esgi.ifind.indexer.indexer.Indexer;

/**
 * The Class PageRankService.
 */
public class PageRankService extends Thread {

	/** The indexer. */
	private Indexer indexer;
	
	/** The page rank. */
	private IfindPageRank pageRank;
	
	/** The parent. */
	private SystemTrayView parent;
	
	/**
	 * Instantiates a new page rank service.
	 *
	 * @param parent the parent
	 */
	public PageRankService(SystemTrayView parent){
		this.parent=parent;
		indexer=new Indexer();
		pageRank=new IfindPageRank();
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		parent.getIfindDanceItem().setEnabled(false);
		HashMap<String, Double>map;
		MonitoringView.getInstance().setTextAreaMonitoring(" +Ifind Dance started...");
		MonitoringView.getInstance().setTextAreaMonitoring("\t-Process PageRank started...");
		map=pageRank.rankUrls();
		MonitoringView.getInstance().setTextAreaMonitoring("\t-Process PageRank finished.");
		if(null!=map){
			MonitoringView.getInstance().setTextAreaMonitoring("\t-Update Index PageRank started...");
			try {
				indexer.updatePageRank(map);
			} catch (IOException e) {
				MonitoringView.getInstance().setTextAreaMonitoring("* Erreur : " +e.getMessage());
			}
			MonitoringView.getInstance().setTextAreaMonitoring("\t-Update Index PageRank finished.");
		}
		MonitoringView.getInstance().setTextAreaMonitoring(" +Ifind Dance finished.");
		parent.getIfindDanceItem().setEnabled(true);
	}
}
