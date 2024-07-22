package CaseStudyCodingTask.JobManagementService;

import jobtypes.JobFactory;

/**
 * 
 * Zeynal Zeynalov
 *
 */

public class App
{
    public static void main( String[] args )
    {
    	System.out.println("[ JobManagementService started... ]\n");
    	
    	int maxStartDelayMilliseconds = 5 * 1000;
    	
    	int countOfJobsToCreate = 10;
    	
        JobFactory jobFactory = new JobFactory();

        JobManagementService.getInstance().addJobList(jobFactory.generateRandomTypeJobs(countOfJobsToCreate, maxStartDelayMilliseconds));
        
        JobManagementService.getInstance().printJobQueue();
        
        JobManagementService.getInstance().start();
        
        JobManagementService.getInstance().printJobHistoryList();
    }
}
