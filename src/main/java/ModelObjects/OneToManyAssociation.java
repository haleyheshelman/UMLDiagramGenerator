package ModelObjects;

public class OneToManyAssociation extends Association {
	
	public OneToManyAssociation(String className, String string){
		super(className, string);
	}

	@Override
	public String getName() {
		return "One to many association";
	}

	@Override
	public String getSelector() {
		return "otma";
	}
}
