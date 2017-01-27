package ParserTests;

import static org.junit.Assert.*;

import java.rmi.NoSuchObjectException;
import org.junit.Before;
import org.junit.Test;

import ModelObjects.ModelObject;
import ModelObjects.UMLInstanceVariable;
import Parsers.HTMLInstanceVariableParser;
import Parsers.IParser;

public class HTMLInstanceVariableParserTest {
	
	IParser p;
	ModelObject o;
	@Before
	public void setUp() throws Exception {
		p = new HTMLInstanceVariableParser();
		o = new UMLInstanceVariable("type", "name", true, false);
	}

	@Test
	public void testParse() throws NoSuchObjectException {
		
		String expected = "+ name : type<br/>";
		String actual = p.parse(o);
		assertEquals(expected, actual);
		
		o = new UMLInstanceVariable("type", "name", false, false);
		expected = "- name : type<br/>";
		actual = p.parse(o);
		assertEquals(expected, actual);
	}

}
