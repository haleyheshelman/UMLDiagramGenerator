package ModelObjects;

public class Extend implements IRelationship {

	String[] pair;

	public Extend(String thisClass, String thatClass) {
		this.pair = new String[2];
		this.pair[0] = thisClass;
		this.pair[1] = thatClass;
	}
	
	@Override
	public String getGraphViz() {
		return null;
	}

}
