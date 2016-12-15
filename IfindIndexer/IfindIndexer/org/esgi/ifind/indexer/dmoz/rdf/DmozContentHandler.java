/*
 * 
 */
package org.esgi.ifind.indexer.dmoz.rdf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.esgi.ifind.indexer.dmoz.DmozDao;
import org.esgi.ifind.indexer.gui.view.MonitoringView;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.LocatorImpl;

/**
 * The Class DmozContentHandler.
 */
public class DmozContentHandler implements ContentHandler{
	
	/** The buffer. */
	private String buffer;
	
	/** The url. */
	private String url;
	
	/** The title. */
	private String title;
	
	/** The description. */
	private String description;
	
	/** The topic. */
	private String topic;
	
	/** The connection. */
	private Connection connection;
	
	/** The Constant SQL_INSERT. */
	public static final String SQL_INSERT = "INSERT INTO dmoz(url, title, description, topic) VALUES(?,?,?,?)";
	
	/**
	 * Instantiates a new dmoz content handler.
	 */
	public DmozContentHandler() {
		super();
		// Set the default locator.
		setLocator(new LocatorImpl());
	}

	/**
	 * Definition of a locator that allows any time during the analysis, 
	 * to localize treatment to the stream. The default locator indicates, 
	 * for example, the line number and character number on the line.
	 * @param value le locator a utiliser.
	 * @see org.xml.sax.ContentHandler#setDocumentLocator(org.xml.sax.Locator)
	 */
	public void setDocumentLocator(Locator value) {
		setLocator(value);
	}

	/**
	 * Event sent when starting the parse the XML stream.
	 * @throws SAXException if problem not allowing
	 * engage in the analysis of the document.
	 * @see org.xml.sax.ContentHandler#startDocument()
	 */
	public void startDocument() throws SAXException {
		MonitoringView.getInstance().setTextAreaMonitoring(" + DMOZ : Analyzing...");
		connection=DmozDao.getInstance().getConnection();
	}

	/**
	 * Event sent at the end of the xml flow analysis.
	 * @throws SAXException if problem does not allow 
	 * to consider the analysis of the document as complete.
	 * @see org.xml.sax.ContentHandler#endDocument()
	 */
	public void endDocument() throws SAXException {
		MonitoringView.getInstance().setTextAreaMonitoring(" + DMOZ : Analyze finished");
	}

	/**
	 * Beginning of program in a namespace.
	 *
	 * @param prefix the prefix
	 * @param URI of the namespace.
	 * @throws SAXException the sAX exception
	 * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String, java.lang.String)
	 */
	public void startPrefixMapping(String prefix, String URI) throws SAXException {
		//System.out.println("Traitement de l'espace de nommage : " + URI + ", prefixe choisi : " + prefix);
	}

	/**
	 * End processing namespace.
	 *
	 * @param prefix the prefix
	 * @throws SAXException the sAX exception
	 * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
	 */
	public void endPrefixMapping(String prefix) throws SAXException {
		//System.out.println("Fin de traitement de l'espace de nommage : " + prefix);
	}

	/**
	 * Received event every time the parser encounters an opening XML tag.
	 *
	* @param nameSpaceURI the URL of the namespace.
	* @param localName the local name of the tag.
	* @param rawName tag name in version 1.0 <code> namespaceURI + ":" + localName </ code>
	* @param attributs the attributes
	* @throws SAXException if the tag does not correspond to what is expected,
	* Such as non compliance with a dtd.
	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String nameSpaceURI, String localName, String rawName, Attributes attributs) throws SAXException {
		MonitoringView.getInstance().setTextAreaMonitoring(" + DMOZ : Opening tag: " + localName);
		
		if(localName.equals("ExternalPage")){
			this.url = attributs.getValue(0);
		}
	}

	/**
	* Event received at each closing tag.
	*
	* @param nameSpaceURI the uri name space
	* @param localName the local name
	* @param rawName rawName the raw
	* @throws SAXException SAX exception the
	* @see #org.xml.sax.ContentHandler (java.lang.String, java.lang.String, java.lang.String)
	*/
	public void endElement(String nameSpaceURI, String localName, String rawName) throws SAXException {
		if(localName.equals("Title")){
			this.title = buffer;
		}
		else if(localName.equals("Description")){
			this.description = buffer;
		}
		else if(localName.equals("topic")){
			this.topic = buffer;
		}
		
		if(localName.equals("ExternalPage")){
			try{
				PreparedStatement ps = connection.prepareStatement(SQL_INSERT);
				ps.setString(1, this.url);
				ps.setString(2, this.title);
				ps.setString(3, this.description);
				ps.setString(4, this.topic);
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	* Event received every time the parser encounters characters (between
	* Two tags).
	*
	* @param ch the characters themselves.
	* @param start the first row of the character was actually treated.
	* @param end the rank of the last character to deal effectively
	* @throws SAXException SAX exception the
	* @see #org.xml.sax.ContentHandler characters (char [], int, int)
	*/
	public void characters(char[] ch, int start, int end) throws SAXException {
		//System.out.println("#PCDATA : " + new String(ch, start, end));
		MonitoringView.getInstance().setTextAreaMonitoring(" + DMOZ : #PCDATA : " + new String(ch, start, end));
		this.buffer = new String(ch, start, end);
	}

	/**
	* Received whenever space characters can be ignored for the purposes of
	* XML. That is to say that this event is sent to several areas in succession,
	* Tabs, carriage returns, and succeeding as well as any combination of these
	* Three types of occurrence.
	*
	* @param ch the characters themselves.
	* @param start the first row of the character was actually treated.
	* @param end the rank of the last character to deal effectively
	* @throws SAXException SAX exception the
	* @see #org.xml.sax.ContentHandler ignorableWhitespace (char [], int, int)
	*/
	public void ignorableWhitespace(char[] ch, int start, int end) throws SAXException {
	}

	/**
	* Meet an operating instruction.
	*
	*@param target the target of the operation command.
	*@param data the values ​​associated with that target. In general, it is presented as
	* A series of name / value pairs.
	*@throws SAXException SAX exception the
	*@see #org.xml.sax.ContentHandler ProcessingInstruction (java.lang.String, java.lang.String)
	*/

	public void processingInstruction(String target, String data) throws SAXException {
	}

	/**
	*Received each time a tag is avoided in the treatment due to
	*Non-blocking problem by the parser. For my part I do not think you
	*Need them in your treatment.
	*
	*@param arg0 the arg0
	*@throws SAXException SAX exception the
	*@see #org.xml.sax.ContentHandler skippedEntity (java.lang.String)
	*/
	public void skippedEntity(String arg0) throws SAXException {            
	}

	/**
	 * Sets the locator.
	 *
	 * @param locator the new locator
	 */
	public void setLocator(Locator locator) {
		this.locator = locator;
	}

	/**
	 * Gets the locator.
	 *
	 * @return the locator
	 */
	public Locator getLocator() {
		return locator;
	}

	/**
	 * Sets the connection.
	 *
	 * @param connection the new connection
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/** The locator. */
	private Locator locator;
}
