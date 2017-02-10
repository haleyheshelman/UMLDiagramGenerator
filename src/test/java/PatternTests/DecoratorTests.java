package PatternTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Drivers.Modeler;
import ModelObjects.Component;
import ModelObjects.DecoratesRelationship;
import ModelObjects.Decorator;
import ModelObjects.ModelObject;
import PatternDetectors.DecoratorDetector;
import PatternDetectors.PatternDetector;

public class DecoratorTests {
	Modeler m;
	List<String> classes;
	PatternDetector p;
	String[] b = {"java"};
	
	@Before
	public void setUp() throws Exception {
		classes = new ArrayList<String>();
		p = new DecoratorDetector();
	}

	@Test
	public void basicDecoratorTest() {
		m = new Modeler();
		m.setBlacklist(b);
		classes.add("ModelObjects.PatternDecorator");
		classes.add("ModelObjects.ModelObject");
		classes.add("ModelObjects.Singleton");
		m.createClassModels(classes);
		List<ModelObject> models = m.getModels();
		List<ModelObject> newmodels = p.check(models);
		
		int components = 0;
		int decorators = 0;
		int relationship = 0;
		for(ModelObject o : newmodels) {
			if(o instanceof Component) {
				components++;
			} else if (o instanceof Decorator) {
				decorators++;
			} else if (o instanceof DecoratesRelationship) {
				relationship++;
			}
		}
		
		assertEquals(7, newmodels.size());
		assertEquals(1, components);
		assertEquals(2, decorators);
		assertEquals(1, relationship);
	}
	
	@Test
	public void basicDecoratorTestIntegrationStyle() {
		m = new Modeler();
		m.setBlacklist(b);
		classes.add("ModelObjects.PatternDecorator");
		classes.add("ModelObjects.ModelObject");
		classes.add("ModelObjects.Singleton");
		m.addPatternDetector(p);
		m.createClassModels(classes);
		List<ModelObject> models = m.getModels();
		
		int components = 0;
		int decorators = 0;
		int relationship = 0;
		for(ModelObject o : models) {
			if(o instanceof Component) {
				components++;
			} else if (o instanceof Decorator) {
				decorators++;
			} else if (o instanceof DecoratesRelationship) {
				relationship++;
			}
		}
		
		assertEquals(7, models.size());
		assertEquals(1, components);
		assertEquals(2, decorators);
		assertEquals(1, relationship);
	}
	

}
