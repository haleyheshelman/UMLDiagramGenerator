package ModelObjects;

public class BiDirectionalDependecy extends Dependency{

	public BiDirectionalDependecy(String first, String second) {
		super(first, second);
	}

	@Override
	public String getName() {
		return "BiDirectional dependency";
	}

}
