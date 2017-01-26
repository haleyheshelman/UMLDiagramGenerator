package PatternDetectors;

import java.util.ArrayList;
import java.util.List;

import ModelObjects.Extend;
import ModelObjects.IRelationship;
import ModelObjects.ModelObject;
import ModelObjects.UMLClass;
import ModelObjects.ViolateCompositionOverInheritance;

public class ViolateCompositionOverInheritanceDetector implements PatternDetector {

	@Override
	public List<ModelObject> check(List<ModelObject> models) {
		List<ModelObject> newModels = new ArrayList<ModelObject>();

		// Detector violators of the ViolateCompositionOverInheritance principle

		for (ModelObject o : models) {
			if (!(o instanceof Extend)) {
				newModels.add(o);
				continue;
			}
			IRelationship mo = (IRelationship) o;
			if (checkViolate(mo.getSecond(), models)) {
				
				List<ModelObject> news = getWrappedObjects(o, models);
				
//				newModels.addAll(news);
				newModels.add(new ViolateCompositionOverInheritance(o));
			} else {
				
				newModels.add(o);
			}
		}

		return newModels;
	}

	private List<ModelObject> getWrappedObjects(ModelObject o, List<ModelObject> models) {
		
		String first = ((IRelationship) o).getFirst();
		String second = ((IRelationship) o).getSecond();
		List<ModelObject> news = new ArrayList<ModelObject>();
		
		for (ModelObject m : models) {
			if (m.getName().equals(first) || m.getName().equals(second)) {
				news.add(new ViolateCompositionOverInheritance(m));
			}
			if (news.size() == 2) {
				break;
			}
		}
		
		return news;
	}

	private boolean checkViolate(String second, List<ModelObject> models) {
		
		for (ModelObject o : models) {
			if (o.getName().equals(second)) {
				if (o.getSelector().equals("class")) {
					return true;
				}
			}
		}
		
		return false;
	}

}
