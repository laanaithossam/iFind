package org.esgi.ifind.indexer.pagerank;

import com.temesoft.google.pr.PageRankService;

/**
 * The Class GooglePageRank.
 */
public class GooglePageRank {
	
	/** The instance. */
	private static GooglePageRank instance=null;
	
	/** The pr service. */
	private static PageRankService prService;
	
	/**
	 * Instantiates a new google page rank.
	 */
	private GooglePageRank(){
		prService=new PageRankService();
	}
	
	/**
	 * Gets the single instance of GooglePageRank.
	 *
	 * @return single instance of GooglePageRank
	 */
	public synchronized static GooglePageRank getInstance(){
		if(null==instance)
			instance=new GooglePageRank();
		return instance;
	}
	
	/**
	 * Gets the pR.
	 *
	 * @param url the url
	 * @return the pR
	 */
	public int getPR(String url){
		 return prService.getPR(url);
	}
}
