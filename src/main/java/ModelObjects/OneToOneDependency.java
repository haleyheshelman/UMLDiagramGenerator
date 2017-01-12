package ModelObjects;

public class OneToOneDependency extends Dependency{

	public OneToOneDependency(String first, String second) {
		super(first, second);
	}

	@Override
	public String getName() {
		return "One to one dependency";
	}

}
