package ParserTests;

import static org.junit.Assert.*;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ModelObjects.ModelObject;
import ModelObjects.OneToManyAssociation;
import ModelObjects.UMLMethod;
import ModelObjects.UMLParameter;
import Parsers.HTMLMethodParser;
import Parsers.HTMLOneToManyAssociationParser;
import Parsers.IParser;

public class HTMLMethodParserTest {

	IParser p;
	ModelObject o;
	@Before
	public void setUp() throws Exception {
		p = new HTMLMethodParser();
		ArrayList<UMLParameter> params = new ArrayList<UMLParameter>();
		params.add(new UMLParameter("type", "name"));
		o = new UMLMethod("<init>", "returnType", params, null, null, true, false);
		
	}

	@Test
	public void testParse() throws NoSuchObjectException {
		
		String expected = "+ init(type name, ) : returnType<br/>";
		String actual = p.parse(o);
		assertEquals(expected, actual);
		
		ArrayList<UMLParameter> params = new ArrayList<UMLParameter>();
		params.add(new UMLParameter("type", "name"));
		o = new UMLMethod("<init>", "returnType", params, null, null, false, false);
		
		expected = "- init(type name, ) : returnType<br/>";
		actual = p.parse(o);
		assertEquals(expected, actual);
	}


}
