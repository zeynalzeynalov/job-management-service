package jobtypes;

import job.Job;
import job.JobExecutionException;

public class JobTypeFastFail extends Job {

	public JobTypeFastFail() {
		
	}
	
	protected void actionStepsOfJob() throws JobExecutionException {
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		// Fail intentionally
		throw new JobExecutionException();
	}
}
