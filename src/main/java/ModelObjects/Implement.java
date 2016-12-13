package ModelObjects;

public class Implement implements IRelationship {
	
	String[] pair;

	public Implement(String thisClass, String thatClass) {
		this.pair = new String[2];
		this.pair[0] = thisClass;
		this.pair[1] = thatClass;
	}
	
	@Override
	public String getGraphViz() {
		return null;
	}

}
