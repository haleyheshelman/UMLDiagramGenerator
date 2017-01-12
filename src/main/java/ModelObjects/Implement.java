package ModelObjects;

public class Implement extends IRelationship {
	
	public Implement(String thisClass, String thatClass) {
		super(thisClass, thatClass);
	}

	@Override
	public String getName() {
		return "implement";
	}

}
