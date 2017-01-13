package ParserTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ModelObjects.ModelObject;
import ModelObjects.UMLParameter;
import Parsers.HTMLParameterParser;
import Parsers.IParser;

public class HTMLParameterParserTest {

	IParser p;
	ModelObject o;
	@Before
	public void setUp() throws Exception {
		p = new HTMLParameterParser();
		o = new UMLParameter("type", "name");
	}

	@Test
	public void testParse() {
		
		String expected = "type name";
		String actual = p.parse(o);
		assertEquals(expected, actual);
	}

}
