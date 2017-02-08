package PatternDetectors;

import java.util.ArrayList;
import java.util.List;

import ModelObjects.Component;
import ModelObjects.DecoratesRelationship;
import ModelObjects.Decorator;
import ModelObjects.Extend;
import ModelObjects.Implement;
import ModelObjects.ModelObject;
import ModelObjects.PatternDecorator;
import ModelObjects.UMLAbstractClass;
import ModelObjects.UMLInstanceVariable;
import ModelObjects.UMLInterface;

public class DecoratorDetector implements PatternDetector {

	@Override
	public List<ModelObject> check(List<ModelObject> models) {
		
		List<ModelObject> newModels = new ArrayList<ModelObject>();
		
		for (ModelObject m : models) {
			System.out.println(m.getName());
			if (m instanceof UMLAbstractClass) {
				UMLAbstractClass c = (UMLAbstractClass) m;
				String implement = findAncestor(c, models);
				if (!implement.equals("Object")) {
					List<UMLInstanceVariable> vars = c.getInstanceVars();
					for (UMLInstanceVariable v : vars) {
						if (v.getType().equals(implement)) {
							newModels.add(new Decorator(m));
							newModels.addAll(getChildren(c, models));
							newModels.add(getAncestor(implement, models));
							newModels.add(getRelationship(c, implement, models));
						}
					}
				}
			}
		}
		
		newModels = getRest(newModels, models);
		
		return newModels;
	}
	
	private List<ModelObject> getRest(List<ModelObject> newModels, List<ModelObject> models) {
		List<ModelObject> finalReturn = new ArrayList<ModelObject>();
		for (ModelObject m : models) {
			if (!checkInDecorator(m, newModels)) {
				finalReturn.add(m);
			}
		}
		finalReturn.addAll(newModels);
		return finalReturn;
	}

	private boolean checkInDecorator(ModelObject m, List<ModelObject> newModels) {
		for (ModelObject model : newModels) {
			if (((PatternDecorator) model).contains(m)) {
//				System.out.println(model.getName() + " : " + m.getName());
//				System.out.println("here");
				return true;
			}
		}
		return false;
	}

	private ModelObject getRelationship(UMLAbstractClass c, String s, List<ModelObject> models) {
		
		for (ModelObject m : models) {
			if (m instanceof Implement) {
				Implement i = (Implement) m;
				if (i.getFirst().equals(c.getName()) && i.getSecond().equals(s)) {
					return new DecoratesRelationship(i);
				}
			}
		}
		System.err.println("No Such class exists");
		System.exit(1);
		return null;
		
	}
	
	private String findAncestor(UMLAbstractClass c, List<ModelObject> models) {
		
		for (ModelObject m : models) {
			if (m instanceof Implement) {
				Implement i = (Implement) m;
				if (i.getFirst().equals(c.getName())) {
					return i.getSecond();
				}
			}
		}
		return "Object";
	}
	
	private ModelObject getAncestor(String s, List<ModelObject> models) {
		for (ModelObject m : models) {
			if (m instanceof UMLInterface) {
				if (m.getName().equals(s)) {
					return new Component(m);
				}
			}
		}
		System.err.println("No Such class exists");
		System.exit(1);
		return null;
		
	}
	
	private List<ModelObject> getChildren(ModelObject m, List<ModelObject> models) {
		List<ModelObject> newModels = new ArrayList<ModelObject>();
		for (ModelObject model : models) {
			if (model instanceof Extend) {
				Extend e = (Extend) model;
				if (e.getSecond().equals(m.getName())) {
					ModelObject found = findClass(e.getFirst(), models);	
					newModels.add(new Decorator(found));
				}
			}
		}
		return newModels;
	}
	
	private ModelObject findClass(String s, List<ModelObject> models) {
		
		for (ModelObject m : models) {
			if (m.getName().equals(s)) {
				return m;
			}
		}
		
		System.err.println("No Such class exists");
		System.exit(1);
		return null;
	}

}
