package Factories;

import java.rmi.NoSuchObjectException;

import ModelObjects.ModelObject;
import Parsers.IParser;

public interface IParserFactory {

	public IParser makeParser(String s) throws NoSuchObjectException;
}
