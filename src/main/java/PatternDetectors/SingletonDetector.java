package PatternDetectors;

import java.util.ArrayList;
import java.util.List;

import ModelObjects.ModelObject;

public class SingletonDetector implements PatternDetector {

	@Override
	public List<ModelObject> check(List<ModelObject> models) {
		List<ModelObject> newModels = new ArrayList<ModelObject>();
		// Check for singletons
		// TODO: Change this
		newModels.addAll(models);
		
		return newModels;
	}

}
