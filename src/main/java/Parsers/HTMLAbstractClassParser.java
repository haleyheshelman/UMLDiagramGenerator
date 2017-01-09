package Parsers;

import Factories.HTMLParserFactory;
import ModelObjects.ModelObject;
import ModelObjects.UMLAbstractClass;
import ModelObjects.UMLInstanceVariable;
import ModelObjects.UMLMethod;

public class HTMLAbstractClassParser extends AbstractHTMLParser {

	public HTMLAbstractClassParser() {
		super();
	}
	
	@Override
	public String parse(ModelObject o) {
		UMLAbstractClass c = (UMLAbstractClass) o;
		
		StringBuilder s = new StringBuilder();
		s.append(c.getName() + "[shape = \"record\",");
		s.append("label=<{ ");
		s.append("<i>" + c.getName()  + "</i>" + "|");
		
		if (!c.getInstanceVars().isEmpty()) {
			for (UMLInstanceVariable var : c.getInstanceVars()) {
				s.append(this.factory.makeParser(var.getClass()).parse(var));
			}
			s.append("|");
		}
		
		for (UMLMethod m : c.getMethods()) {
			s.append(this.factory.makeParser(m.getClass()).parse(m));
		}
		
		s.append("}>];");
		String output = s.toString();
//		output = output.replace("<", "");
//		output = output.replace(">", "");
		return output;
	}
}
