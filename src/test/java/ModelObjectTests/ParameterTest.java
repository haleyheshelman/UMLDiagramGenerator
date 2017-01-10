package ModelObjectTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ModelObjects.UMLParameter;

public class ParameterTest {

	UMLParameter p;
	
	@Before
	public void setUp() throws Exception {
		p = new UMLParameter("type", "name");
	}

	@Test
	public void testParameterToString() {
		
		String expected = "type name";
		String actual = p.toString();
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testParameterGetName() {
		
		String expected = "name";
		String actual = p.getName();
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testParameterGetType() {
		
		String expected = "type";
		String actual = p.getType();
		
		assertEquals(expected, actual);
		
	}
	

}
