package job;

/**
 * 
 * State Design Pattern applied to have flexibility for extension of new Job states.
 *
 */

public interface IJobState {
	
	void changeState(JobStateContext jobStateContext);
}
