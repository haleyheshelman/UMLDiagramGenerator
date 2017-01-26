package Factories;

import java.rmi.NoSuchObjectException;
import java.util.Map;

import ModelObjects.ModelObject;
import Parsers.IParser;

public abstract class AbstractParserFactory implements IParserFactory {
	
	protected Map<String, IParser> map;

	@Override
	public abstract IParser makeParser(String s) throws NoSuchObjectException;
	
	public void addParser(String s, IParser parser) {
		this.map.put(s, parser);
	}

}
