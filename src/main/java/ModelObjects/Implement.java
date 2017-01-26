package ModelObjects;

public class Implement extends IRelationship {
	
	public Implement(String name, String interfaceName) {
		super(name, interfaceName);
	}

	@Override
	public String getName() {
		return "implement";
	}

	@Override
	public String getSelector() {
		return "implement";
	}
	
	

}
