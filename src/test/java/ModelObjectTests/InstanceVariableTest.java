package ModelObjectTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ModelObjects.UMLInstanceVariable;

public class InstanceVariableTest {
	
	UMLInstanceVariable instVar1;

	@Before
	public void setUp() throws Exception {
		instVar1 = new UMLInstanceVariable("type", "name", true);
	}

	@Test
	public void testGetType() {
		
		String expected = "type";
		String actual = instVar1.getType();
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testNameType() {
		
		String expected = "name";
		String actual = instVar1.getName();
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testGetIsPublic() {
		
		assertTrue(instVar1.getIsPublic());
		
	}

}
