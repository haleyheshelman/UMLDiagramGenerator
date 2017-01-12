package ModelObjects;

public class OneToManyDependency extends Dependency{

	public OneToManyDependency(String first, String second) {
		super(first, second);
	}

	@Override
	public String getName() {
		return "One to many dependency";
	}

}
