package ModelObjects;

public class OneToOneAssociation extends Association {
	
	public OneToOneAssociation(String thisclass, String thatclass){
		super(thisclass, thatclass);
	}

	@Override
	public String getName() {
		return "One to one association";
	}

}
