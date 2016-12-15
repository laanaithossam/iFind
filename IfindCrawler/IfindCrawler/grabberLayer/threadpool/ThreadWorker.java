package grabberLayer.threadpool;

import grabberLayer.resource.RessourceJob;

public class ThreadWorker extends Thread{
	/**
	 * threads list
	 * @see ThreadPool
	 */
	private ThreadPool pool;
	/**
	 * is running if set to true
	 */
	private Boolean isRunning = true;
	/**
	 * a job
	 * @see RessourceJob
	 */
	private RessourceJob job;
	
	/**
	 * Constructor
	 * @param pool
	 */
	public ThreadWorker(ThreadPool pool)
	{
		this.pool = pool;
	}
	
	/**
	 * stops the jop
	 * 
	 * @see RessourceJob#stopJob()
	 */
	public void stopWorker(){
		isRunning =false;
		if(job!=null) {
			job.stopJob();
		}
	}
	
	/**
	 * runs a job
	 * 
	 * @see ThreadPool#getNext()
	 * @see ThreadPool#incrementActiveThread()
	 * @see ThreadPool#decrementActiveThread()
	 */
	public void run()
	{
		while(isRunning){
			//blocks until job
			job=pool.getNext();
			if(job==null)
				return;
			try
			{
				pool.incrementActiveThread();
				job.run();
				pool.decrementActiveThread();
			}
			catch(Exception e){
				System.out.println("Job exception: "+e);
			}
		}	
	}
}

