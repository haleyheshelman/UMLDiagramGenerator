package ModelObjects;

public class Extend implements IRelationship {

	String[] pair;

	public Extend(String thisClass, String thatClass) {
		this.pair = new String[2];
		this.pair[0] = thisClass;
		this.pair[0] = this.pair[0].substring(this.pair[0].lastIndexOf('/') + 1);
		this.pair[1] = thatClass;
		this.pair[1] = this.pair[1].substring(this.pair[1].lastIndexOf('/') + 1);
	}
	
	@Override
	public String getGraphViz() {
		StringBuilder s = new StringBuilder();
		s.append(this.pair[0] + "->" + this.pair[1]);
		s.append(" [arrowhead=\"onormal\"];");
		
		return s.toString();
	}

}
