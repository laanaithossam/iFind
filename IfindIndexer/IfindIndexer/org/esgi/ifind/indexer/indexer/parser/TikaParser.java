package org.esgi.ifind.indexer.indexer.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.esgi.ifind.indexer.indexer.Items;
import org.xml.sax.SAXException;

/**
 * Class parser
 * It's allow to parse a large kind of documents
 * This class implements singleton design  pattern.
 */
public class TikaParser {
	
	/** The instance. */
	static TikaParser instance=null;
	private Tika tika;
/**
 * Default Constructor.
 */
	private TikaParser() {
		tika=new Tika();
		tika.setMaxStringLength(10*1024*1024);
	}

/**
 * Gets the single instance of TikaParser.
 *
 * @return the unique instance of the class
 */
	public static TikaParser getInstance(){
		if(null==instance)
			instance=new TikaParser();
		return instance;
	}

/**
 * Gets the items.
 *
 * @param f : file which will be parsed
 * @return an {@link Items} object
 * @throws IOException Signals that an I/O exception has occurred.
 * @throws SAXException the sAX exception
 * @throws TikaException the tika exception
 * @see Items
 */
	public Items getItems(File f) throws IOException, SAXException, TikaException {
		Items item=new Items();
		Metadata metadata = new Metadata();
		metadata.set(Metadata.RESOURCE_NAME_KEY,f.getName()); 
		InputStream is = new FileInputStream(f);
		String content = tika.parseToString(is, metadata);
		item.setBody(content);
		
		for(String name : metadata.names()) {        
			String value = metadata.get(name);
			if(name.equalsIgnoreCase("title"))
				item.setTitle(value);
			else if(name.equalsIgnoreCase("description"))
				item.setDescription(value);
			else if(name.equalsIgnoreCase("subject"))
				item.setDescription(value);
			else if(name.equalsIgnoreCase("author"))
				item.setAuthor(value);
			else if(name.equalsIgnoreCase("keywords"))
				item.setKeywords(value);
		}
		return item;
	}
}
