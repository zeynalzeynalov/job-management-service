package job;

import java.util.TimerTask;

/**
 * 
 * Template Method Design Pattern applied to give ability of overriding actionStepsOfJob() at derived Job types.
 *
 */

public abstract class Job extends TimerTask implements IJob {
	
	private String name;
	private JobPriority priority;
	private JobStateContext state;
	private long startDelay;
	private long totalExecutionDuration;
	
	public Job() {
		
		priority = JobPriorityManager.getInstance().getDefaultPriority();
		state = new JobStateContext();
	}
	
	// Template method design pattern
	protected abstract void actionStepsOfJob() throws JobExecutionException;
	
	public void run() {
		
		long startTime = System.nanoTime();
		
		try
		{
			print();
			state.nextState();
			print();

			actionStepsOfJob();
			
			state.nextState();
		}
		catch (JobExecutionException e) {
			
			state.setState(new JobStateFailed());
		}

		totalExecutionDuration = ((System.nanoTime() - startTime) / 1000000);

		print();		
	}
	
	public int compareTo(IJob other) {
		
		if(this.priority.getValue() > other.getPriority().getValue())
			return -1;
		else
			if(this.priority.getValue() < other.getPriority().getValue())
				return 1;
			else
				return 0;
				
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public JobPriority getPriority() {
		
		return priority;
	}
	
	public void setPriority(JobPriority priorityNew) {
	
		this.priority = priorityNew;
	}
	
	public JobStateContext getState() {
		
		return state;
	}

	public long getStartDelay() {
		
		return startDelay;
	}

	public void setStartDelay(long startDelay) {
		
		this.startDelay = startDelay;
	}

	public long getTotalExecutionDuration() {
		
		return totalExecutionDuration;
	}
	
	public void print() {

		System.out.println(String.format("[%7s ] | %20s | %12s | %8s | StartDelay: %5d | TotalExecutionTime: %5d", 
				getName(), 
				this.getClass().getSimpleName(), 
				priority, 
				state.getName(),
				getStartDelay(),
				totalExecutionDuration
				));
	}
	
}
