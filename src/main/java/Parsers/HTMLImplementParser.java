package Parsers;

import ModelObjects.Implement;
import ModelObjects.ModelObject;

public class HTMLImplementParser implements IParser {

	public HTMLImplementParser() {
		super();

	}
	
	@Override
	public String parse(ModelObject o) {
		Implement i = (Implement) o;
		
		StringBuilder s = new StringBuilder();
		
		s.append(i.getFirst() + "->" + i.getSecond());
		s.append(" [arrowhead=\"onormal\",style=\"dashed\"];");
		
		return s.toString();
		
	}

}
