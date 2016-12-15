package org.esgi.ifind.indexer.watcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TimerTask;

import org.esgi.ifind.indexer.gui.view.MonitoringView;
import org.esgi.ifind.indexer.indexer.IndexerService;

/**
 * To monitor a directory.
 *
 * @see IndexerService
 */
public abstract class DirectoryWatcher extends TimerTask {
	
	/** The directory to monitor. */
	private File fileRoot;
	
	/** Path of the file listing the directories previously downloaded. */
	static final String historyPath="config/history.ser";
	
	/** The list of directories previously downloaded. @see getHistoryMap() */
	private HashMap<File,Long> dir = new HashMap<File, Long>();
	
	/** Variable to leave the DirectoryWatcher. */
	public boolean isRun;

/**
 * Constructor.
 *
 * @param fileRoot Supports setting a directory File
 * @see #getHistoryMap()
 */
	public DirectoryWatcher(File fileRoot) {
		isRun=true;
		this.dir=getHistoryMap();
		this.fileRoot=fileRoot;
	}

/**
 * Performs the function watch ().
 *
 * @see #watch(File)
 */
	public final void run() {
		watch(fileRoot);
	}

/**
 * Recursing the directories in the directory fileRoot.
 *
 * @param file the file
 * @return Faux: Quand isRun=false
 */
	public boolean watch(File file){
		if(!isRun)
			return false;
		if (!file.exists())
			this.cancel();
		HashSet<File> checkedFiles = new HashSet<File>();
		Long current;

		current = (Long)dir.get(file);
		checkedFiles.add(file);

		// scan the files and check for modification/addition
		if (current == null || current.longValue() != file.lastModified()) {
			dir.put(file, new Long(file.lastModified()));
			serializeHistoryMap();

			if(file.isFile())
				onChange(file);
		}

		if (file.isDirectory()){
			File[] filesArray = file.listFiles();
			if(filesArray!=null){
				for(int i = 0; i < filesArray.length; i++) {
					watch(filesArray[i]);
				}
			}
		}
		return true;
	}

/**
 * Serializes the list of directories searched.
 *
 */
	public void serializeHistoryMap() {
		try {
			FileOutputStream file = new FileOutputStream(historyPath);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(dir);
			out.close();
		}
		catch (Exception e) {
			MonitoringView.getInstance().setTextAreaMonitoring("* Exception: Class DirectoryWatcher - " +
					"Method serializeHistoryMap() : " +e.getMessage());
		}
	}

/**
 * Returns a list of directories / files, and their last modification date.
 *
 * @return HashMap<File, Long> : Key = Name directory / file, Value= modification date 
 */
	@SuppressWarnings("unchecked")
	public HashMap<File, Long> getHistoryMap(){
		HashMap<File, Long> map = new  HashMap<File, Long>();
		try {
			FileInputStream file = new FileInputStream(historyPath);
			ObjectInputStream in = new ObjectInputStream(file);
			map = (HashMap<File, Long>) in.readObject();
			in.close();
		}catch (Exception e) {
		}
		return map;
	}

/**
 * On change.
 *
 * @param file the file
 */
	protected abstract void onChange( File file);
}

