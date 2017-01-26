package ModelObjects;

public abstract class Dependency extends IRelationship {

	public Dependency(String className, String string) {
		super(className, string);
	}

	public boolean isOpposite(Dependency d) {

		return ((this.pair[0] == d.pair[1]) && (d.pair[0] == this.pair[1]));

	}

}
