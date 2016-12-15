package thread;

import bo.TasksCollection;
import grabberLayer.Grabber;

public class StartNewGrabber extends Thread {
	private TasksCollection tc;
	/**
	 * url to grab
	 */
	private String url;
	/**
	 * project path
	 */
	private String myProjectPath;
	/**
	 * an instance of the grabber
	 * 
	 * @see Grabber
	 */
	private Grabber g;
	public StartNewGrabber(TasksCollection tc,String myProjectPath,String url){
		this.tc=tc;
		this.myProjectPath=myProjectPath;
		this.url=url;
		g = new Grabber(this.tc,this.myProjectPath,this.url);
	}

	/**
	 * start grabber
	 * 
	 * @see Grabber
	 */
	public void run() {
		g.startGrab();
	}
}
