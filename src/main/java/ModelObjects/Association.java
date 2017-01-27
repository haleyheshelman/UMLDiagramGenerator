package ModelObjects;

public abstract class Association extends IRelationship {

	public Association(String className, String string) {
		super(className, string);
	}

	public boolean isOpposite(Association d) {

		return ((this.pair[0] == d.pair[1]) && (d.pair[0] == this.pair[1]));
	}
	
	public boolean isEqual(Object o) {
		if (!(o instanceof Association)) {
			return false;
		}
		IRelationship a = (IRelationship) o;
		
		return this.pair[0].equals(a.getFirst()) && this.pair[1].equals(a.getSecond());
	}
}
