package org.esgi.ifind.indexer.indexer;
/**
 * Class describing the document meta data.
 */
public class Items {
	
	/** name of the file. @see Items#getName() @see Items#setName(String) */
	private String name;
	
	/** url of the file. @see Items#getUrl() @see Items#setUrl(String) */
	private String url;
	
	/** title of the file. @see Items#getTitle() @see Items#setTitel(String) */
	private String title;
	
	/** body of the file. @see Items#getBody() @see Items#setBody(String) */
	private String body;
	
	/** description of the file. @see Items#getDescription() @see Items#setDescription(String) */
	private String description;
	
	/** Keywords of the file. @see Items#getKeywords() @see Items#setKeywords(String) */
	private String keywords;
	
	/** author of the file. @see Items#getauthor() @see Items#setauthor(String) */
	private String author;
	
	/**
	 * Default constructor.
	 */
	public Items(){}
	
	/**
	 * Author getter.
	 *
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * Author setter.
	 *
	 * @param author the new author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * keywords getter.
	 *
	 * @return keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * keywords setter.
	 *
	 * @param keywords the new keywords
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	/**
	 * name getter.
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * name setter.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * url setter.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * title setter.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * body setter.
	 *
	 * @param body the new body
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
	/**
	 * description setter.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * url getter.
	 *
	 * @return url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * title getter.
	 *
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * body getter.
	 *
	 * @return body
	 */
	public String getBody() {
		return body;
	}
	
	/**
	 * description getter.
	 *
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	
}
