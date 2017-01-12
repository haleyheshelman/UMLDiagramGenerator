package Parsers;

import ModelObjects.ModelObject;
import ModelObjects.OneToOneDependency;

public class HTMLOneToOneDependencyParser implements IParser {

	@Override
	public String parse(ModelObject o) {
		OneToOneDependency e =  (OneToOneDependency) o;

		StringBuilder s = new StringBuilder();
		s.append(e.getFirst() + "->" + e.getSecond());
		s.append(" [arrowhead=\"vee\",style=\"dashed\"];");

		return s.toString();
	}

}
