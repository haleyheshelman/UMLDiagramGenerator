package Parsers;

import ModelObjects.ModelObject;
import ModelObjects.UMLParameter;

public class HTMLParameterParser implements IParser {
	
	public HTMLParameterParser() {
		super();
	}

	@Override
	public String parse(ModelObject o) {
		UMLParameter p = (UMLParameter) o;
		
		StringBuilder s = new StringBuilder();
		s.append(p.getType().substring(p.getType().lastIndexOf('.') + 1) + " " + p.getName());
		return s.toString();
		
	}

}
