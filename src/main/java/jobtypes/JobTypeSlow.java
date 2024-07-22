package jobtypes;

import job.Job;
import job.JobExecutionException;

public class JobTypeSlow extends Job {

	public JobTypeSlow() {
		
	}
	
	protected void actionStepsOfJob() throws JobExecutionException {

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
	}
}
