package ModelObjects;

public class Extend extends IRelationship {

	public Extend(String thisClass, String thatClass) {
		super(thisClass, thatClass);
	}

	@Override
	public String getName() {
		return "extend";
	}
	
//	@Override
//	public String toGraphViz() {
//		StringBuilder s = new StringBuilder();
//		s.append(this.pair[0] + "->" + this.pair[1]);
//		s.append(" [arrowhead=\"onormal\"];");
//		
//		return s.toString();
//	}


}
