package job;

public final class JobStateContext {
	
	private IJobState state;

	public JobStateContext() {

		this.state = new JobStateQueued();
	}

	public void setState(IJobState state) {
		
		this.state = state;
	}
	
	public IJobState getState() {
		
		return state;
	}
	
	public void nextState() {

		state.changeState(this);
	}
	
	public String getName() {
		
		return state.getClass().getName().substring(12).toUpperCase();
	}
}
