package FactoryTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Factories.HTMLParserFactory;
import Factories.IParserFactory;
import ModelObjects.Extend;
import ModelObjects.IRelationship;
import ModelObjects.Implement;
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
	public void testMakeParser() {
		
		assertTrue(f.makeParser(UMLClass.class) instanceof HTMLClassParser);
		assertTrue(f.makeParser(UMLAbstractClass.class) instanceof HTMLAbstractClassParser);
		assertTrue(f.makeParser(UMLInterface.class) instanceof HTMLInterfaceParser);
		assertTrue(f.makeParser(UMLMethod.class) instanceof HTMLMethodParser);
		assertTrue(f.makeParser(UMLInstanceVariable.class) instanceof HTMLInstanceVariableParser);
		assertTrue(f.makeParser(UMLParameter.class) instanceof HTMLParameterParser);
		assertTrue(f.makeParser(Extend.class) instanceof HTMLExtendParser);
		assertTrue(f.makeParser(Implement.class) instanceof HTMLImplementParser);
		assertTrue(f.makeParser(OneToOneAssociation.class) instanceof HTMLOneToOneAssociationParser);
		assertTrue(f.makeParser(OneToManyAssociation.class) instanceof HTMLOneToManyAssociationParser);
		assertTrue(f.makeParser(OneToOneDependency.class) instanceof HTMLOneToOneDependencyParser);
		assertTrue(f.makeParser(OneToManyDependency.class) instanceof HTMLOneToManyDependencyParser);
		assertEquals(f.makeParser(IRelationship.class), null);

	}

}
