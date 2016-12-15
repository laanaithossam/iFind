package org.esgi.ifind.indexer.indexer;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.esgi.ifind.indexer.configuration.Configuration;
import org.esgi.ifind.indexer.gui.SystemTrayView;
import org.esgi.ifind.indexer.gui.view.MonitoringView;
import org.esgi.ifind.indexer.pagerank.GooglePageRank;
import org.esgi.ifind.indexer.pagerank.IfindPageRank;
import org.esgi.ifind.indexer.watcher.DirectoryWatcher;


/**
 * Service of the process of indexing.
 *
 * @see SystemTrayView#getIndexerService()
 * @see SystemTrayView#initIndexerService()
 */
public class IndexerService extends Thread {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The indexer. {@link Indexer} object 
	 * @see IndexerService#IndexerService() 
	 * @see IndexerService#runIndexer(File) 
	 */
	private Indexer indexer;

	/** Directory of files to index. */
	private File directory;

	/** timer task. 
	 * @see DirectoryWatcher 
	 * @see IndexerService#run() 
	 */
	private TimerTask task;

	/** The path of data which will be indexed. 
	 * @see Configuration#getDataPath() 
	 */
	private String dataPath;

	/** Timer. */
	private Timer timer;

	/** IfindPageRank object. 
	 * @see IfindPageRank 
	 * @see IndexerService#IndexerService() 
	 * @see IndexerService#runIndexer(File) */
	private IfindPageRank iFindPR;

	/** Variable allowing to control the service. */
	public boolean isRun=true;

	/**
	 * Constructor.
	 */
	public IndexerService() {
		dataPath=Configuration.getInstance().getDataPath();
		directory=new File(dataPath);
		iFindPR=new IfindPageRank();
		indexer=new Indexer();
	}

	/**
	 * Run method, initialize a new {@link DirectoryWatcher} task.
	 */
	public void run()
	{
		task=new DirectoryWatcher(directory) {
			@Override
			protected void onChange(File file) {
				if(isRun)
					runIndexer(file);
			}
		};

		timer = new Timer();
		timer.schedule( task , new Date(), 1000 );
	}

	/**
	 * Function which call the {@link Indexer} class to index the file.
	 *
	 * @param file The file which has been detected by the {@link DirectoryWatcher} task
	 * @see Indexer#indexData(File, String, String, float)
	 * @see IfindPageRank#setOutboundMap(String, File)
	 */
	private  boolean runIndexer(File file){
		String extension="";
		String url="";
		String fileName=file.getName();

		if (!fileName.isEmpty()){
			try {
				extension=fileName.split("\\.")[1];
				if(extension.equals("png")||extension.equals("jpg")||extension.equals("gif"))
					extension="img";
				if(extension.equals("doc")||extension.equals("docx"))
					extension="doc";
			} catch (Exception e) {
				extension="html";
			}
		}
		if(extension.equals("html") ||extension.equals("pdf")||extension.equals("doc")||extension.equals("img"))
			try {
				String[] path;
				url=file.getPath().replace(directory.getPath(), "");
				if (file.getName().equals("index.html"))
					url=url.replace("index.html", "");
				path=url.split("\\\\");
				if(path[1].contains("Grab_")){
					if(file.getName().contains("Grab_"))
						return false;
					url=url.replace(path[1] + "\\", "");
				}
				url="http:/" + url.replaceAll("\\\\", "/");

				if(extension.equals("html"))
					iFindPR.setOutboundMap(url,file);
				if(Boolean.valueOf(Configuration.getInstance().getGooglePr()))
					indexer.indexData(file,url,extension,GooglePageRank.getInstance().getPR(url));
				else
					indexer.indexData(file,url,extension,0.0001f);
				MonitoringView.getInstance().setTextAreaMonitoring("  - "+ url +" Indexed.");

			} catch (IOException e) {
				MonitoringView.getInstance().setTextAreaMonitoring("* Exception: Class IndexerService - " +
						"Method runIndexer() : " +e.getMessage());
			}
			return true;
	}

	/**
	 * Stop the indexing service.
	 */
	public void stopIndexer(){
		isRun=false;
		((DirectoryWatcher)task).isRun=false;
	}

}
