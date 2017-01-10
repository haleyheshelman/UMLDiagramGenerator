package ModelObjects;

public class Implement extends IRelationship {
	
	public Implement(String thisClass, String thatClass) {
		super(thisClass, thatClass);
	}

	@Override
	public String getName() {
		return "implement";
	}
	
//	@Override
//	public String toGraphViz() {
//		StringBuilder s = new StringBuilder();
//		
//		s.append(this.pair[0] + "->" + this.pair[1]);
//		s.append(" [arrowhead=\"vee\",style=\"dashed\"];");
//		
//		return s.toString();
//	}


}
