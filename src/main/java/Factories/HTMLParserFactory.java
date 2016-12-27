package Factories;

import Parsers.HTMLAbstractClassParser;
import Parsers.HTMLClassParser;
import Parsers.HTMLExtendParser;
import Parsers.HTMLImplementParser;
import Parsers.HTMLInstanceVariableParser;
import Parsers.HTMLInterfaceParser;
import Parsers.HTMLMethodParser;
import Parsers.HTMLParameterParser;
import Parsers.IParser;

public class HTMLParserFactory implements IParserFactory {

	@Override
	public IParser makeClassParser() {
		return new HTMLClassParser(this.makeMethodParser(), this.makeInstanceVariableParser());
	}

	@Override
	public IParser makeMethodParser() {
		return new HTMLMethodParser(this.makeParameterParser());
	}

	@Override
	public IParser makeInstanceVariableParser() {
		return new HTMLInstanceVariableParser();
	}

	@Override
	public IParser makeParameterParser() {
		return new HTMLParameterParser();
	}

	@Override
	public IParser makeAbstractClassParser() {
		return new HTMLAbstractClassParser(this.makeMethodParser(), this.makeInstanceVariableParser());
	}

	@Override
	public IParser makeExtendParser() {
		return new HTMLExtendParser();
	}

	@Override
	public IParser makeImplementParser() {
		return new HTMLImplementParser();
	}

	@Override
	public IParser makeInterfaceParser() {
		return new HTMLInterfaceParser(this.makeMethodParser());
	}

}
