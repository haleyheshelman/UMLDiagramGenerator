package Factories;

import ModelObjects.Extend;
import ModelObjects.Implement;
import ModelObjects.ModelObject;
import ModelObjects.UMLAbstractClass;
import ModelObjects.UMLClass;
import ModelObjects.UMLInstanceVariable;
import ModelObjects.UMLInterface;
import ModelObjects.UMLMethod;
import ModelObjects.UMLParameter;
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

//	@Override
//	public IParser makeClassParser() {
//		return new HTMLClassParser(this.makeMethodParser(), this.makeInstanceVariableParser());
//	}
//
//	@Override
//	public IParser makeMethodParser() {
//		return new HTMLMethodParser(this.makeParameterParser());
//	}
//
//	@Override
//	public IParser makeInstanceVariableParser() {
//		return new HTMLInstanceVariableParser();
//	}
//
//	@Override
//	public IParser makeParameterParser() {
//		return new HTMLParameterParser();
//	}
//
//	@Override
//	public IParser makeAbstractClassParser() {
//		return new HTMLAbstractClassParser(this.makeMethodParser(), this.makeInstanceVariableParser());
//	}
//
//	@Override
//	public IParser makeExtendParser() {
//		return new HTMLExtendParser();
//	}
//
//	@Override
//	public IParser makeImplementParser() {
//		return new HTMLImplementParser();
//	}
//
//	@Override
//	public IParser makeInterfaceParser() {
//		return new HTMLInterfaceParser(this.makeMethodParser());
//	}

	@Override
	public IParser makeParser(Class<? extends ModelObject> c) {
		IParser p = null;
		
		if (c.equals(UMLClass.class)) {
			p = new HTMLClassParser();
		} else if (c.equals(UMLAbstractClass.class)) {
			p = new HTMLAbstractClassParser();
		} else if (c.equals(UMLInterface.class)) {
			p = new HTMLInterfaceParser();
		} else if (c.equals(UMLMethod.class)) {
			p = new HTMLMethodParser();
		} else if (c.equals(UMLInstanceVariable.class)) {
			p = new HTMLInstanceVariableParser();
		} else if (c.equals(UMLParameter.class)) {
			p = new HTMLParameterParser();
		} else if (c.equals(Extend.class)) {
			p = new HTMLExtendParser();
		} else if (c.equals(Implement.class)) {
			p = new HTMLImplementParser();
		} else {
			System.out.println("This is not a supported object to parse");
			System.exit(1);
		}
		
		return p;
	}

}
