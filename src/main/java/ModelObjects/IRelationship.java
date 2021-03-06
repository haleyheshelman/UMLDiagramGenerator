package ModelObjects;

public abstract class IRelationship implements ModelObject{

	protected String[] pair;
	
	public IRelationship(String name, String superName){
		this.pair = new String[2];
		if(name != null) {
			this.pair[0] = name;
			this.pair[0] = this.pair[0].substring(this.pair[0].lastIndexOf('/') + 1);
		}
		if(superName != null) {
			
			this.pair[1] = superName;
			this.pair[1] = this.pair[1].substring(this.pair[1].lastIndexOf('/') + 1);
			this.pair[1] = this.pair[1].substring(this.pair[1].lastIndexOf('.') + 1);
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
//		String type = this.getClass().toString();
//		s.append(type.substring(type.indexOf('.') + 1)); 
		s.append("->");
		s.append(pair[1]);
		return s.toString();
	}
	

}
