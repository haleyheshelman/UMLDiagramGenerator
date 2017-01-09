package Parsers;

import Factories.HTMLParserFactory;
import Factories.IParserFactory;
import ModelObjects.ModelObject;
import ModelObjects.UMLClass;
import ModelObjects.UMLInstanceVariable;
import ModelObjects.UMLMethod;

public class HTMLClassParser extends AbstractHTMLParser {
	
	public HTMLClassParser() {
		super();
	}

	@Override
	public String parse(ModelObject o) {
		StringBuilder s = new StringBuilder();
		s.append(o.getName() + "[shape = \"record\",");
		s.append("label=<{ ");
		s.append(o.getName() + "|");
		
		if (((UMLClass) o).getInstanceVars().isEmpty()) {
			for (UMLInstanceVariable var : ((UMLClass) o).getInstanceVars()) {
				s.append(this.factory.makeParser(var.getClass()).parse(var));
			}
			s.append("|");
		}
		
		for (UMLMethod m : ((UMLClass) o).getMethods()) {
			s.append(this.factory.makeParser(m.getClass()).parse(m));
		}
		
		s.append("}>];");
		String output = s.toString();
//		output = output.replace("<", "");
//		output = output.replace(">", "");
		return output;
	}
}
