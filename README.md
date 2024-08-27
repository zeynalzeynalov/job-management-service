# Job management service

### Applied software design patterns:

**Thread Safe Singleton:**

In order to have only one unique instance of JobManagementService object, constructor is hidden and added public method getInstance(). Also `ConfigurationManager` and `JobPriorityManager` obey singleton pattern.


**State:**

Applied to Job states for having a state machine implementation to design flow between different states. Flexibility is, any new job state can be added to solution by implementing IJobState interface. And also transition between these states can be handled with ```IJobState.changeState()``` method.

**Template Method:**

As we can have any type of Job, each Job type will have its own actions to be executed. To give an interface to the Job types we have following method inside abstract class Job: 
```
protected abstract void actionStepsOfJob() throws JobExecutionException;
```

This method can be overridden in new Job type class to add related necessary actions in it. Reason for this implementation is we want to change state of Job after execution of ```Job.actionStepsOfJob()``` method. 
So run() method inside Job class firstly execute ```actionStepsOfJob()``` then perform Job state change with ```state.nextState()```. To hide ```actionStepsOfJob()``` inside Job, protected modifier applied.

**Factory:**

To create different types of Jobs, `JobFactory` class is implemented. 
Factory create Job instance from name of related Job type class. Also can create a random Job type instance.
