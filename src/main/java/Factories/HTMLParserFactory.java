package Factories;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.HashMap;

import ModelObjects.Extend;
import ModelObjects.Implement;
import ModelObjects.ModelObject;
import ModelObjects.OneToManyAssociation;
import ModelObjects.OneToManyDependency;
import ModelObjects.OneToOneAssociation;
import ModelObjects.OneToOneDependency;
import ModelObjects.Singleton;
import ModelObjects.UMLAbstractClass;
import ModelObjects.UMLClass;
import ModelObjects.UMLInstanceVariable;
import ModelObjects.UMLInterface;
import ModelObjects.UMLMethod;
import ModelObjects.UMLParameter;
import ModelObjects.ViolateCompositionOverInheritance;
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
import Parsers.HTMLSingletonParser;
import Parsers.HTMLViolateCompositionOverInheritanceClassParser;
import Parsers.HTMLViolateCompositionOverInheritanceRelationshipParser;
import Parsers.IParser;

public class HTMLParserFactory extends AbstractParserFactory {

	private static HTMLParserFactory instance;
	
	public HTMLParserFactory() {

		this.map = new HashMap<String, IParser>();
		this.map.put("class", new HTMLClassParser());
		this.map.put("abstractclass", new HTMLAbstractClassParser());
		this.map.put("interface", new HTMLInterfaceParser());
		this.map.put("method", new HTMLMethodParser());
		this.map.put("instvar", new HTMLInstanceVariableParser());
		this.map.put("param", new HTMLParameterParser());
		this.map.put("extend", new HTMLExtendParser());
		this.map.put("implement", new HTMLImplementParser());
		this.map.put("otoa", new HTMLOneToOneAssociationParser());
		this.map.put("otma", new HTMLOneToManyAssociationParser());
		this.map.put("otod", new HTMLOneToOneDependencyParser());
		this.map.put("otmd", new HTMLOneToManyDependencyParser());
		this.map.put("singleclass", new HTMLSingletonParser(new HTMLClassParser()));
		this.map.put("compoverinherclass", new HTMLViolateCompositionOverInheritanceClassParser(new HTMLClassParser()));
		this.map.put("compoverinherabstractclass", new HTMLViolateCompositionOverInheritanceClassParser(new HTMLAbstractClassParser()));
		this.map.put("compoverinherinterface", new HTMLViolateCompositionOverInheritanceClassParser(new HTMLInterfaceParser()));
		this.map.put("compoverinherextend", new HTMLViolateCompositionOverInheritanceRelationshipParser(new HTMLExtendParser()));
	}
	
	@Override
	public IParser makeParser(String c) throws NoSuchObjectException {
		
		IParser p = null;
		p = this.map.get(c);
		
		if (p == null) {
			throw new NoSuchObjectException("No parser found for this object: " + c);
		}
		
		return this.map.get(c);
	}

	public static HTMLParserFactory getInstance() {
		if (instance == null) {
			instance = new HTMLParserFactory();
		}
		return instance;
	}
}
