package job;

public interface IJob extends Runnable, Comparable<IJob> {
	
	String getName();
	void setName(String name);
	
	JobPriority getPriority();
	void setPriority(JobPriority priorityNew);
	
	JobStateContext getState();
	
	long getStartDelay();
	void setStartDelay(long startDelay);
	
	long getTotalExecutionDuration();
	
	void print();
}
