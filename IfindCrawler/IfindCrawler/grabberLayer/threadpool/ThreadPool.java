package grabberLayer.threadpool;

import grabberLayer.resource.RessourceJob;

import java.util.LinkedList;

import bo.TasksCollection;



public class ThreadPool {
	/**
	 * @see RessourceJob
	 */
	private LinkedList<RessourceJob> jobs = new LinkedList<RessourceJob>();
	/**
	 * set to true if started
	 */
	private boolean poolStarted=false;
	/**
	 * @see TasksCollection
	 */
	private TasksCollection tc;
	/**
	 * active threads
	 */
	private int activeThreads=0;
	/**
	 * Creates a threadPool
	 * 
	 * @param size
	 * 		number of threads
	 * @param tc
	 * 
	 * @see ThreadWorker
	 */
	public ThreadPool(int size,TasksCollection tc)
	{
		this.tc=tc;
		for(int i=0; i<size; i++)
		{
			ThreadWorker thread = new ThreadWorker(this);
			tc.addThread(thread);
			thread.start();
		}
	}
	/**
	 * increment active threads number
	 */
	protected synchronized void incrementActiveThread()
	{
		activeThreads++;
		tc.tcAvtiveThread++;
	}
	/**
	 * decrement active threads
	 */
	protected synchronized void decrementActiveThread()
	{
		activeThreads--;
		tc.tcAvtiveThread--;
	}
	
	/**
	 * runs a job
	 * @param job
	 * 		job to run
	 */
	public void run(RessourceJob job)
	{
		if(!poolStarted)
			poolStarted=true;
		
		synchronized(jobs)
		{
			jobs.addLast(job);
			if(tc!=null)
			jobs.notify();
		}
	}

	/**
	 * wait for the next job, and removes the first one
	 * 
	 * @return
	 * 		job
	 * @see RessourceJob
	 */
	public RessourceJob getNext()
	{
		if(poolStarted&&jobs.isEmpty()&&0>=activeThreads)
			return null;
		
		RessourceJob returnVal = null;
		// synchronized: Je garantie que sur cette période la je 
		// serais le seul à travailler sur l'objet
		
		synchronized(jobs)
		{
			while(jobs.isEmpty())
			{
				try
				{
					jobs.wait();
				}
				catch(InterruptedException ex)
				{
					System.err.println("Interrupted");
				}
			}
			returnVal = (RessourceJob) jobs.removeFirst();
		}
		return returnVal;
	}
}
