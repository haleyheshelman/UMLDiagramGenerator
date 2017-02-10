package PatternDetectors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;

import ModelObjects.Adaptee;
import ModelObjects.Adapter;
import ModelObjects.AdaptsRelationship;
import ModelObjects.Association;
import ModelObjects.Component;
import ModelObjects.IRelationship;
import ModelObjects.Implement;
import ModelObjects.ModelObject;
import ModelObjects.PatternDecorator;
import ModelObjects.Target;
import ModelObjects.UMLAbstractClass;
import ModelObjects.UMLClass;
import ModelObjects.UMLInterface;
import ModelObjects.UMLMethod;

public class AdapterDetector implements PatternDetector {

	@SuppressWarnings("unchecked")
	@Override
	public List<ModelObject> check(List<ModelObject> models) {
		List<ModelObject> newModels = new ArrayList<ModelObject>();
		for (ModelObject o : models) {
			if (o instanceof UMLClass) {
				String interfaceName = findAncestor((UMLClass) o, models);
				ModelObject inter = getAncestor(interfaceName, models);
				
				if (inter == null) continue;
				
				List<ModelObject> associations = getAssociations(o.getName(), models);
				List<UMLMethod> interMethods = new ArrayList<UMLMethod>();
				if (inter instanceof UMLClass) {
					interMethods = ((UMLClass) inter).getMethods();
				}
				if (inter instanceof UMLInterface) {
					interMethods = ((UMLInterface) inter).getMethods();
				}
				
				List<UMLMethod> myMethods = ((UMLClass) o).getMethods();
				for (UMLMethod m : myMethods) {
					if (containsMethod(m, interMethods)) {
						InsnList instructions = m.getInstructions();
						Iterator<AbstractInsnNode> i = instructions.iterator();
						while (i.hasNext()) {
							AbstractInsnNode node = i.next();
							if (node.getType() == AbstractInsnNode.METHOD_INSN) {
								MethodInsnNode methodInst = (MethodInsnNode) node;
								String owner = methodInst.owner;
								List<ModelObject> checked = checkAssociation(owner, associations);
								if (!checked.isEmpty()) {
									// We found it
									Adapter adapter = new Adapter(o);
									Target target = new Target(inter);
									List<AdaptsRelationship> rels  = new ArrayList<AdaptsRelationship>();
									for (ModelObject got : checked) {
										rels.add(new AdaptsRelationship(got));
									}
									
									newModels.add(adapter);
									newModels.add(target);
									newModels.addAll(getAdaptees(checked, models));
									newModels.addAll(rels);
								}
							}
						}
					}
				}	
			}
		}
		
		newModels = getRest(newModels, models);
		
		return newModels;
	}
	
	private List<ModelObject> getRest(List<ModelObject> newModels, List<ModelObject> models) {
		List<ModelObject> toReturn = new ArrayList<ModelObject>();
		for (ModelObject o : models) {
			boolean added = false;
			for (ModelObject p : newModels) {
				if (((PatternDecorator) p).contains(o)) {
					toReturn.add(p);
					added = true;
				}
			}
			if (!added) {
				toReturn.add(o);
			}
		}
		
		return toReturn;
	}

	private List<Adaptee> getAdaptees(List<ModelObject> checked, List<ModelObject> models) {
		List<Adaptee> adaptees = new ArrayList<Adaptee>();
		
		for(ModelObject o : checked) {
			Association a = (Association) o;
			for (ModelObject m : models) {
				if (a.getSecond().equals(m.getName())) {
					adaptees.add(new Adaptee(m));
				}
			}
		}
		return adaptees;
	}

	private List<ModelObject> checkAssociation(String owner, List<ModelObject> associations) {
		
		List<ModelObject> checked = new ArrayList<ModelObject>();
		for (ModelObject o : associations) {
			Association a = (Association) o;
			if (a.getSecond().equals(owner)) {
				checked.add(o);
			}
		}
		return checked;
	}

	private boolean containsMethod(UMLMethod m, List<UMLMethod> methods) {
		for (UMLMethod method : methods) {
			if (method.getName().equals(m.getName())) {
				return true;
			}
		}
		return false;
	}
	
	private List<ModelObject> getAssociations(String name, List<ModelObject> models) {
	
		List<ModelObject> toReturn = new ArrayList<ModelObject>();
		for (ModelObject o : models) {
			if (o instanceof Association) {
				if (((IRelationship) o).getFirst().equals(name)) {
					toReturn.add(o);
				}
			}
		}
		return toReturn;
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
				return m;
			}
		}
	}
	return null;
	
}

}
