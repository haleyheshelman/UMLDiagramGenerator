package ModelObjects;

public abstract class Dependency extends IRelationship {

	public Dependency(String className, String string) {
		super(className, string);
	}

	public boolean isOpposite(Dependency d) {

		return ((this.pair[0] == d.pair[1]) && (d.pair[0] == this.pair[1]));

	}

	public boolean isEqual(ModelObject check) {
		if (!(check instanceof Dependency)) {
			return false;
		}
		String first = ((IRelationship) check).getFirst();
		String second = ((IRelationship) check).getSecond();
		
		return first.equals(this.pair[0]) && second.equals(this.pair[1]);
	}

}
