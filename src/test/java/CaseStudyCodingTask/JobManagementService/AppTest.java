package CaseStudyCodingTask.JobManagementService;

import java.util.Iterator;
import java.util.List;

import job.IJob;
import job.JobPriorityManager;
import jobtypes.JobFactory;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for JobManagementService application.
 */
public class AppTest extends TestCase {

	private int maxStartDelayMilliseconds = 5 * 1000;
	private int countOfJobsToCreate = 5;

	private JobFactory jobFactory = new JobFactory();
	
	public AppTest( String testName ) {
        
		super( testName );
    }

    public static Test suite() {
        
    	return new TestSuite( AppTest.class );
    }

    public void testJobWaitingQueueJobCount() {
    		
    	JobManagementService.getInstance().reset();

        JobManagementService.getInstance().addJobList(jobFactory.generateRandomTypeJobs(countOfJobsToCreate, maxStartDelayMilliseconds));
        
        assertEquals( countOfJobsToCreate, JobManagementService.getInstance().getJobQueueSize() );    
    }
    
    public void testJobWaitingQueueOrderingWithPriority() {
    	    	
    	JobManagementService.getInstance().reset();
    	
        List<IJob> jobList = jobFactory.generateRandomTypeJobs(countOfJobsToCreate, maxStartDelayMilliseconds);
        
        JobManagementService.getInstance().addJobList(jobList);
        
        int maxPriorityValue = JobPriorityManager.getInstance().getLowestPriority().getValue();
    
        for (Iterator iterator = jobList.iterator(); iterator.hasNext();) {
		
        	IJob iJob = (IJob) iterator.next();
		
			if(iJob.getPriority().getValue() > maxPriorityValue)
				maxPriorityValue = iJob.getPriority().getValue();	
		}

        JobManagementService.getInstance().printJobQueue();
        
        assertEquals( maxPriorityValue, JobManagementService.getInstance().getJobQueuePeek().getPriority().getValue() );
    }

    public void testJobFactoryReturnedJobsCount() {
		   
        List<IJob> jobList = jobFactory.generateRandomTypeJobs(countOfJobsToCreate, maxStartDelayMilliseconds);
        
        assertEquals( countOfJobsToCreate, jobList.size() );    
    }

    public void testJobFactoryReturnedJobsStatesToBeQueued() {
		   
        List<IJob> jobList = jobFactory.generateRandomTypeJobs(countOfJobsToCreate, maxStartDelayMilliseconds);

        for(IJob job : jobList) {
        	
        	assertEquals( job.getState().getName(), "QUEUED" );
        }       
    }
    
    public void testJobWaitingQueueStatesAfterExecutionsToBeSuccessOrFailed() {
		
    	JobManagementService.getInstance().reset();

    	maxStartDelayMilliseconds = 2 * 1000;
    	
        JobManagementService.getInstance().addJobList(jobFactory.generateRandomTypeJobs(countOfJobsToCreate, maxStartDelayMilliseconds));
        
        for(IJob job : JobManagementService.getInstance().getJobHistoryList()) {
        	
        	assertTrue( job.getState().getName().equals("QUEUED") );
        }
        
        JobManagementService.getInstance().start();
        
        JobManagementService.getInstance().printJobHistoryList();
        
        for(IJob job : JobManagementService.getInstance().getJobHistoryList()) {
        	
        	assertTrue( job.getState().getName().equals("SUCCESS") || job.getState().getName().equals("FAILED"));
        }
    }
}
