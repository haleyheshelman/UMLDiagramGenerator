package Parsers;

import ModelObjects.Extend;
import ModelObjects.ModelObject;

public class HTMLExtendParser implements IParser {

	@Override
	public String parse(ModelObject o) {
		Extend e = (Extend) o;
		
		StringBuilder s = new StringBuilder();
		s.append(e.getFirst() + "->" + e.getSecond());
		s.append(" [arrowhead=\"onormal\"];");
		
		return s.toString();
	}

}
