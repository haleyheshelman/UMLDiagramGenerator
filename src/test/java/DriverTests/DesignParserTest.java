package DriverTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Drivers.DesignParser;
import Factories.HTMLParserFactory;
import ModelObjects.ModelObject;
import ModelObjects.UMLClass;
import ModelObjects.UMLInstanceVariable;
import ModelObjects.UMLMethod;

public class DesignParserTest {

	DesignParser p;
	
	@Before
	public void setUp() throws Exception {
		p = new DesignParser();
	}

	@Test
	public void testAddSetFactory() {
		
		p.addFactory("blah", new HTMLParserFactory());
		p.setFactory("blah");
		
	}
	
	@Test
	public void testParseObjects() {
		
		ArrayList<ModelObject> objects = new ArrayList<ModelObject>();
		objects.add(new UMLClass("name", new ArrayList<UMLMethod>(), new ArrayList<UMLInstanceVariable>()));
		
		String expected = "digraph uml{rankdir=BT; concentrate=true;node[shape = box];name[shape = \"record\",label=<{ name|}>];}";
		String actual = p.parseObjects(objects);
		assertEquals(expected, actual);
	}

}
