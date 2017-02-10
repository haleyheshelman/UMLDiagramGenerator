package Parsers;

import ModelObjects.ModelObject;
import ModelObjects.OneToManyAssociation;
import ModelObjects.OneToOneAssociation;

public class HTMLOneToManyAssociationParser implements IParser {

	@Override
	public String parse(ModelObject o) {
		OneToManyAssociation e =  (OneToManyAssociation) o;

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
		s.append(" [arrowhead=\"vee\",label=\"1..n\"];");

		return s.toString();
	}

}
