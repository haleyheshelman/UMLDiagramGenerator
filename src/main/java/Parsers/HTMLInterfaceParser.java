package Parsers;

import ModelObjects.ModelObject;
import ModelObjects.UMLInterface;
import ModelObjects.UMLMethod;

public class HTMLInterfaceParser implements IParser{

	private IParser methodParser;
	
	public HTMLInterfaceParser(IParser methodParser) {
		this.methodParser = methodParser;
	}
	
	@Override
	public String parse(ModelObject o) {
		UMLInterface i = (UMLInterface) o;
		
		StringBuilder s = new StringBuilder();
		s.append(i.getName() + "[shape = \"record\",");
		s.append("label=<{ ");
		s.append("<i>" + i.getName() + "</i>" + "|");
		
		for (UMLMethod m : i.getMethods()) {
			String add = methodParser.parse(m);
			s.append(add);
		}
		
		s.append("}>];");
		String output = s.toString();
//		output = output.replace("<", "");
//		output = output.replace(">", "");
		return output;
		
	}

}
