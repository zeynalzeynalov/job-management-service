package jobtypes;

import job.Job;
import job.JobExecutionException;

public class JobTypeSlowFail extends Job {

	public JobTypeSlowFail() {
		
	}
	
	protected void actionStepsOfJob() throws JobExecutionException {

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
		// Fail intentionally
		throw new JobExecutionException();
	}
}
