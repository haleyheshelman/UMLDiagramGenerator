package PatternDetectors;

import java.util.ArrayList;
import java.util.List;

import ModelObjects.ModelObject;

public class ViolateCompositionOverInheritanceDetector implements PatternDetector {

	@Override
	public List<ModelObject> check(List<ModelObject> models) {
		List<ModelObject> newModels = new ArrayList<ModelObject>();

		// Detector violatores of the ViolateCompositionOverInheritance principle
		//TODO: Change this
		newModels.addAll(models);
		
		return models;
	}

}
