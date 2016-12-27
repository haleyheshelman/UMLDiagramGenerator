package Parsers;

import ModelObjects.ModelObject;
import ModelObjects.UMLClass;
import ModelObjects.UMLInstanceVariable;
import ModelObjects.UMLMethod;

public class HTMLClassParser implements IParser {
	
	private IParser methodParser;
	private IParser instVarParser;
	
	public HTMLClassParser(IParser methodParser, IParser instVarParser) {
		this.methodParser = methodParser;
		this.instVarParser = instVarParser;
	}

	@Override
	public String parse(ModelObject o) {
		StringBuilder s = new StringBuilder();
		s.append(o.getName() + "[shape = \"record\",");
		s.append("label=<{ ");
		s.append(o.getName() + "|");
		
		if (((UMLClass) o).getInstanceVars().isEmpty()) {
			for (UMLInstanceVariable var : ((UMLClass) o).getInstanceVars()) {
				s.append(instVarParser.parse(var));
			}
			s.append("|");
		}
		
		for (UMLMethod m : ((UMLClass) o).getMethods()) {
			s.append(methodParser.parse(m));
		}
		
		s.append("}>];");
		String output = s.toString();
//		output = output.replace("<", "");
//		output = output.replace(">", "");
		return output;
	}
}
