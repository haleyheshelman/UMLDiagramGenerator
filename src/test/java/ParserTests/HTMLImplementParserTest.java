package ParserTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ModelObjects.Implement;
import ModelObjects.ModelObject;
import ModelObjects.UMLInstanceVariable;
import Parsers.HTMLImplementParser;
import Parsers.HTMLInstanceVariableParser;
import Parsers.IParser;

public class HTMLImplementParserTest {

	IParser p;
	ModelObject o;
	@Before
	public void setUp() throws Exception {
		p = new HTMLImplementParser();
		o = new Implement("this", "that");
	}

	@Test
	public void testParse() {
		
		String expected = "this->that [arrowhead=\"onormal\",style=\"dashed\"];";
		String actual = p.parse(o);
		assertEquals(expected, actual);

	}
}
