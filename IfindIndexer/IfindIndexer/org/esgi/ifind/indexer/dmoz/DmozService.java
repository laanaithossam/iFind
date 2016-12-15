package org.esgi.ifind.indexer.dmoz;
/**
 * The Class DmozService.
 */
public class DmozService {
	
	/** The instance. */
	private static DmozService instance = null;
	
	/** The ifind searcher dao. */
	private DmozDao ifindSearcherDao = DmozDao.getInstance();
	
	/**
	 * Instantiates a new dmoz service.
	 */
	private DmozService() {}
	
	/**
	 * Gets the single instance of DmozService.
	 *
	 * @return single instance of DmozService
	 */
	public static DmozService getInstance() {
		if (null == instance) {
			instance = new DmozService();
		}
		return instance;
	}
	
	/**
	 * Gets the description from dmoz.
	 *
	 * @param url the url
	 * @return the description from dmoz
	 */
	public String getDescriptionFromDmoz(String url) {
		return ifindSearcherDao.getDescriptionFromDmoz(url);
	}
}
