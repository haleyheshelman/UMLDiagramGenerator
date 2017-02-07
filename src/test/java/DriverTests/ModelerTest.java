package DriverTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Drivers.Modeler;
import ModelObjects.Dependency;
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
		String[] black = {"ModelObject"};
		m.setBlacklist(black);
		m.setRecursion(true);
		m.createClassModels(classes);
		
		List<ModelObject> models = m.getModels();
		assertTrue(m.getRecursion());
		assertFalse(models.isEmpty());
		assertEquals("Runner", models.get(0).getName());
		assertEquals(models.get(0).getClass(), UMLInterface.class);
		
		m.setRecursion(false);
		m.getModels().clear();
	}
	
	@Test
	public void testCreateClassModelsRecursion() throws Exception {
		m.setRecursion(true);
		List<String> classes = new ArrayList<String>();
		classes.add("Factories.HTMLParserFactory");
		m.createClassModels(classes);
		
		List<ModelObject> models = m.getModels();
		assertEquals(7, models.size());
		
		m.getModels().clear();
	}
	
	@Test
	public void testAbstractObject() {
		List<String> classes = new ArrayList<String>();
		classes.add("Factories.AbstractParserFactory");
		m.createClassModels(classes);
		assertTrue(m.getModels().get(1) instanceof UMLAbstractClass);
		
		classes.clear();
		classes.add("Parsers.RelationshipParserDecorator");
		m.createClassModels(classes);
		
		boolean containsReturnType = false;
		boolean containsParamType = false;
		for(ModelObject model : m.getModels()){
			if (model instanceof Dependency && 
					model.toString().contains("ModelObject")) {
				containsParamType = true;
			}
			else if ((model instanceof Dependency && 
					model.toString().contains("String"))) {
				containsReturnType = true;
			}
		}
		assertFalse(containsReturnType);
		assertTrue(containsParamType);
		m.getModels().clear();
	}
	
	@Test
	public void testCheckBlacklist() {
		List<String> classes = new ArrayList<String>();
		classes.add("Factories.HTMLParserFactory");
		String[] black = {"AbstractParserFactory"};
		m.setBlacklist(black);
		m.createClassModels(classes);
		
		assertEquals(3, m.getModels().size());
		
		m.getModels().clear();
	}
	
	@Test
	public void testMoreDependencies() {
		List<String> classes = new ArrayList<String>();
		classes.add("Drivers.Modeler");
		m.createClassModels(classes);
		
		assertEquals(8, m.getModels().size());
	}
	
	
	
	
	

}
