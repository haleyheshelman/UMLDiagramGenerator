package ParserTests;

import static org.junit.Assert.*;

import java.rmi.NoSuchObjectException;

import org.junit.Before;
import org.junit.Test;

import ModelObjects.ModelObject;
import ModelObjects.OneToManyDependency;
import ModelObjects.OneToOneAssociation;
import Parsers.HTMLOneToManyDependencyParser;
import Parsers.HTMLOneToOneAssociationParser;
import Parsers.IParser;

public class HTMLOneToManyDependencyParserTest {

	IParser p;
	ModelObject o;
	@Before
	public void setUp() throws Exception {
		p = new HTMLOneToManyDependencyParser();
		o = new OneToManyDependency("type", "name");
	}

	@Test
	public void testParse() throws NoSuchObjectException {
		
		String expected = "type->name [arrowhead=\"vee\",style=\"dashed\",label=\"1..n\"];";
		String actual = p.parse(o);
		assertEquals(expected, actual);
	}


}
