package ModelObjectTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ModelObjects.UMLMethod;
import ModelObjects.UMLParameter;

public class MethodTest {
	
	UMLMethod m1;
	UMLMethod m2;

	@Before
	public void setUp() throws Exception {
		
		ArrayList<UMLParameter> p = new ArrayList<UMLParameter>();
		p.add(new UMLParameter("type", "name"));
		
		m1 = new UMLMethod("sig", "returnType", new ArrayList<UMLParameter>(), true, false);
		m2 = new UMLMethod(null, null, p, false, false);
		
	}

	@Test
	public void testGetSignature() {
		
		String expected = "sig";
		String actual = m1.getSignature();
		assertEquals(expected, actual);
		
		expected = null;
		actual = m2.getSignature();
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testGetReturnType() {
		
		String expected = "returnType";
		String actual = m1.getReturnType();
		assertEquals(expected, actual);
		
		expected = null;
		actual = m2.getReturnType();
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testGetName() {
		
		String expected = "sig";
		String actual = m1.getName();
		assertEquals(expected, actual);
		
		expected = null;
		actual = m2.getName();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetIsPublic() {
		
		assertTrue(m1.getIsPublic());
		assertFalse(m2.getIsPublic());
	}
	
	@Test
	public void testToString() {
		
		String expected = "returnType sig()";
		String actual = m1.toString();
		assertEquals(expected, actual);
		
		expected = "null null(type name)";
		actual = m2.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetParameters() {
		
		List<UMLParameter> params1 = m1.getParameters();
		List<UMLParameter> params2 = m2.getParameters();

		assertTrue(params1.isEmpty());
		
		assertEquals(params2.size(), 1);
		assertEquals(params2.get(0).getType(), "type");
		assertEquals(params2.get(0).getName(), "name");
	}
}
