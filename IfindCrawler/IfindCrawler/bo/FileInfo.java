package bo;

/**
 * Contains a file info
 */
public class FileInfo {
	/**
	 * The file name
	 * 
	 * @see FileInfo#getFilename()
	 * @see FileInfo#setFilename(String)
	 */
	private String filename;
	
	/**
	 * The file size
	 * 
	 * @see FileInfo#getFilesize()
	 * @see FileInfo#setFilesize(int)
	 */
	private int filesize;
	
	/**
	 * The file url
	 * 
	 * @see FileInfo#getUrl()
	 * @see FileInfo#setUrl(String)
	 */
	private String url;
	
	/**
	 * Percent of file fetched
	 * 
	 * @see FileInfo#getPercentDownloaded()
	 * @see FileInfo#setPercentDownloaded(int)
	 */
	private int percentDownloaded;
	
	/**
	 * Returns the percentage of file recovery
	 * 
	 * @return the percentage of file recovery
	 */
	public int getPercentDownloaded() {
		return percentDownloaded;
	}

	/**
	 * Updates the percentage of file recovery
	 * 
	 * @param percentDownloaded
	 * 		the new percentage of file recovery
	 */
	public void setPercentDownloaded(int percentDownloaded) {
		this.percentDownloaded = percentDownloaded;
	}

	/**
	 * Returns the file name
	 * 
	 * @return the file name
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Returns the file url
	 * 
	 * @return the file url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Updates the file url
	 * 
	 * @param url
	 * 		the new file url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Updates the file name
	 * 
	 * @param filename
	 * 		the new file name
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * Returns the file size
	 * 
	 * @return the file size in bytes
	 */
	public int getFilesize() {
		return filesize;
	}

	/**
	 * Updates the file size
	 * 
	 * @param filesize
	 * 		the new file size in bytes
	 */
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}

	public FileInfo(){
		
	}
	
	
	
	
}
