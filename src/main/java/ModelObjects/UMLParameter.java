package ModelObjects;

public class UMLParameter implements ModelObject {

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

	@Override
	public String getName() {
		return this.name;
	}

	public String getType() {
		return this.type;
	}

	@Override
	public String getSelector() {
		return "param";
	}

}
