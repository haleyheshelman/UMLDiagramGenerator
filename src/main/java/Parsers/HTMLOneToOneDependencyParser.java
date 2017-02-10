package Parsers;

import ModelObjects.ModelObject;
import ModelObjects.OneToOneDependency;

public class HTMLOneToOneDependencyParser implements IParser {

	@Override
	public String parse(ModelObject o) {
		OneToOneDependency e =  (OneToOneDependency) o;

		StringBuilder s = new StringBuilder();
		String first = e.getFirst();
		String second = e.getSecond();
		if (first.contains(".")) {
			first = first.substring(first.lastIndexOf('.') + 1);
		}
		if (second.contains(".")) {
			second = second.substring(second.lastIndexOf('.') + 1);
		}
		s.append(first + "->" + second);
		s.append(" [arrowhead=\"vee\",style=\"dashed\"];");

		return s.toString();
	}

}
