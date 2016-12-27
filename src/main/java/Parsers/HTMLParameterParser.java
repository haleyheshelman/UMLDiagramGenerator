package Parsers;

import ModelObjects.ModelObject;
import ModelObjects.UMLParameter;

public class HTMLParameterParser implements IParser {

	@Override
	public String parse(ModelObject o) {
		UMLParameter p = (UMLParameter) o;
		
		StringBuilder s = new StringBuilder();
		s.append(p.getType() + " " + p.getName());
		return s.toString();
		
	}

}
