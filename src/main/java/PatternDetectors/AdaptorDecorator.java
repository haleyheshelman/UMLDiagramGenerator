package PatternDetectors;

import java.util.ArrayList;
import java.util.List;

import ModelObjects.Component;
import ModelObjects.Implement;
import ModelObjects.ModelObject;
import ModelObjects.UMLAbstractClass;
import ModelObjects.UMLClass;
import ModelObjects.UMLInterface;

public class AdaptorDecorator implements PatternDetector {

	@Override
	public List<ModelObject> check(List<ModelObject> models) {
		List<ModelObject> newModels = new ArrayList<ModelObject>();
		for (ModelObject o : models) {
			if (o instanceof UMLClass) {
				String interfaceName = findAncestor((UMLClass) o, models);
				ModelObject inter = getAncestor(interfaceName, models);
				
			}
		}
		
		return null;
	}
	
private String findAncestor(UMLClass c, List<ModelObject> models) {
		
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

}
