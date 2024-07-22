package job;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JobPriorityManager {

	private static JobPriorityManager instanceOfSingleton;
	
	private static List<JobPriority> priorityList;
	private final static JobPriority jobPriorityDefault = new JobPriority(1, "DEFAULT");
	
	private JobPriorityManager() {
		
		priorityList = new ArrayList<JobPriority>();
	}
	
	public static synchronized JobPriorityManager getInstance(){
		
        if(instanceOfSingleton == null){
        	
        	instanceOfSingleton = new JobPriorityManager();
        }
        
        return instanceOfSingleton;
    }
	
	public JobPriority getRandomPriority() {
		
        return priorityList.size() > 0 ? priorityList.get(new Random().nextInt(priorityList.size())) : jobPriorityDefault;
    }
	
	public void loadJobPriorityList(String[] priorityStringList)
	{
		for (int i = 0; i < priorityStringList.length; i++) {
			priorityList.add(new JobPriority(i + 1 > 10 ? 10 : i + 1, priorityStringList[i]));
		}
	}

	public JobPriority getDefaultPriority() {

		return priorityList.size() > 0 ? priorityList.get(0) : jobPriorityDefault;
	}

	public JobPriority getLowestPriority() {

		return priorityList.get(0);
	}
	
	public JobPriority getHighestPriority() {

		return priorityList.get(priorityList.size() - 1);
	}
}
