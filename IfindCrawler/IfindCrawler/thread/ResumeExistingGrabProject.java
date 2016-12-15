package thread;

import grabberLayer.Grabber;
import java.util.ArrayList;

import bo.TasksCollection;

public class ResumeExistingGrabProject extends Thread {
	/**
	 * urls to fetch
	 */
	private ArrayList<String> urls;
	/**
	 * an instance of the grabber
	 */
	private Grabber g;
	/**
	 * a list of tasks
	 */
	private TasksCollection tc;
	/**
	 * project's path
	 */
	private String myProjectPath;
 
	/**
	 * resumes an existing grab project
	 * 
	 * @param tc
	 * 		a new tasks collection
	 * @param myProjectPath
	 * 		the new project path
	 * @param urls
	 * 		urls to fetch
	 * 
	 * @see Grabber#Grabber(TasksCollection, String)
	 */
	public ResumeExistingGrabProject(TasksCollection tc,String myProjectPath,ArrayList<String>urls){
		this.urls = urls;
		this.tc=tc;
		this.myProjectPath=myProjectPath;
		g=new Grabber(this.tc,this.myProjectPath);
	}
	
	/**
	 * launches the resume
	 * 
	 * @see Grabber#resumeGrab(ArrayList)
	 */
	public void run() {
		g.resumeGrab(urls);
	}
}
