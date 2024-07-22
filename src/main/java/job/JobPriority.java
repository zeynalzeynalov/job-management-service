package job;

public class JobPriority {

	private final int value;
	private final String name;
	
	public JobPriority(int value, String name) {

		this.value = value;
		this.name = name;
	}
	
	public int getValue() {
		
		return value;
	}

	public String getName() {
		
		return name;
	}
	
	@Override
	public String toString( ) {
		
		return getName();
	}
	
}
