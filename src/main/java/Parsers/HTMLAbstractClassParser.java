package Parsers;

import ModelObjects.ModelObject;
import ModelObjects.UMLAbstractClass;
import ModelObjects.UMLInstanceVariable;
import ModelObjects.UMLMethod;

public class HTMLAbstractClassParser implements IParser{

	private IParser instVarParser;
	private IParser methodParser;

	public HTMLAbstractClassParser(IParser methodParser, IParser instVarParser) {
		this.methodParser = methodParser;
		this.instVarParser = instVarParser;
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
				s.append(this.instVarParser.parse(var));
			}
			s.append("|");
		}
		
		for (UMLMethod m : c.getMethods()) {
			s.append(this.methodParser.parse(m));
		}
		
		s.append("}>];");
		String output = s.toString();
//		output = output.replace("<", "");
//		output = output.replace(">", "");
		return output;
	}
}
