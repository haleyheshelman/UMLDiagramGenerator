package Parsers;

import java.rmi.NoSuchObjectException;

import Factories.HTMLParserFactory;
import Factories.IParserFactory;
import ModelObjects.ModelObject;

public abstract class AbstractHTMLParser implements IParser {
	
	@Override
	public abstract String parse(ModelObject o) throws NoSuchObjectException;

}
