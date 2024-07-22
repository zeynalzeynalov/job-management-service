package CaseStudyCodingTask.JobManagementService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;

import job.IJob;
import job.JobPriorityManager;

/**
 * 
 * Thread Safe Singleton Design Pattern applied to have only unique instance of JobManagementService.
 *
 */

public class JobManagementService {

	private static JobManagementService instanceOfSingleton;
	private PriorityQueue<IJob> jobQueue;
    private static List<IJob> jobHistoryList;
	
	private JobManagementService() {
		
		this.jobQueue = new PriorityQueue<IJob>();
		JobManagementService.jobHistoryList = new ArrayList<IJob>();
		
		JobPriorityManager.getInstance().loadJobPriorityList(ConfigurationManager.getInstance().getProperty("JobPriorityList").split(";"));
	}
	
	public static synchronized JobManagementService getInstance(){
        if(instanceOfSingleton == null){
        	
        	instanceOfSingleton = new JobManagementService();
        }
        
        return instanceOfSingleton;
    }
	
	public void addJob(IJob job) {
		
		this.jobQueue.add(job);
	}
	
	public void addJobList(List<IJob> jobList) {
		
		for(IJob job : jobList)
			this.jobQueue.add(job);
	}
	
	public void start() {
	
		System.out.println(String.format("\n[ Total count of jobs in queue: %d ]", jobQueue.size()).toUpperCase());
	
		long maxStartDelay = -1;
		
		while(!jobQueue.isEmpty()) {
        	
         	IJob job = jobQueue.poll();
        	
        	if(job.getStartDelay() == 0)
        	{
	        	Thread threadTask = new Thread(job, job.getName());
	        	threadTask.setPriority(job.getPriority().getValue());
	        	threadTask.start();
        	}
        	else
        	{
        		Timer timerTask = new Timer(true);
        		timerTask.schedule((TimerTask)job, job.getStartDelay());
        	}
        	
        	jobHistoryList.add(job);
        	
        	if(maxStartDelay < job.getStartDelay())
        		maxStartDelay = job.getStartDelay();
		}
		
        try {
            Thread.sleep(maxStartDelay + 5 * 1000 + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
    public List<IJob> getJobHistoryList() {
    	
		return jobHistoryList;
	}
    
    public void printJobHistoryList() {
    	
    	System.out.println("\n[ JOB EXECUTION HISTORY: ]");
		
    	for (Iterator<IJob> iterator = jobHistoryList.iterator(); iterator.hasNext();) {
			IJob iJob = (IJob) iterator.next();
			iJob.print();
		}
	}
    
    public void printJobQueue() {
    	
    	System.out.println("[ JOB WAITING QUEUE: ]");
		
    	Object[] tempJobList = jobQueue.toArray();
    			
		Arrays.sort(tempJobList);
    	
    	for (Object job : tempJobList) {
    		
			((IJob)job).print();
		}
	}

	public int getJobQueueSize() {

		return jobQueue.size();
	}

	public IJob getJobQueuePeek() {

		return jobQueue.peek();
	}
	
	public void reset() {

		jobQueue.clear();
		jobHistoryList.clear();
	}
}
