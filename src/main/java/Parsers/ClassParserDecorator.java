package Parsers;

import java.rmi.NoSuchObjectException;

import ModelObjects.ModelObject;
import ModelObjects.PatternDecorator;

public abstract class ClassParserDecorator implements IParser {

	IParser underlying;
	
	@Override
	public String parse(ModelObject o) throws NoSuchObjectException {
		StringBuilder s = new StringBuilder();
		
		ModelObject toParse = ((PatternDecorator) o).getUnderlying();
		
		String toAdd = this.underlying.parse(toParse);
		int firstBracket = toAdd.indexOf('[');
		String firstPart = toAdd.substring(0, firstBracket + 1);
		String secondPart = toAdd.substring(firstBracket + 1);
		s.append(firstPart);
		s.append(this.addConfig());
		
		int firstCurly = secondPart.indexOf('{');
		String chunkTwo = secondPart.substring(0, firstCurly+1);
		
		s.append(chunkTwo);
		s.append(this.addHeader());
		s.append(secondPart.substring(firstCurly + 1));
		
		return s.toString();
	}
	
	public abstract String addConfig();
	public abstract String addHeader();
}
