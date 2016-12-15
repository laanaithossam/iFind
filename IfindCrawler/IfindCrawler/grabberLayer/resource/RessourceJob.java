package grabberLayer.resource;

import bo.TasksCollection;
import grabberLayer.Grabber;

public class RessourceJob extends Thread {
	private Grabber grabber;
	private String url;
	private TasksCollection tc;
	private String myProjectPath;
	private boolean stopThread = false;
	
	public RessourceJob(Grabber grabber, String url,TasksCollection tc,String myProjectPath) {
		this.grabber = grabber;
		this.url = url;
		this.tc = tc;
		this.myProjectPath=myProjectPath;
	}
	
	public synchronized void testFin() throws InterruptedException {
	        if( stopThread ) {
	                throw new InterruptedException();
	        } 
	} 
	
	public synchronized void stopJob() {
        this.stopThread = true;
	} 

	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getId() + " - Processing : "+url);
			testFin();
			Ressource resource = new Ressource(url,this.tc,myProjectPath);
			tc.removeDiscoverdUrl(url + "\r\n");
			grabber.setUrlVisited(url);
			grabber.addUrls(resource.getUrls());
		} catch (InterruptedException e) {
		}
	}
}
