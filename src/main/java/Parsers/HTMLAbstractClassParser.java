package Parsers;

import java.rmi.NoSuchObjectException;

import Factories.HTMLParserFactory;
import Factories.IParserFactory;
import ModelObjects.ModelObject;
import ModelObjects.UMLAbstractClass;
import ModelObjects.UMLInstanceVariable;
import ModelObjects.UMLMethod;

public class HTMLAbstractClassParser implements IParser {
	
	public HTMLAbstractClassParser() {
		super();
	}
	
	@Override
	public String parse(ModelObject o) throws NoSuchObjectException {
		UMLAbstractClass c = (UMLAbstractClass) o;
		
		StringBuilder s = new StringBuilder();
		s.append(c.getName() + "[shape = \"record\",");
		s.append("label=<{ ");
		s.append("<i>" + c.getName()  + "</i>" + "|");
		
		if (!c.getInstanceVars().isEmpty()) {
			for (UMLInstanceVariable var : c.getInstanceVars()) {
				s.append(HTMLParserFactory.getInstance().makeParser(var.getSelector()).parse(var));
			}
			s.append("|");
		}
		
		for (UMLMethod m : c.getMethods()) {
			s.append(HTMLParserFactory.getInstance().makeParser(m.getSelector()).parse(m));
		}
		
		s.append("}>];");
		String output = s.toString();
//		output = output.replace("<", "");
//		output = output.replace(">", "");
		return output;
	}
}
