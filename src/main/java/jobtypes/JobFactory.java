package jobtypes;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import job.IJob;
import job.JobPriorityManager;

/**
 * 
 * Factory Design Pattern applied to create different types of Jobs from same production point.
 *
 */

public class JobFactory {
	
	public IJob createJob(String jobType) {
		
		if(jobType == null)
			return null;
		
		Object object = null;
		
		Class<?> jobClass;
		try {
			jobClass = Class.forName("jobtypes.JobType"+ jobType);
			Constructor<?> constructor = jobClass.getConstructor();
			object = constructor.newInstance();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return ((IJob)object);
	}
	
	public IJob createRandomJob() {
		
		final String[] jobTypes = { "Fast", "FastFail", "Slow", "SlowFail" };
		
		return createJob(jobTypes[ new Random().nextInt(jobTypes.length) ]);
	}
	
	public List<IJob> generateRandomTypeJobs(int jobCount, int maxStartDelay) {

		List<IJob> jobList = new ArrayList<IJob>();

	    Random random = new Random();
	    
	    for (int i = 0; i < jobCount; i++) {
			
	     	IJob job = this.createRandomJob();
	    	
	     	job.setName(String.format("Job %s", i));
	    	job.setPriority(JobPriorityManager.getInstance().getRandomPriority());
	    	
	    	int randomStartDelay = random.nextInt(maxStartDelay);
	    	
	    	randomStartDelay = randomStartDelay < 1000 ? 0 : randomStartDelay;
	    	
	    	job.setStartDelay(randomStartDelay);
	    	jobList.add(job);
		}
	    
	    return jobList;
	}
 }
