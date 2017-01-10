package Parsers;

import ModelObjects.Extend;
import ModelObjects.ModelObject;
import ModelObjects.OneToOneAssociation;

public class HTMLOneToOneAssociationParser extends AbstractHTMLParser {

	@Override
	public String parse(ModelObject o) {
		OneToOneAssociation e =  (OneToOneAssociation) o;

		StringBuilder s = new StringBuilder();
		s.append(e.getFirst() + "->" + e.getSecond());
		s.append(" [arrowhead=\"vee\"];");

		return s.toString();
	}

}
