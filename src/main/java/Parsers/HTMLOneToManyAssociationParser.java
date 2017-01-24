package Parsers;

import ModelObjects.ModelObject;
import ModelObjects.OneToManyAssociation;
import ModelObjects.OneToOneAssociation;

public class HTMLOneToManyAssociationParser implements IParser {

	@Override
	public String parse(ModelObject o) {
		OneToManyAssociation e =  (OneToManyAssociation) o;

		StringBuilder s = new StringBuilder();
		s.append(e.getFirst() + "->" + e.getSecond());
		s.append(" [arrowhead=\"vee\",label=\"0..n\"];");

		return s.toString();
	}

}
