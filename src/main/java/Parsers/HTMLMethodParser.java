package Parsers;

import java.rmi.NoSuchObjectException;

import Factories.HTMLParserFactory;
import Factories.IParserFactory;
import ModelObjects.ModelObject;
import ModelObjects.UMLMethod;
import ModelObjects.UMLParameter;

public class HTMLMethodParser implements IParser{
			
	public HTMLMethodParser() {
		super();
	}

	@Override
	public String parse(ModelObject o) throws NoSuchObjectException {
		UMLMethod m = (UMLMethod) o;
		
		StringBuilder s = new StringBuilder();
		if (m.getIsPublic()) {
			s.append("+ ");
		} else {
			s.append("- ");
		}
		String toAppend = m.getSignature();
		if (m.getSignature().contains(">") || m.getSignature().contains("<")) {
			toAppend = m.getSignature().replace("<", "");
			toAppend = toAppend.replace(">", "");
		}
		
		s.append(toAppend + "(");
		for (UMLParameter param : m.getParameters()) {
			s.append(HTMLParserFactory.getInstance().makeParser(param.getSelector()).parse(param));
			s.append(", ");
		}
//		s.delete(s.length()-2, s.length()-1);
		s.append(") : " + m.getReturnType());
		s.append("<br/>");
		
		String output = s.toString();
//		output = output.replace("<", "");
//		output = output.replace(">", "");
		
		return output;
	}

}
