package bo;

import grabberLayer.threadpool.ThreadWorker;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import tools.Log;
import view.MainView;

/**
 * Contains the tasks relative to a project
 */
public class TasksCollection {
	/**
	 * Main view containing a project status
	 * 
	 * @see MainView
	 * @see TasksCollection#getProjectViewIdentifier()
	 */
	private MainView projectViewIdentifier;

	/**
	 * list of used panels
	 * 
	 * @see TasksCollection#getListPanel()
	 */
	private ArrayList<JPanel> listPanel;
	/**
	 * list of used threads
	 * 
	 * @see TasksCollection#addThread(ThreadWorker)
	 */
	public ArrayList<ThreadWorker> listThread;
	/**
	 * number of errors
	 * 
	 * @see TasksCollection#setCountError(int)
	 * @see TasksCollection#addErrorUrl(String)
	 */
	private int countError;
	/**
	 * number of terminated files
	 * 
	 * @see TasksCollection#setCountCompleted(int)
	 * @see TasksCollection#addCompletedUrl(String)
	 */
	private int countCompleted;
	/**
	 * number on queue
	 * 
	 * @see TasksCollection#setCountDiscovered(int)
	 * @see TasksCollection#addDiscoveredUrl(String)
	 * @see TasksCollection#removeDiscoverdUrl(String)
	 */
	private int countQueue;
	/**
	 * number of files 
	 * 
	 * @see TasksCollection#getCountSize()
	 * @see TasksCollection#setCountSize(double)
	 * @see TasksCollection#calculateSize(int)
	 */
	private double countSize;
	/**
	 * list of erroneous urls
	 * 
	 * @see TasksCollection#getErrorUrlsList()
	 * @see TasksCollection#addErrorUrl(String)
	 */
	private ArrayList<String> errorUrlsList;
	/**
	 * The project
	 * 
	 * @see ProjectParamsBO
	 * @see TasksCollection#updateGUI()
	 */
	public ProjectParamsBO pp;
	/**
	 * active threads
	 */
	public int tcAvtiveThread;
	/**
	 * The file
	 * 
	 * @see FileInfo
	 * @see TasksCollection#getFi()
	 * @see TasksCollection#setFi(FileInfo)
	 */
	private FileInfo fi;
	/**
	 * log file 
	 * 
	 * @see Log
	 * @see TasksCollection#populateLog()
	 */
	public Log log;
	
	/**
	 * Constructor
	 * 
	 * @param projectViewIdentifier
	 * 		The current project id
	 * 
	 * @see FileInfo#FileInfo()
	 * @see ProjectParamsBO#ProjectParamsBO()
	 */
	public TasksCollection(MainView projectViewIdentifier){
		this.projectViewIdentifier=projectViewIdentifier;
		log=new Log();
		listPanel =new ArrayList<JPanel>();
		listThread =new ArrayList<ThreadWorker>();
		errorUrlsList=new ArrayList<String>();
		fi=new FileInfo();
		pp= new ProjectParamsBO();
		countError=0;
		countCompleted=0;
		countQueue=0;
		countSize=0;
	}

	/**
	 * Returns the panels list
	 * 
	 * @return list of panels
	 */
	public ArrayList<JPanel> getListPanel(){
		return listPanel;
	}

	/**
	 * Updates number of errors
	 * 
	 * @param countError
	 * 		the new number of errors
	 */
	public void setCountError(int countError) {
		this.countError = countError;
	}

	/**
	 * Updates the number of completed files
	 * 
	 * @param countCompleted
	 * 		the new number of completed files
	 */
	public void setCountCompleted(int countCompleted) {
		this.countCompleted = countCompleted;
	}

	/**
	 * Updates the number of discovered files
	 * 
	 * @param countDiscovered
	 * 		the new number of discovered files
	 */
	public void setCountDiscovered(int countDiscovered) {
		this.countQueue = countDiscovered;
	}

	/**
	 * The number of files
	 * 
	 * @param countSize
	 * 		the new number of total files
	 */
	public void setCountSize(double countSize) {
		this.countSize = countSize;
	}

	/**
	 * Returns the number of files
	 * 
	 * @return the number of files
	 */
	public double getCountSize() {
		return countSize;
	}

	/**
	 * Links a thread to the current project
	 * 
	 * @param thread
	 * 		the thread to be linked
	 * 
	 * @see ThreadWorker
	 */
	public void addThread(ThreadWorker thread){
		if(!listThread.contains(thread))
			listThread.add(thread);
	}

	/**
	 * Returns the project view ID
	 * 
	 * @return the project view ID
	 */
	public MainView getProjectViewIdentifier() {
		return projectViewIdentifier;
	}
	
	/**
	 * Returns a file
	 * 
	 * @return a file information
	 */
	public FileInfo getFi() {
		return fi;
	}

	/**
	 * Updates a file information
	 * 
	 * @param fi
	 * 		new files info
	 */
	public void setFi(FileInfo fi) {
		this.fi = fi;
	}
	
	/**
	 * creates a list of tasks
	 * 
	 * @param jpbar
	 * 		progress bar
	 * @param fi
	 * 		file info
	 * @param jlblFilename
	 * 		file name label
	 * @param jlblPercent
	 * 		percent label
	 * @param jpanel
	 * 		panel containing the tasks list
	 * 
	 * @see MainView#getThreadPan()
	 * @see MainView#updatePan()
	 */
	public void setListTask(JProgressBar jpbar,FileInfo fi,JLabel jlblFilename,JLabel jlblPercent,JPanel jpanel) {
		this.listPanel.add(jpanel);
		jpanel.add(jlblFilename);
		jpanel.add(jpbar);
		jpanel.add(jlblPercent);
		projectViewIdentifier.getThreadPan().add(jpanel);
		projectViewIdentifier.updatePan();
		this.fi=fi;
	}
	/**
	 * Deletes a panel
	 * 
	 * @param jpanel
	 * 		the panel to be deleted
	 * 
	 * @see MainView#getThreadPan()
	 * @see MainView#updatePan()
	 */
	public void deleteItemPanel(JPanel jpanel){
		this.listPanel.remove(jpanel);
		projectViewIdentifier.getThreadPan().remove(jpanel);
		projectViewIdentifier.updatePan();
	}
	/**
	 * Deletes all jpanels
	 * 
	 * @see MainView#getThreadPan()
	 * @see MainView#updatePan()
	 */
	public void deleteAllItemPanel(){
		for(JPanel j:listPanel){
			projectViewIdentifier.getThreadPan().remove(j);
			projectViewIdentifier.updatePan();
		}
	}
	
	/**
	 * Return errors
	 * 
	 * @return the list of erroneous urls
	 */
	public ArrayList<String> getErrorUrlsList() {
		return errorUrlsList;
	}
	
	/**
	 * Adds a discovered url to the list of discovered urls
	 * 
	 * @param url
	 * 		the new url discovered
	 */
	synchronized public void addDiscoveredUrl(String url){
		countQueue++;
		projectViewIdentifier.getTextAreaDiscovery().append(url + "\r\n");
		updateDiscovereddBorderTitle();
	}
	
	/**
	 * Removes an url from the list of discovered urls
	 * 
	 * @param url
	 * 		the url tu be removed
	 */
	synchronized public void removeDiscoverdUrl(String url){
		countQueue--;
		projectViewIdentifier.getTextAreaDiscovery().setText(projectViewIdentifier.getTextAreaDiscovery().getText().replace(url, ""));
		updateDiscovereddBorderTitle();
	}
	
	/**
	 * Adds an url to the list of erroneous urls
	 * 
	 * @param url
	 * 		the erroneous url 
	 */
	synchronized public void addErrorUrl(String url){
		if (!this.errorUrlsList.contains(url)){
			countError++;
			errorUrlsList.add(url);
			updateCompletedBorderTitle();
		}
	}
	
	/**
	 * Adds an url to the completed list
	 * 
	 * @param url
	 * 		the completed url
	 */
	synchronized public void addCompletedUrl(String url){
		countCompleted++;
		projectViewIdentifier.getTextAreaCompleted().append(url + "\r\n");
		updateCompletedBorderTitle();
	}
	
	/**
	 * Calculates the size of the project
	 * 
	 * @param i
	 * 		the number of files to add to the project size
	 */
	synchronized public void calculateSize(int i){
		countSize+=i;
	}
	
	/**
	 * updates the number of completed files on the title
	 * 
	 * @see MainView#getTextAreaCompleted()
	 */
	public void updateCompletedBorderTitle(){
		projectViewIdentifier.getTextAreaCompleted().setBorder(BorderFactory.createTitledBorder(
													"Completed(" +countCompleted +")" +
													" - Total downloaded Size("+ Math.floor(((countSize* 7.62939453 * Math.pow(10,-6))*100+0.5)/100) + " Mb)" +
													" - Errors("+countError +")"));	
	}
	
	/**
	 * updates the number of discovered files on the title
	 * 
	 * @see MainView#getTextAreaDiscovery()
	 */
	public void updateDiscovereddBorderTitle(){
		projectViewIdentifier.getTextAreaDiscovery().setBorder(BorderFactory.createTitledBorder("Queue("+countQueue+")"));
	}
	
	/**
	 * Updates the GUI
	 * 
	 * @see ProjectParamsBO#getSize()
	 * @see ProjectParamsBO#getErrorUrlsList()
	 * @see ProjectParamsBO#getCompletedUrlsList()
	 * @see ProjectParamsBO#getQueueUrlsList()
	 * @see MainView#getTextAreaDiscovery()
	 * @see MainView#getTextAreaCompleted()
	 * @see TasksCollection#updateCompletedBorderTitle()
	 * @see TasksCollection#updateDiscovereddBorderTitle()
	 */
	public void updateGUI(){
		countSize=pp.getSize();
		countError=pp.getErrorUrlsList().size();
		countCompleted=pp.getCompletedUrlsList().size();
		countQueue=pp.getQueueUrlsList().size();
		
		for (String s:pp.getQueueUrlsList()){
			projectViewIdentifier.getTextAreaDiscovery().append(s+"\r\n");
		}
		for (String s:pp.getCompletedUrlsList()){
			projectViewIdentifier.getTextAreaCompleted().append(s+"\r\n");
		}
		updateCompletedBorderTitle();
		updateDiscovereddBorderTitle();	
	}
	
	/**
	 * Resets GUI
	 * 
	 * @see MainView#getTextAreaCompleted()
	 * @see MainView#getTextAreaDiscovery()
	 * @see TasksCollection#updateCompletedBorderTitle()
	 * @see TasksCollection#updateDiscovereddBorderTitle()
	 */
	public void razGUI(){
		errorUrlsList=new ArrayList<String>();
		countSize=0;
		countError=0;
		countCompleted=0;
		countQueue=0;
		projectViewIdentifier.getTextAreaCompleted().setText("");
		projectViewIdentifier.getTextAreaDiscovery().setText("");
		updateCompletedBorderTitle();
		updateDiscovereddBorderTitle();	
	}
	
	/**
	 * Write log to file
	 */
	public void populateLog(){
		
		ArrayList<String> myList=new ArrayList<String>();
		
		log.fill(pp.getUrlToGrab(),log.Titre1);
		log.fill("-Nombre des URL téléchargeés  : " + countCompleted, log.LigneNormal);
		log.fill("-Taille globale des fichiers téléchargés : " + Math.floor(((countSize* 7.62939453 * Math.pow(10,-6))*100+0.5)/100) + " Mb", log.LigneNormal);
		log.fill("-Nombre des URL restant à grabber  : " + countQueue, log.LigneNormal);
		log.fill("-Nombre des URL erronées  : " + countError, log.LigneAlerte);
		
		myList=new ArrayList<String>();
		projectViewIdentifier.getTextAreaCompleted().AddUrls(myList);
		log.fill("+Liste des URL téléchargées",log.ListeNormal);
		for(String s:myList){
			log.fill(s,log.LigneNormal);
		}
		
		log.fill("+Liste des URL erronées",log.ListeAlerte);
		
		for(String s:errorUrlsList){
			log.fill(s,log.LigneAlerte);
		}
		
		myList=new ArrayList<String>();
		projectViewIdentifier.getTextAreaDiscovery().AddUrls(myList);
		log.fill("+Liste des URL restant à grabber",log.ListeNormal);
		
		for(String s:myList){
			log.fill(s,log.LigneNormal);
		}
		
		log.end();
		
		try {
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + log.logFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
