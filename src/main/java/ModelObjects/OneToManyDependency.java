package ModelObjects;

public class OneToManyDependency extends Dependency{

	public OneToManyDependency(String className, String string) {
		super(className, string);
	}

	@Override
	public String getName() {
		return "One to many dependency";
	}

	@Override
	public String getSelector() {
		return "otmd";
	}
	
	

}
