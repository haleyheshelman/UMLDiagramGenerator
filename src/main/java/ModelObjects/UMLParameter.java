package ModelObjects;

public class UMLParameter {

	private String type;
	private String name;
	public UMLParameter(String type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append(type + " " + name);
		return s.toString();
	}

	public String toGraphViz() {
		StringBuilder s = new StringBuilder();
		s.append(type + " " + name);
		return s.toString();
	}
}
