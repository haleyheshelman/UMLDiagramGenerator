package ModelObjects;

public class OneToOneDependency extends Dependency{

	public OneToOneDependency(String className, String string) {
		super(className, string);
	}

	@Override
	public String getName() {
		return "One to one dependency";
	}

	@Override
	public String getSelector() {
		return "otod";
	}

}
