package Factories;

import java.rmi.NoSuchObjectException;

import ModelObjects.ModelObject;
import Parsers.IParser;

public interface IParserFactory {

	public IParser makeParser(Class<? extends ModelObject> class1) throws NoSuchObjectException;
}
