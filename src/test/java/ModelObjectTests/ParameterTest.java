package ModelObjectTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ModelObjects.UMLParameter;

public class ParameterTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testParameterToString() {
		
		UMLParameter p = new UMLParameter("type", "name");
		String expected = "type name";
		String actual = p.toString();
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testParameterGetName() {
		
		UMLParameter p = new UMLParameter("type", "name");
		String expected = "name";
		String actual = p.getName();
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testParameterGetType() {
		
		UMLParameter p = new UMLParameter("type", "name");
		String expected = "type";
		String actual = p.getType();
		
		assertEquals(expected, actual);
		
	}
	

}
