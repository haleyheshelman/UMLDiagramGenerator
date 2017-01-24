package Parsers;

import ModelObjects.ModelObject;
import ModelObjects.UMLInstanceVariable;

public class HTMLInstanceVariableParser implements IParser {
	
	public HTMLInstanceVariableParser() {
		super();

	}

	@Override
	public String parse(ModelObject o) {
		UMLInstanceVariable var = (UMLInstanceVariable) o;
		
		StringBuilder s = new StringBuilder();
		
		if (var.getIsPublic()) {
			s.append("+ ");
		} else {
			s.append("- ");
		}
		
		s.append(var.getName() + " : ");
		s.append(var.getType() +"<br/>");
	
		return s.toString();
		
	}

}
