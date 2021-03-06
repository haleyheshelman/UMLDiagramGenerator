package ModelObjects;

import java.util.Collections;
import java.util.List;

public class UMLInstanceVariable implements ModelObject{

	String type;
	String name;
	boolean isPublic;
	boolean isStatic;
	
	public UMLInstanceVariable(String type, String name, boolean isPublic, boolean isStatic) {
		this.type = type;
		this.name = name;
		this.isPublic = isPublic;
		this.isStatic = isStatic;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getParsedName() {
		String output = this.name.substring(this.name.lastIndexOf('.') + 1);
		output = output.substring(output.lastIndexOf('/') + 1);
		return output;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getParsedType() {
		String output = this.type.substring(this.type.lastIndexOf('.') + 1);
		output = output.substring(output.lastIndexOf('/') + 1);
		return output;
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append(isPublic + " " + type + " " + name);
		return s.toString();
	}

	public boolean getIsPublic() {
		return this.isPublic;
	}

	public boolean getIsStatic() {
		return this.isStatic;
	}

	@Override
	public String getSelector() {
		return "instvar";
	}

}
