package ParserTests;

import static org.junit.Assert.*;

import java.rmi.NoSuchObjectException;

import org.junit.Before;
import org.junit.Test;

import ModelObjects.ModelObject;
import ModelObjects.OneToOneDependency;
import ModelObjects.UMLParameter;
import Parsers.HTMLOneToOneDependencyParser;
import Parsers.HTMLParameterParser;
import Parsers.IParser;

public class HTMLOnetoOneDependencyParserTest {

	IParser p;
	ModelObject o;
	@Before
	public void setUp() throws Exception {
		p = new HTMLOneToOneDependencyParser();
		o = new OneToOneDependency("type", "name");
	}

	@Test
	public void testParse() throws NoSuchObjectException {
		
		String expected = "type->name [arrowhead=\"vee\",style=\"dashed\"];";
		String actual = p.parse(o);
		assertEquals(expected, actual);
	}


}
