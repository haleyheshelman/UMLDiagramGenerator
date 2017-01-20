package Factories;

// testing commit

import java.rmi.NoSuchObjectException;
import java.util.Map;

import ModelObjects.ModelObject;
import Parsers.IParser;

public abstract class AbstractParserFactory implements IParserFactory {
	
	protected Map<Class<? extends ModelObject>, IParser> map;

	@Override
	public abstract IParser makeParser(Class<? extends ModelObject> class1) throws NoSuchObjectException;
	
	public void addParser(Class<? extends ModelObject> clazz, IParser parser) {
		this.map.put(clazz, parser);
	}

}
