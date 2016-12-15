package bo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contains a project parameters
 */
public class ProjectParamsBO implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * Files being queued
	 * 
	 * @see ProjectParamsBO#getQueueUrlsList()
	 * @see ProjectParamsBO#setQueueUrlsList(ArrayList)
	 */
	private ArrayList<String> queueUrlsList;
	/**
	 * Files that failed to fetch
	 * 
	 * @see ProjectParamsBO#getErrorUrlsList()
	 * @see ProjectParamsBO#setErrorUrlsList(ArrayList)
	 */
	private ArrayList<String> errorUrlsList;
	/**
	 * Files that have been successfully fetched 
	 * 
	 * @see ProjectParamsBO#getCompletedUrlsList()
	 * @see ProjectParamsBO#setCompletedUrlsList(ArrayList)
	 */
	private ArrayList<String> completedUrlsList;
	
	/**
	 * Size of the project
	 * 
	 * @see ProjectParamsBO#getSize()
	 * @see ProjectParamsBO#setSize(double)
	 */
	private double size;

	/**
	 * name of the project
	 * 
	 * @see ProjectParamsBO#getProjectName()
	 * @see ProjectParamsBO#setProjectName(String)
	 */
	private String projectName;
	/**
	 * path to the project workspace
	 * 
	 * @see ProjectParamsBO#getProjectWorkspace()
	 * @see ProjectParamsBO#setProjectWorkspace(String)
	 */
	private String projectWorkspace;
	/**
	 * main url of the project
	 * 
	 * @see ProjectParamsBO#getUrlToGrab()
	 * @see ProjectParamsBO#setUrlToGrab(String)
	 */
	private String urlToGrab;
	/**
	 * set to true if it already started fetching
	 * 
	 * @see ProjectParamsBO#isAlreadyStarted()
	 * @see ProjectParamsBO#setAlreadyStarted(boolean)
	 */
	private boolean isAlreadyStarted;
	
	/**
	 * Returns the project status
	 * 
	 * @return a boolean set to true if the project has already started fetching
	 */
	public boolean isAlreadyStarted() {
		return isAlreadyStarted;
	}

	/**
	 * Updates the project status
	 * 
	 * @param isAlreadyStarted
	 * 		the new status
	 */
	public void setAlreadyStarted(boolean isAlreadyStarted) {
		this.isAlreadyStarted = isAlreadyStarted;
	}

	/**
	 * Returns the project name
	 * 
	 * @return a String containing the project name
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * Updates the project name
	 * 
	 * @param projectName
	 * 		the new project name
	 */
	public void setProjectName(String projectName) {
		this.projectName = "Grab_" + projectName;
	}

	/**
	 * Returns the project workspace
	 * 
	 * @return a string containing the project workspace
	 */
	public String getProjectWorkspace() {
		return projectWorkspace;
	}

	/**
	 * Updates the project workspace
	 * 
	 * @param projectWorkspace
	 * 		the new project workspace
	 */
	public void setProjectWorkspace(String projectWorkspace) {
		this.projectWorkspace = projectWorkspace;
	}

	/**
	 * Returns the main url
	 * 
	 * @return a string containing he main url of the project
	 */
	public String getUrlToGrab() {
		return urlToGrab;
	}

	/**
	 * Updates the main url
	 * 
	 * @param urlToGrab
	 * 		the new main url of the project
	 */
	public void setUrlToGrab(String urlToGrab) {
		this.urlToGrab = urlToGrab;
	}
	
	/**
	 * Returns erroneous files
	 * 
	 * @return a list containing the urls of the erroneous files
	 */
	public ArrayList<String> getErrorUrlsList() {
		return errorUrlsList;
	}

	/**
	 * Updates the erroneous files list
	 * 
	 * @param errorUrlsList
	 * 		the updated list of erroneous files
	 */
	public void setErrorUrlsList(ArrayList<String> errorUrlsList) {
		this.errorUrlsList = errorUrlsList;
	}
	
	/**
	 * Returns the list of urls on queue
	 * 
	 * @return a list containing the urls of files on queue
	 */
	public ArrayList<String> getQueueUrlsList() {
		return queueUrlsList;
	}

	/**
	 * Updates the files queue list
	 * 
	 * @param queueUrlsList
	 * 		the new list of queued files
	 */
	public void setQueueUrlsList(ArrayList<String> queueUrlsList) {
		this.queueUrlsList = queueUrlsList;
	}

	/**
	 * Returns urls completed
	 * 
	 * @return a list of urls successfully fetched
	 */
	public ArrayList<String> getCompletedUrlsList() {
		return completedUrlsList;
	}

	/**
	 * Updates urls completed
	 * 
	 * @param completedUrlsList
	 * 		the new list of successfuly downloaded files
	 */
	public void setCompletedUrlsList(ArrayList<String> completedUrlsList) {
		this.completedUrlsList = completedUrlsList;
	}

	/**
	 * Returns the project size
	 * 
	 * @return the project size
	 */
	public double getSize() {
		return size;
	}

	/**
	 * Updates the project size
	 * 
	 * @param size
	 * 		new size of the project
	 */
	public void setSize(double size) {
		this.size = size;
	}
}
