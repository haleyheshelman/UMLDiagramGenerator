package ModelObjectTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ModelObjects.UMLParameter;

public class ParameterTest {

	UMLParameter p;
	UMLParameter p2;
	
	@Before
	public void setUp() throws Exception {
		p = new UMLParameter("type", "name");
		p2 = new UMLParameter(null, null);
	}

	@Test
	public void testParameterToString() {
		
		String expected = "type name";
		String actual = p.toString();
		assertEquals(expected, actual);
		
		expected = "null null";
		actual = p2.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testParameterGetName() {
		
		String expected = "name";
		String actual = p.getName();
		assertEquals(expected, actual);
		
		expected = null;
		actual = p2.getName();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testParameterGetType() {
		
		String expected = "type";
		String actual = p.getType();
		assertEquals(expected, actual);
		
		expected = null;
		actual = p2.getType();
		assertEquals(expected, actual);
		
	}
	

}
