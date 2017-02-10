package ParserTests;

import static org.junit.Assert.*;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ModelObjects.ModelObject;
import ModelObjects.Singleton;
import ModelObjects.UMLClass;
import ModelObjects.UMLInstanceVariable;
import ModelObjects.UMLMethod;
import ModelObjects.UMLParameter;
import Parsers.HTMLClassParser;
import Parsers.HTMLSingletonParser;
import Parsers.IParser;

public class HTMLSingletonParserTest {

	IParser p;
	ModelObject o;
	@Before
	public void setUp() throws Exception {
		p = new HTMLSingletonParser(new HTMLClassParser());
		ArrayList<UMLParameter> params = new ArrayList<UMLParameter>();
		params.add(new UMLParameter("type", "name"));
		UMLMethod method = new UMLMethod("<init>", "returnType", params, null, true, false);
		ArrayList<UMLMethod> methods = new ArrayList<UMLMethod>();
		methods.add(method);
		ArrayList<UMLInstanceVariable> instVars = new ArrayList<UMLInstanceVariable>();
		instVars.add(new UMLInstanceVariable("type", "name", true, false));
		o = new Singleton(new UMLClass("name", methods, instVars));
		
	}

	@Test
	public void testParse() throws NoSuchObjectException {
		
		String expected = "name[color=blue,shape = \"record\",label=<{singleton<br /> name|+ name : type<br/>|+ init(type name, ) : returnType<br/>}>];";
		String actual = p.parse(o);
		assertEquals(expected, actual);
		
	}

}
