package job;

public class JobStateQueued implements IJobState {

	public void changeState(JobStateContext jobStateContext) {

		jobStateContext.setState(new JobStateRunning());
	}

}
