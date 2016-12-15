package org.esgi.ifind.indexer;

import org.esgi.ifind.indexer.gui.SystemTrayView;
import org.esgi.ifind.indexer.indexer.IndexerService;


/**
 * Main Class.
 */
public class Application {
	
	/**
	 * Main method.
	 *
	 * @param args the arguments
	 * @see SystemTrayView
	 * @see IndexerService
	 */
	public static void main(String[] args) {
		SystemTrayView trayView = new SystemTrayView();	
		if (!trayView.init()){
			IndexerService indexerService= new IndexerService();
			indexerService.start();
		}
	}
}
