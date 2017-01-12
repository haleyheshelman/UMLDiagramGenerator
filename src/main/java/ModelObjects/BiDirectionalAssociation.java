package ModelObjects;

public class BiDirectionalAssociation extends Association{

	/**
	 * May not need this class.
	 * @param first
	 * @param second
	 */
	
	public BiDirectionalAssociation(String first, String second) {
		super(first, second);
	
	}

	@Override
	public String getName() {
		return "Bidirectional association";
	}

}
