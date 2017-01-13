package ParserTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ModelObjects.Extend;
import ModelObjects.Implement;
import ModelObjects.ModelObject;
import Parsers.HTMLExtendParser;
import Parsers.HTMLImplementParser;
import Parsers.IParser;

public class HTMLExtendParserTest {

	IParser p;
	ModelObject o;
	@Before
	public void setUp() throws Exception {
		p = new HTMLExtendParser();
		o = new Extend("this", "that");
	}

	@Test
	public void testParse() {
		
		String expected = "this->that [arrowhead=\"onormal\"];";
		String actual = p.parse(o);
		assertEquals(expected, actual);

	}
}
