package Factories;

import Parsers.IParser;

public interface IParserFactory {
	
	public IParser makeParser(String s);
	
	public IParser makeClassParser();
	public IParser makeMethodParser();
	public IParser makeInstanceVariableParser();
	public IParser makeParameterParser();
	public IParser makeAbstractClassParser();
	public IParser makeExtendParser();
	public IParser makeImplementParser();
	public IParser makeInterfaceParser();
}
