package PatternDetectors;

import java.util.ArrayList;
import java.util.List;

import ModelObjects.Association;
import ModelObjects.Dependency;
import ModelObjects.IRelationship;
import ModelObjects.ModelObject;
import ModelObjects.UMLAbstractClass;
import ModelObjects.UMLInterface;
import ModelObjects.ViolateDependencyInversion;

public class ViolateDependencyInversionDetector implements PatternDetector {

	@Override
	public List<ModelObject> check(List<ModelObject> models) {
		
		List<ModelObject> newModels = new ArrayList<ModelObject>();
		for (ModelObject o : models) {
			if (o instanceof Association || o instanceof Dependency) {
				IRelationship r = (IRelationship) o;
				ModelObject two = findObject(r.getSecond(), models);
				
				if (two == null) {
					newModels.add(o);
				} else if (!(two instanceof UMLInterface) && !(two instanceof UMLAbstractClass)) {
					ModelObject violation = new ViolateDependencyInversion(o);
					newModels.add(violation);
				} else {
					newModels.add(o);
				}
			} else {
				newModels.add(o);
			}
		}
		
		return newModels;
	}

	public ModelObject findObject(String name, List<ModelObject> models) {
		ModelObject output = null;
		
		for (ModelObject o : models) {
			if (o.getName().equals(name)) {
				output = o;
				break;
			}
		}
		return output;
	}
	
}
