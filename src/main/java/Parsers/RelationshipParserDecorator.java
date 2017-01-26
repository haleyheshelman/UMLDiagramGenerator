package Parsers;

import java.rmi.NoSuchObjectException;

import ModelObjects.ModelObject;
import ModelObjects.PatternDecorator;

public abstract class RelationshipParserDecorator implements IParser {

IParser underlying;
	
	@Override
	public String parse(ModelObject o) throws NoSuchObjectException {
		StringBuilder s = new StringBuilder();
		
		ModelObject toParse = ((PatternDecorator) o).getUnderlying();
		String toAdd = this.underlying.parse(toParse);
		int firstBracket = toAdd.indexOf('[');
		String first = toAdd.substring(0, firstBracket + 1);
		String second = toAdd.substring(firstBracket + 1);
		
		s.append(first);
		s.append(this.addConfig());
		s.append(second);
		
		return s.toString();
	}
	
	public abstract String addConfig();
}
