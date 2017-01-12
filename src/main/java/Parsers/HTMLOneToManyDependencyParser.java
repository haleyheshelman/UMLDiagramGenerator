package Parsers;

import ModelObjects.ModelObject;
import ModelObjects.OneToManyDependency;
import ModelObjects.OneToOneDependency;

public class HTMLOneToManyDependencyParser implements IParser {

	@Override
	public String parse(ModelObject o) {
		OneToManyDependency e =  (OneToManyDependency) o;

		StringBuilder s = new StringBuilder();
		s.append(e.getFirst() + "->" + e.getSecond());
		s.append(" [arrowhead=\"vee\",style=\"dashed\",label=\"0..n\"];");

		return s.toString();
	}

}
