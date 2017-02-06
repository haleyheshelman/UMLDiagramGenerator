package DriverTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Drivers.Modeler;
import ModelObjects.ModelObject;
import ModelObjects.OneToOneDependency;
import ModelObjects.UMLAbstractClass;
import ModelObjects.UMLInterface;

public class ModelerTest {

	Modeler m;
	
	@Before
	public void setUp() throws Exception {
		m = new Modeler();
		m.setBlacklist(new String[0]);
		m.setRecursion(false);
	}

	@Test
	public void testCreateClassModelsInterface() throws Exception {
		List<String> classes = new ArrayList<String>();
		classes.add("Runners.Runner");
		m.setRecursion(true);
		m.createClassModels(classes);
		
		List<ModelObject> models = m.getModels();
		assertFalse(models.isEmpty());
		assertEquals("Runner", models.get(0).getName());
		assertEquals(models.get(0).getClass(), UMLInterface.class);
	}
	
	@Test
	public void testCreateClassModelsRecursion() throws Exception {
		m.setRecursion(true);
		List<String> classes = new ArrayList<String>();
		classes.add("Parsers.HTMLClassParser");
		m.createClassModels(classes);
		
		List<ModelObject> models = m.getModels();
		assertEquals(2, models.size());
	}
	
	@Test
	public void testAbstractObject() {
		List<String> classes = new ArrayList<String>();
		classes.add("Factories.AbstractParserFactory");
		m.createClassModels(classes);
		
		
		assertTrue(m.getModels().get(0) instanceof UMLAbstractClass);
	}
	
	@Test
	public void testCheckBlacklist() {
		List<String> classes = new ArrayList<String>();
		classes.add("Factories.HTMLParserFactory");
		String[] black = {"AbstractParserFactory"};
		m.setBlacklist(black);
		m.createClassModels(classes);
		
		assertEquals(2, m.getModels().size());
	}

}
