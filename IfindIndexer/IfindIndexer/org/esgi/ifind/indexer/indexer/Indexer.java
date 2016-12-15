package org.esgi.ifind.indexer.indexer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.lucene.analysis.fr.FrenchAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Fieldable;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.esgi.ifind.indexer.configuration.Configuration;
import org.esgi.ifind.indexer.dmoz.DmozService;
import org.esgi.ifind.indexer.gui.view.MonitoringView;
import org.esgi.ifind.indexer.indexer.parser.TikaParser;
import org.esgi.ifind.indexer.pagerank.PageRankService;


/**
 * Indexing class.
 *
 * @see IndexerService
 */
public class Indexer{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Lucene index writer. 
	 * @see Indexer#createIndexWriter(String) 
	 * */
	private IndexWriter writer;
	
	/** Path where indexes will be stored. 
	 * @see Indexer#createIndexWriter(String) 
	 * @see Configuration#getIndexPath() 
	 * */
	private String indexPath;
	
	/** Lucene Analyzer, here we use FrenchAnalyzer. 
	 * @see Indexer#Indexer() 
	 */
	private FrenchAnalyzer frAnalyer;
	
	/** Lucene configuration writer. 
	 * @see Indexer#createIndexWriter(String) 
	 */
	private IndexWriterConfig confWriter;
	
	/** Lucene Indexes Directory. 
	 * @see Indexer#createIndexWriter(String) 
	 */
	private FSDirectory directory;
	
	/** Items object which describe the meta data and the body of the page. 
	 * @see Items 
	 * @see TikaParser#getItems(File) 
	 * */
	private Items item;
	
	/** Lucene document. */
	Document doc;
	
	/**
	 * Constructor
	 * Initialize Analyzer, and indexPath.
	 *
	 * @see Indexer#frAnalyer
	 * @see Indexer#indexPath
	 */
	public Indexer(){
		frAnalyer=new FrenchAnalyzer(Version.LUCENE_31);
		indexPath=Configuration.getInstance().getIndexPath();
	}
	
	/**
	 * Create Lucene index writer.
	 *
	 * @param extension Extension of file to index if extension="img", the indexes will be stored in
	 * "ImgIndex" directory
	 * else "WebIndex"
	 * @return true, if successful
	 * true : if Indexer#writer is initialized else false
	 * @see  Indexer#indexData(File, String, String, float)
	 * @see Indexer#updatePageRank(HashMap)
	 */
	private boolean createIndexWriter(String extension){

		String indexPathData;
		indexPathData=indexPath;
		if(extension.equals("img"))
			indexPathData=indexPath+"ImgINDEX";
		else
			indexPathData=indexPath+"WebINDEX";
		try {
			directory=FSDirectory.open(new File(indexPathData));
			confWriter=new IndexWriterConfig(Version.LUCENE_31, frAnalyer);
			writer=new IndexWriter((Directory)directory,confWriter);
			return true;
		} catch (IOException e) {
			MonitoringView.getInstance().setTextAreaMonitoring("* Exception: Class Indexer - " +
					"Method createIndexWriter() : " +e.getMessage());
			return false;
		}
	}

/**
 * Function of indexing data.
 *
 * @param f File which will be indexed
 * @param url Url of the file
 * @param extension extension of the file
 * @param pageRank the page rank of the page
 * @return true if indexing was successful
 * @throws IOException Signals that an I/O exception has occurred.
 * @see TikaParser#getItems(File)
 */
	public  boolean indexData(File f,String url,String extension,float pageRank) throws IOException{
		
		item=new Items();
		doc =new Document();
		String description="";

		if(!extension.equals("img")){
			try {
				item=TikaParser.getInstance().getItems(f);
				description=item.getDescription();
				if(description==null)
					description = DmozService.getInstance().getDescriptionFromDmoz(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		item.setName(f.getName());
		item.setUrl(url);
		
		if ((item.getUrl() != null) && (!item.getUrl().equals(""))){
			doc.add(new Field("id",String.valueOf(url) ,Field.Store.YES,Field.Index.NOT_ANALYZED));
			doc.add(new Field("url",String.valueOf(url) ,Field.Store.YES,Field.Index.ANALYZED));
		}
		if ((item.getName() != null) && (!item.getName().equals("")))
			doc.add(new Field("name", item.getName(),Field.Store.YES,Field.Index.ANALYZED));
		if ((item.getTitle() != null) && (!item.getTitle().equals("")))
			doc.add(new Field("title", item.getTitle(),Field.Store.YES,Field.Index.ANALYZED));
		if ((item.getAuthor() != null) && (!item.getAuthor().equals("")))
			doc.add(new Field("author", item.getAuthor(),Field.Store.YES,Field.Index.ANALYZED));
		if ((item.getKeywords() != null) && (!item.getKeywords().equals("")))
			doc.add(new Field("keywords", item.getKeywords(),Field.Store.YES,Field.Index.ANALYZED));
		if ((item.getBody() != null) && (!item.getBody().equals(""))) 
			doc.add(new Field("body", item.getBody(),Field.Store.NO,Field.Index.ANALYZED));		
		if ((description != null) && (!description.equals("")))
			doc.add(new Field("description",description ,Field.Store.YES,Field.Index.ANALYZED));
		doc.add(new Field("pr",String.valueOf((float)pageRank) ,Field.Store.YES,Field.Index.NO));
		
		createIndexWriter(extension);
		writer.updateDocument(new Term("id",url), doc,frAnalyer);
		writer.optimize();
		writer.commit();
		writer.close();
		return true;
	}

/**
 * Function of updating the Lucene document page rank.
 *
 * @param map Key:String url of page ; Value:Double the page rank coefficient
 * @throws IOException Signals that an I/O exception has occurred.
 * @see PageRankService#run()
 */
	public void updatePageRank(HashMap<String, Double> map) throws IOException{
		String sUrl;
		double pr;
		Document newDoc=new Document();
		directory=FSDirectory.open(new File(indexPath+"WebINDEX"));
		try {
			for(Entry<String, Double>entry:map.entrySet()){
				sUrl=entry.getKey();
				pr=entry.getValue();
				Term term=new Term("id", sUrl);

				IndexReader reader=IndexReader.open(directory,false);
				TermDocs termDocs= reader.termDocs(term);

				if(termDocs.next()){
					MonitoringView.getInstance().setTextAreaMonitoring("\t\t-" + sUrl + " : PR = " + (float)pr);
					Document oldDoc=reader.document(termDocs.doc());
					newDoc=new Document();
					for(Fieldable oldField:oldDoc.getFields()){
						Field newField=(Field) oldField;
						if(newField.name().equals("pr"))
							newField.setValue(String.valueOf((float)pr));
						newDoc.add(newField);
					}
					reader.deleteDocuments(term);
					reader.close();
					createIndexWriter("html");
					writer.updateDocument(term, newDoc,frAnalyer);
					writer.optimize();
					writer.commit();
					writer.close();	
				}
				
			}

		} catch (CorruptIndexException e) {
			MonitoringView.getInstance().setTextAreaMonitoring("* Exception: Class Indexer - " +
					"Method updatePageRank() : " +e.getMessage());
		} catch (IOException e) {
			MonitoringView.getInstance().setTextAreaMonitoring("* Exception: Class Indexer - " +
					"Method updatePageRank() : " +e.getMessage());
		} 
		/*
		try {
			IndexSearcher searcher=new IndexSearcher(directory);
			MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_31,new String[] {"body"}, frAnalyer);
		    Query queryparser;
		    Sort sort =new Sort(new SortField("pr", SortField.FLOAT));
		    queryparser = parser.parse("societe");
		    TopDocs topDocs = searcher.search(queryparser, Integer.MAX_VALUE,sort);
			
			ScoreDoc[] scoreDosArray = topDocs.scoreDocs;
			for(int i =0;i<topDocs.totalHits;i++){
				Document doc=searcher.doc(scoreDosArray[i].doc);
				System.out.println(doc.getField("url").stringValue());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
}
