package ModelObjectTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ModelObjects.UMLAbstractClass;
import ModelObjects.UMLInstanceVariable;
import ModelObjects.UMLMethod;
import ModelObjects.UMLParameter;

public class AbstractClassTest {

	UMLAbstractClass class1;
	@Before
	public void setUp() throws Exception {
		class1 = new UMLAbstractClass("name", new ArrayList<UMLMethod>(), new ArrayList<UMLInstanceVariable>());		
	}

	@Test
	public void testGetName() {
		
		String expected = "name";
		String actual = class1.getName();
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testGetMethods() {
		
		ArrayList<UMLMethod> methods = new ArrayList<UMLMethod>();
		methods.add(new UMLMethod("sig", "returnType", new ArrayList<UMLParameter>(), true, false));
		class1 = new UMLAbstractClass("name", methods, new ArrayList<UMLInstanceVariable>());
		
		assertEquals(class1.getMethods().size(), 1);
		assertEquals(class1.getMethods().get(0).getName(), "sig");
		assertEquals(class1.getMethods().get(0).getSignature(), "sig");
		assertEquals(class1.getMethods().get(0).getReturnType(), "returnType");
		assertTrue(class1.getMethods().get(0).getIsPublic());
		assertFalse(class1.getMethods().get(0).getIsStatic());
		assertEquals(class1.getMethods().get(0).getParameters().size(), 0);

	}
	
	@Test
	public void testGetInstanceVars() {
		
		ArrayList<UMLInstanceVariable> instVars = new ArrayList<UMLInstanceVariable>();
		instVars.add(new UMLInstanceVariable("type", "name", true, false));
		class1 = new UMLAbstractClass("name", new ArrayList<UMLMethod>(), instVars);
		
		assertEquals(class1.getInstanceVars().size(), 1);
		assertEquals(class1.getInstanceVars().get(0).getName(), "name");
		assertEquals(class1.getInstanceVars().get(0).getType(), "type");
		assertTrue(class1.getInstanceVars().get(0).getIsPublic());
		assertFalse(class1.getInstanceVars().get(0).getIsStatic());
		
	}
}
