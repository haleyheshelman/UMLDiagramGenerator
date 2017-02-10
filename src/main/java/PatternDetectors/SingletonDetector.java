package PatternDetectors;

import java.util.ArrayList;
import java.util.List;

import ModelObjects.ModelObject;
import ModelObjects.Singleton;
import ModelObjects.UMLClass;
import ModelObjects.UMLInstanceVariable;
import ModelObjects.UMLMethod;

public class SingletonDetector implements PatternDetector {

	@Override
	public List<ModelObject> check(List<ModelObject> models) {
		List<ModelObject> newModels = new ArrayList<ModelObject>();
		List<ModelObject> methodChecks = new ArrayList<ModelObject>();
		
		for (ModelObject m : models) {
			if (m.getClass() != UMLClass.class) {
				newModels.add(m);
				continue;
			}
			
			List<UMLInstanceVariable> vars = ((UMLClass) m).getInstanceVars();
			for (UMLInstanceVariable v : vars) {
				boolean isStatic = v.getIsStatic();
				boolean isPublic = v.getIsPublic();
				String type = v.getParsedType();
				
				if (!type.equals(m.getName())) {
					continue;
				}
				
				if (isStatic) {
					if (isPublic) {
						ModelObject singleton = new Singleton(m);
						newModels.add(singleton);
						continue;
					} else {
						methodChecks.add(m);
						continue;
					}
				} else {
					continue;
				}
			}
			if (!methodChecks.contains(m)) {
				newModels.add(m);
			}
		}
		
		List<ModelObject> made = new ArrayList<ModelObject>();
		for (ModelObject o : methodChecks) {
			List<UMLMethod> methods = ((UMLClass) o).getMethods();
			for (UMLMethod method : methods) {
				boolean isStatic = method.getIsStatic();
				boolean isPublic = method.getIsPublic();
				String returnType = method.getParsedReturnType();
				
				if (!returnType.equals(o.getName())) {
					continue;
				}
				
				if (isStatic && isPublic) {
					ModelObject singleton = new Singleton(o);
					newModels.add(singleton);
					made.add(o);
					continue;
				} else {
					continue;
				}
				
			}
			if (!made.contains(o)) newModels.add(o);
		}		
		return newModels;
	}

}
