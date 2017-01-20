package Factories;

import java.rmi.NoSuchObjectException;
import java.util.HashMap;

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

public class HTMLParserFactory extends AbstractParserFactory {

	private static IParserFactory instance;
	
	public HTMLParserFactory() {
		this.map = new HashMap<Class<? extends ModelObject>, IParser>();
		this.map.put(UMLClass.class, new HTMLClassParser());
		this.map.put(UMLAbstractClass.class, new HTMLAbstractClassParser());
		this.map.put(UMLInterface.class, new HTMLInterfaceParser());
		this.map.put(UMLMethod.class, new HTMLMethodParser());
		this.map.put(UMLInstanceVariable.class, new HTMLInstanceVariableParser());
		this.map.put(UMLParameter.class, new HTMLParameterParser());
		this.map.put(Extend.class, new HTMLExtendParser());
		this.map.put(Implement.class, new HTMLImplementParser());
		this.map.put(OneToOneAssociation.class, new HTMLOneToOneAssociationParser());
		this.map.put(OneToManyAssociation.class, new HTMLOneToManyAssociationParser());
		this.map.put(OneToOneDependency.class, new HTMLOneToOneDependencyParser());
		this.map.put(OneToManyDependency.class, new HTMLOneToManyDependencyParser());
	}
	
	@Override
	public IParser makeParser(Class<? extends ModelObject> c) throws NoSuchObjectException {
		
		IParser p = null;
		p = this.map.get(c);
		
		if (p == null) {
			throw new NoSuchObjectException("No parser found for this object");
		}
		
		return this.map.get(c);
	}

	public static IParserFactory getInstance() {
		if (instance == null) {
			instance = new HTMLParserFactory();
		}
		return instance;
	}
}
