package Factories;

import ModelObjects.Extend;
import ModelObjects.Implement;
import ModelObjects.ModelObject;
import ModelObjects.OneToManyAssociation;
import ModelObjects.OneToManyDependency;
import ModelObjects.OneToOneAssociation;
import ModelObjects.OneToOneDependency;
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
import Parsers.HTMLOneToManyAssociationParser;
import Parsers.HTMLOneToManyDependencyParser;
import Parsers.HTMLOneToOneAssociationParser;
import Parsers.HTMLOneToOneDependencyParser;
import Parsers.HTMLParameterParser;
import Parsers.IParser;



public class HTMLParserFactory implements IParserFactory {

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
		} else if (c.equals(OneToOneAssociation.class)) {
			p = new HTMLOneToOneAssociationParser();
		} else if (c.equals(OneToManyAssociation.class)){
			p = new HTMLOneToManyAssociationParser();
		} else if (c.equals(OneToOneDependency.class)) {
			p = new HTMLOneToOneDependencyParser();
		} else if (c.equals(OneToManyDependency.class)) {
			p = new HTMLOneToManyDependencyParser();
		} else {
			System.out.println("This is not a supported object to parse");
			System.exit(1);
		}
		
		return p;
	}

}
