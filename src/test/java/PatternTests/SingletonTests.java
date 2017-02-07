package PatternTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Drivers.Modeler;
import ModelObjects.ModelObject;
import ModelObjects.Singleton;
import PatternDetectors.PatternDetector;
import PatternDetectors.SingletonDetector;

public class SingletonTests {
	Modeler m;
	List<String> classes;
	PatternDetector p;
	
	@Before
	public void setUp() throws Exception {
		m = new Modeler();
		classes = new ArrayList<String>();
		p = new SingletonDetector();
	}

	@Test
	public void eagerSingletonTest() {
		classes.add("Testers.EagerSingleton");
		m.createClassModels(classes);
		List<ModelObject> models = m.getModels();
		List<ModelObject> newmodels = p.check(models);
		
		boolean containsSingleton = false;
		for(ModelObject o : newmodels) {
			if(o instanceof Singleton) {
				containsSingleton = true;
			}
		}
		assertTrue(containsSingleton);
	}
}
