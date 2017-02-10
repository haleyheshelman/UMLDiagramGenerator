package ModelObjectTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ModelObjects.UMLInterface;
import ModelObjects.UMLMethod;
import ModelObjects.UMLParameter;

public class InterfaceTest {
	
	UMLInterface i1;
	UMLInterface i2;

	@Before
	public void setUp() throws Exception {
		
		ArrayList<UMLMethod> m = new ArrayList<UMLMethod>();
		m.add(new UMLMethod("sig", "returnType", new ArrayList<UMLParameter>(), null, true, false));
		
		i1 = new UMLInterface("name", m);
		i2 = new UMLInterface(null, new ArrayList<UMLMethod>());
		
	}

	@Test
	public void testGetName() {
		
		String expected = "name";
		String actual = i1.getName();
		assertEquals(expected, actual);
		
		expected = null;
		actual = i2.getName();
		assertEquals(expected, actual);	
	}
	
	@Test
	public void testGetMethods() {
		
		assertTrue(i2.getMethods().isEmpty());
		
		List<UMLMethod> methods = i1.getMethods();
		
		assertEquals(methods.size(), 1);
		assertEquals(methods.get(0).getName(), "sig");
		assertEquals(methods.get(0).getReturnType(), "returnType");
		assertTrue(methods.get(0).getIsPublic());
	}

}
