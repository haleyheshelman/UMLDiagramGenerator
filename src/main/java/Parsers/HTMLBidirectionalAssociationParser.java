package Parsers;

import ModelObjects.BiDirectionalAssociation;
import ModelObjects.ModelObject;

public class HTMLBidirectionalAssociationParser extends AbstractHTMLParser {

	/**
	 * May not need this class
	 */
	
	@Override
	public String parse(ModelObject o) {
		BiDirectionalAssociation e =  (BiDirectionalAssociation) o;

		StringBuilder s = new StringBuilder();
		s.append(e.getFirst() + "->" + e.getSecond());
		s.append(" [arrowhead=\"vee\",label=\"0..n\"];");

		return s.toString();
	}

}
