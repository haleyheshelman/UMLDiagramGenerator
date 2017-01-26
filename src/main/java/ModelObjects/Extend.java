package ModelObjects;

public class Extend extends IRelationship {

	public Extend(String name, String superName) {
		super(name, superName);
	}

	@Override
	public String getName() {
		return "extend";
	}

	@Override
	public String getSelector() {
		return "extend";
	}

}
