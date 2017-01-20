package ParserTests;

import static org.junit.Assert.*;

import java.rmi.NoSuchObjectException;

import org.junit.Before;
import org.junit.Test;

import ModelObjects.ModelObject;
import ModelObjects.OneToOneAssociation;
import ModelObjects.OneToOneDependency;
import Parsers.HTMLOneToOneAssociationParser;
import Parsers.HTMLOneToOneDependencyParser;
import Parsers.IParser;

public class HTMLOneToOneAssociationParserTest {

	IParser p;
	ModelObject o;
	@Before
	public void setUp() throws Exception {
		p = new HTMLOneToOneAssociationParser();
		o = new OneToOneAssociation("type", "name");
	}

	@Test
	public void testParse() throws NoSuchObjectException {
		
		String expected = "type->name [arrowhead=\"vee\"];";
		String actual = p.parse(o);
		assertEquals(expected, actual);
	}


}
