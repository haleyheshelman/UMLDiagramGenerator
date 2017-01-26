package ModelObjects;

public class OneToOneAssociation extends Association {
	
	public OneToOneAssociation(String className, String string){
		super(className, string);
	}

	@Override
	public String getName() {
		return "One to one association";
	}

	@Override
	public String getSelector() {
		return "otoa";
	}

}
