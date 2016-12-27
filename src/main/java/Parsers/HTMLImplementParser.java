package Parsers;

import ModelObjects.Implement;
import ModelObjects.ModelObject;

public class HTMLImplementParser implements IParser {

	@Override
	public String parse(ModelObject o) {
		Implement i = (Implement) o;
		
		StringBuilder s = new StringBuilder();
		
		s.append(i.getFirst() + "->" + i.getSecond());
		s.append(" [arrowhead=\"vee\",style=\"dashed\"];");
		
		return s.toString();
		
	}

}
