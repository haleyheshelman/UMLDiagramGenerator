package FactoryTests;

import static org.junit.Assert.*;

import java.rmi.NoSuchObjectException;

import org.junit.Before;
import org.junit.Test;

import Factories.HTMLParserFactory;
import Factories.IParserFactory;
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

public class HTMLParserFactoryTest {

	IParserFactory f;
	@Before
	public void setUp() throws Exception {
		f = new HTMLParserFactory();
	}

	@Test
	public void testMakeParser() throws NoSuchObjectException {
		
		ModelObject dummy = new UMLClass(null, null, null);
		assertTrue(f.makeParser(dummy.getSelector()) instanceof HTMLClassParser);
		dummy = new UMLAbstractClass(null, null, null);
		assertTrue(f.makeParser(dummy.getSelector()) instanceof HTMLAbstractClassParser);
		dummy = new UMLInterface(null, null);
		assertTrue(f.makeParser(dummy.getSelector()) instanceof HTMLInterfaceParser);
		dummy = new UMLMethod(null, null, null, false, false);
		assertTrue(f.makeParser(dummy.getSelector()) instanceof HTMLMethodParser);
		dummy = new UMLInstanceVariable(null, null, false, false);
		assertTrue(f.makeParser(dummy.getSelector()) instanceof HTMLInstanceVariableParser);
		dummy = new UMLParameter(null, null);
		assertTrue(f.makeParser(dummy.getSelector()) instanceof HTMLParameterParser);
		dummy = new Extend(null, null);
		assertTrue(f.makeParser(dummy.getSelector()) instanceof HTMLExtendParser);
		dummy = new Implement(null, null);
		assertTrue(f.makeParser(dummy.getSelector()) instanceof HTMLImplementParser);
		dummy = new OneToOneAssociation(null, null);
		assertTrue(f.makeParser(dummy.getSelector()) instanceof HTMLOneToOneAssociationParser);
		dummy = new OneToManyAssociation(null, null);
		assertTrue(f.makeParser(dummy.getSelector()) instanceof HTMLOneToManyAssociationParser);
		dummy = new OneToOneDependency(null, null);
		assertTrue(f.makeParser(dummy.getSelector()) instanceof HTMLOneToOneDependencyParser);
		dummy = new OneToManyDependency(null, null);
		assertTrue(f.makeParser(dummy.getSelector()) instanceof HTMLOneToManyDependencyParser);
		
	}

}
