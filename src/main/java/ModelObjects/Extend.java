package ModelObjects;

public class Extend extends IRelationship {

	public Extend(String thisClass, String thatClass) {
		super(thisClass, thatClass);
	}

	@Override
	public String getName() {
		return "extend";
	}

}
