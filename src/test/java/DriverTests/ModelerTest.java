package DriverTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Drivers.Modeler;
import ModelObjects.ModelObject;
import ModelObjects.OneToOneDependency;
import ModelObjects.UMLInterface;

public class ModelerTest {

	Modeler m;
	
	@Before
	public void setUp() throws Exception {
		m = new Modeler();
	}

	@Test
	public void testCreateClassModelsInterface() throws Exception {
		List<String> classes = new ArrayList<String>();
		classes.add("Runners.Runner");
		m.createClassModels(classes);
		
		List<ModelObject> models = m.getObjects();
		assertFalse(models.isEmpty());
		assertEquals("Runner", models.get(0).getName());
		assertEquals(models.get(0).getClass(), UMLInterface.class);
		assertEquals(models.get(1).getClass(), OneToOneDependency.class);
	}
	
	@Test
	public void testCreateClassModelsRecursion() throws Exception {
		m.setRecursion(true);
		List<String> classes = new ArrayList<String>();
		classes.add("Parsers.HTMLClassParser");
		m.createClassModels(classes);
		
		List<ModelObject> models = m.getObjects();
		assertEquals(4, models.size());

		
	}
	
	

}
