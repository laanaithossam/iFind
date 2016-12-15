package grabberLayer;

import grabberLayer.resource.RessourceJob;
import grabberLayer.threadpool.ThreadPool;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import bo.TasksCollection;





public class Grabber {

	public SortedSet<String> history = new TreeSet<String>();
	TasksCollection tc;
	private String myProjectPath;
	private String url;
	private int maxThread = 5;
	ThreadPool pool;
	
	public void resumeGrab(ArrayList<String> urls){
		int index = 0;
		String [] url = new String[urls.size()];
		for(String s : urls) {
			url[index] = s;
			index++;
		}
		addUrls(url);
	}
	
	public void addUrls(String[] urls)
	{
		synchronized(history)
		{
			for(String url:urls)
				if(!history.contains(url))
					pool.run(new RessourceJob(this,url,this.tc,this.myProjectPath));
		}
	}
	public void setUrlVisited(String url)
	{
		synchronized(history)
		{
			history.add(url);
		}
	}
	
	public void startGrab(){
		addUrls(new String[]{this.url});
	}
	public Grabber(TasksCollection tc,String myProjectPath){ 
		this.tc=tc;
		this.myProjectPath=myProjectPath;
		pool = new ThreadPool(maxThread,tc);
	}
	public Grabber(TasksCollection tc,String myProjectPath,String url){ 
		this.tc=tc;
		this.myProjectPath=myProjectPath;
		this.url=url;
		pool = new ThreadPool(maxThread,tc);
	}
	
	public int getMaxThread(){
		return this.maxThread;
	}
}
