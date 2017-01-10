package ModelObjects;

public class BiDirectionalAssociation extends Association{

	public BiDirectionalAssociation(String first, String second) {
		super(first, second);
	
	}

	@Override
	public String getName() {
		return "Bidirectional association";
	}

}
