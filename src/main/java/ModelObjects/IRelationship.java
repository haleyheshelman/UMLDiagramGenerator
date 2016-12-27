package ModelObjects;

public abstract class IRelationship implements ModelObject{

	protected String[] pair;
	
	public String getFirst() {
		return this.pair[0];
	}
	
	public String getSecond() {
		return this.pair[1];
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(pair[0]);
		s.append("->");
		s.append(pair[1]);
		return s.toString();
	}
}
