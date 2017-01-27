package ParserTests;

import static org.junit.Assert.*;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ModelObjects.ModelObject;
import ModelObjects.UMLInterface;
import ModelObjects.UMLMethod;
import ModelObjects.UMLParameter;
import Parsers.HTMLInterfaceParser;
import Parsers.IParser;

public class HTMLInterfaceParserTest {

	IParser p;
	ModelObject o;
	@Before
	public void setUp() throws Exception {
		p = new HTMLInterfaceParser();
		ArrayList<UMLParameter> params = new ArrayList<UMLParameter>();
		params.add(new UMLParameter("type", "name"));
		UMLMethod method = new UMLMethod("<init>", "returnType", params, true, false);
		ArrayList<UMLMethod> methods = new ArrayList<UMLMethod>();
		methods.add(method);
		o = new UMLInterface("name", methods);
		
	}

	@Test
	public void testParse() throws NoSuchObjectException {
		
		String expected = "name[shape = \"record\",label=<{<i>name</i>|+ init(type name, ) : returnType<br/>}>];";
		String actual = p.parse(o);
		assertEquals(expected, actual);
		
	}


}
