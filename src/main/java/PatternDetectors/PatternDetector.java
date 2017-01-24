package PatternDetectors;

import java.util.List;

import ModelObjects.ModelObject;

public interface PatternDetector {

	public List<ModelObject> check(List<ModelObject> models);
	
}
