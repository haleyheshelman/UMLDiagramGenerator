package ModelObjects;

public abstract class Association extends IRelationship {

	public Association(String first, String second) {
		super(first, second);
	}

	public boolean isOpposite(Association d) {

		return ((this.pair[0] == d.pair[1]) && (d.pair[0] == this.pair[1]));

	}
}
