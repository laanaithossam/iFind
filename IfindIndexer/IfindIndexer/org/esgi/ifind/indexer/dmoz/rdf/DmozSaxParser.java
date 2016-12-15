package org.esgi.ifind.indexer.dmoz.rdf;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * The Class DmozSaxParser.
 */
public class DmozSaxParser extends Thread {
	
	/** The parent button. */
	JButton parentButton;
	
	/** The uri. */
	String uri;
	
	/**
	 * Instantiates a new dmoz sax parser.
	 *
	 * @param uri the uri
	 * @param parentButton the parent button
	 * @throws SAXException the sAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public DmozSaxParser(String uri,JButton parentButton) throws SAXException, IOException {
		this.parentButton=parentButton;	
		this.uri=uri;
	}
	
	/**
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		super.run();
		parentButton.setEnabled(false);
		XMLReader saxReader;
		try {
			saxReader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
			saxReader.setContentHandler(new DmozContentHandler());
			saxReader.parse(uri);
			parentButton.setEnabled(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(parentButton.getParent(), e.getMessage());
			parentButton.setEnabled(true);
		}
	}
}
