package Parsers;

import java.rmi.NoSuchObjectException;

import ModelObjects.ModelObject;

public interface IParser {

	public String parse(ModelObject o) throws NoSuchObjectException;

}
