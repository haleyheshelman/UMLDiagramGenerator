package ModelObjects;

public class OneToManyAssociation extends Association {
	
	public OneToManyAssociation(String first, String second){
		super(first, second);
	}

	@Override
	public String getName() {
		return "One to many association";
	}

}
