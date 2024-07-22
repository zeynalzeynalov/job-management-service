package jobtypes;

import job.Job;
import job.JobExecutionException;

public class JobTypeFast extends Job {

	protected void actionStepsOfJob() throws JobExecutionException {
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}
	
}
