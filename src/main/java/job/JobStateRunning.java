package job;

public class JobStateRunning implements IJobState {

	public void changeState(JobStateContext jobStateContext) {

		jobStateContext.setState(new JobStateSuccess());
	}

}
