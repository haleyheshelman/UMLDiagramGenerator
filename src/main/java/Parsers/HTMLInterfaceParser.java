package Parsers;

import java.rmi.NoSuchObjectException;

import Factories.HTMLParserFactory;
import Factories.IParserFactory;
import ModelObjects.ModelObject;
import ModelObjects.UMLInterface;
import ModelObjects.UMLMethod;

public class HTMLInterfaceParser implements IParser {
	
	public HTMLInterfaceParser() {
		super();
	}
	
	@Override
	public String parse(ModelObject o) throws NoSuchObjectException {
		UMLInterface i = (UMLInterface) o;
		
		StringBuilder s = new StringBuilder();
		s.append(i.getName() + "[shape = \"record\",");
		s.append("label=<{");
		s.append("<i>" + i.getName() + "</i>" + "|");
		
		for (UMLMethod m : i.getMethods()) {
			String add = HTMLParserFactory.getInstance().makeParser(m.getSelector()).parse(m);
			s.append(add);
		}
		
		s.append("}>];");
		String output = s.toString();
		return output;
		
	}

}
