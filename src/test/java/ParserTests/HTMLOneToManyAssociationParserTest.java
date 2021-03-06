package ParserTests;

import static org.junit.Assert.*;

import java.rmi.NoSuchObjectException;

import org.junit.Before;
import org.junit.Test;

import ModelObjects.ModelObject;
import ModelObjects.OneToManyAssociation;
import ModelObjects.OneToManyDependency;
import Parsers.HTMLOneToManyAssociationParser;
import Parsers.HTMLOneToManyDependencyParser;
import Parsers.IParser;

public class HTMLOneToManyAssociationParserTest {

	IParser p;
	ModelObject o;
	@Before
	public void setUp() throws Exception {
		p = new HTMLOneToManyAssociationParser();
		o = new OneToManyAssociation("type", "name");
	}

	@Test
	public void testParse() throws NoSuchObjectException {
		
		String expected = "type->name [arrowhead=\"vee\",label=\"1..n\"];";
		String actual = p.parse(o);
		assertEquals(expected, actual);
	}

}
