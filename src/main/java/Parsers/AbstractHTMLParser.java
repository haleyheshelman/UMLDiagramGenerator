package Parsers;

import Factories.HTMLParserFactory;
import Factories.IParserFactory;
import ModelObjects.ModelObject;

public abstract class AbstractHTMLParser implements IParser {

	protected IParserFactory factory;
	
	public AbstractHTMLParser() {
		this.factory = new HTMLParserFactory();
	}
	
	@Override
	public abstract String parse(ModelObject o);

}
