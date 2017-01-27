package ModelObjects;

public abstract class IRelationship implements ModelObject{

	protected String[] pair;
	
	public IRelationship(String name, String superName){
		this.pair = new String[2];
		if(name != null) {
			this.pair[0] = name;
			this.pair[0] = name.substring(name.lastIndexOf('/') + 1);
		}
		if(superName != null) {
			
			this.pair[1] = superName;
			this.pair[1] = superName.substring(superName.lastIndexOf('/') + 1);
		}
		
	}
	
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
