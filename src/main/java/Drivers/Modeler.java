package Drivers;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import ModelObjects.Association;
import ModelObjects.Dependency;
import ModelObjects.Extend;
import ModelObjects.IRelationship;
import ModelObjects.Implement;
import ModelObjects.ModelObject;
import ModelObjects.OneToManyAssociation;
import ModelObjects.OneToManyDependency;
import ModelObjects.OneToOneAssociation;
import ModelObjects.OneToOneDependency;
import ModelObjects.UMLAbstractClass;
import ModelObjects.UMLClass;
import ModelObjects.UMLInstanceVariable;
import ModelObjects.UMLInterface;
import ModelObjects.UMLMethod;
import ModelObjects.UMLParameter;

public class Modeler {
	/**
	 * Reads in a list of Java Classes and creates a list of model objects that can be
	 * retrieved later.
	 * 
	 * @param args
	 *            : the names of the classes, separated by spaces. For example:
	 *            java example.DesignParser java.lang.String
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private List<ModelObject> models;
	private boolean recursion = false;
	private List<String> primitives;
	
	public Modeler() {
		this.models = new ArrayList<ModelObject>();
		this.primitives = new ArrayList<String>();
		this.primitives.add("byte");
		this.primitives.add("short");
		this.primitives.add("int");
		this.primitives.add("long");
		this.primitives.add("float");
		this.primitives.add("double");
		this.primitives.add("boolean");
		this.primitives.add("char");
		this.primitives.add("void");
	}
	
	private void createClassModel(String s) throws ClassNotFoundException, IOException {
		List<String> names = new ArrayList<String>();
		names.add(s);
		createClassModels(names);
	}
	
	@SuppressWarnings("unchecked")
	public void createClassModels(List<String> classes) throws IOException, ClassNotFoundException  {
		
		/**
		 * This creates a list of model objects for the given list of class names. This will recurse through super classes 
		 * if the recursion is set to true. NOTE: This does not return the list of model objects.
		 * 
		 * @param classes - List of class names with full extension
		 *
		 */
		
		List<String> superNames = new ArrayList<String>();
		for (String className : classes) {
			ClassReader reader = new ClassReader(className);
			
			ClassNode classNode = new ClassNode();
			reader.accept(classNode, ClassReader.EXPAND_FRAMES);
			
			String superName = classNode.superName;
			System.out.println(superName);
			
			// Use the following line to get just the name of the class rather than its full extension name.
			String superNameParsed = superName.substring(superName.lastIndexOf('/') + 1);
			if (!superNameParsed.equals("Object")) {
				superNames.add(superName);
				models.add((ModelObject) new Extend(classNode.name, classNode.superName));
				if (recursion) createClassModel(superName);
			}
			
			// Now add the interfaces of the class
			for (String interfaceName : (List<String>) classNode.interfaces) {
				models.add((ModelObject) new Implement(classNode.name, interfaceName));
			}			
			
			// Now actually model the class we were given.
			String name = classNode.name;
			name = name.substring(name.lastIndexOf('/') + 1);
			int modifier = classNode.access;
			
			// Get Method Objects and Instance Variables in the case of classes and abstract classes
			List<UMLMethod> methodObjects = createMethodModels(classNode.methods);
			if (Modifier.isInterface(modifier)) {
				models.add(new UMLInterface(name, methodObjects));
			} else if (Modifier.isAbstract(modifier)) {
				List<UMLInstanceVariable> vars = createInstanceVariableModels(classNode.fields);
				models.add(new UMLAbstractClass(name, methodObjects, vars));
				
				// Create Associations
				getAssociations(name, vars);
				
			} else {
				List<UMLInstanceVariable> vars = createInstanceVariableModels(classNode.fields);
				models.add(new UMLClass(name, methodObjects, vars));
				
				// Create Associations
				getAssociations(name, vars);
			}
			getDependencies(name, methodObjects);

		}
	}
	
	private void getAssociations(String className, List<UMLInstanceVariable> vars) {
		for (UMLInstanceVariable var : vars) {
			
			Association association;
			
			if (var.getType().contains(className)) continue;
			if (primitives.contains(var.getType())) continue;
			
			if (var.getType().contains("[]")) {
				association = new OneToManyAssociation(className, var.getType());
			} else {
				association = new OneToOneAssociation(className, var.getType());

			}
			if (!containsAssociation(association)) {
				models.add((ModelObject) association);
			}
		}
	}
	
	private void getDependencies(String className, List<UMLMethod> methods) {
		for (UMLMethod m : methods) {
			
			Dependency dependency;
			if (m.getReturnType().contains("[]")) {
				dependency = new OneToManyDependency(className, m.getReturnType());
			} else {
				dependency = new OneToOneDependency(className, m.getReturnType());

			}
			if (!containsDependency(dependency)) {
				if (!containsLikeAssociation(dependency)) {
					if (!m.getReturnType().contains(className)) {
						if (!primitives.contains(m.getReturnType())) {							
							models.add((ModelObject) dependency);
						}
					}
				}
			}
			
			for (UMLParameter p : m.getParameters()) {
				if (p.getType().contains(className)) continue;
				if (primitives.contains(p.getType())) continue;
				
				if (p.getType().contains("[]")) {
					dependency = new OneToManyDependency(className, p.getType());
				} else {
					dependency = new OneToOneDependency(className, p.getType());
				}
				if (!containsDependency(dependency)) {
					if (!containsLikeAssociation(dependency)) {
						models.add((ModelObject) dependency);
					}
				}
			}
		}
	}
	
	private boolean containsLikeAssociation(Dependency dependency) {
		
		for (ModelObject o : this.models) {
			
			if ((o instanceof OneToOneAssociation) && (dependency instanceof OneToOneDependency)) {
				return ((dependency.getFirst().equals(((IRelationship) o).getFirst()))) && ((dependency.getSecond().equals(((IRelationship) o).getSecond())));
			} else if ((o instanceof OneToManyAssociation) && (dependency instanceof OneToManyDependency)) {
				return ((dependency.getFirst().equals(((IRelationship) o).getFirst()))) && ((dependency.getSecond().equals(((IRelationship) o).getSecond())));
			}
		}
		
		return false;
	}

	private boolean containsDependency(Dependency dependency) {
		for (ModelObject m : this.models) {
			if (m instanceof OneToOneDependency) {
				boolean checkOne = ((OneToOneDependency) m).getFirst().equals(dependency.getFirst());
				boolean checkTwo = ((OneToOneDependency) m).getSecond().equals(dependency.getSecond());
				return (checkOne && checkTwo);
			} else if (m instanceof OneToManyDependency) {
				boolean checkOne = ((OneToManyDependency) m).getFirst().equals(dependency.getFirst());
				boolean checkTwo = ((OneToManyDependency) m).getSecond().equals(dependency.getSecond());
				return (checkOne && checkTwo);
			}
		}
		return false;
	}

	private boolean containsAssociation(Association association) {
		
		for (ModelObject m : this.models) {
			if (m instanceof OneToOneAssociation) {
				boolean checkOne = ((OneToOneAssociation) m).getFirst().equals(association.getFirst());
				boolean checkTwo = ((OneToOneAssociation) m).getSecond().equals(association.getSecond());
				return (checkOne && checkTwo);
			} else if (m instanceof OneToManyAssociation) {
				boolean checkOne = ((OneToManyAssociation) m).getFirst().equals(association.getFirst());
				boolean checkTwo = ((OneToManyAssociation) m).getSecond().equals(association.getSecond());
				return (checkOne && checkTwo);
			}
		}
		return false;
	}

	private List<UMLInstanceVariable> createInstanceVariableModels(List<FieldNode> vars) {
		
		/**
		 * This creates a list of model objects for the instance variables in the given list of field nodes.
		 * 
		 * @param vars - List of field nodes
		 * @return Returns a list of variableModels
		 */
		
		List<UMLInstanceVariable> varModels = new ArrayList<UMLInstanceVariable>();

		for (FieldNode f : vars) {
			Type t = Type.getType(f.desc);
			String s = t.getClassName();
			s = s.substring(s.lastIndexOf('.') + 1);
			
			boolean p = (f.access & Opcodes.ACC_PUBLIC) > 0;
			
			varModels.add(new UMLInstanceVariable(s, f.name, p));
		}
		return varModels;
		
	}
	
	private List<UMLMethod> createMethodModels(List<MethodNode> methods) {
		
		/**
		 * This returns a list of model objects for the methods in the list of method nodes.
		 * 
		 * @param methods - List of methodNodes
		 * @return List of method model objects.
		 */
		
		List<UMLMethod> output = new ArrayList<UMLMethod>();
		
		for (MethodNode m : methods) {
			
			String returnType =Type.getReturnType(m.desc).getClassName();
			returnType = returnType.substring(returnType.lastIndexOf('.') + 1);
			String sig = m.name;
			
			List<UMLParameter> params = new ArrayList<UMLParameter>();
			
			// Later get the names of the params rather than just using param.
			for (Type argType : Type.getArgumentTypes(m.desc)) {
				String s = argType.getClassName();
				s = s.substring(s.lastIndexOf('.') + 1);
				params.add(new UMLParameter(s, "param"));
			}
			
			boolean p = (m.access & Opcodes.ACC_PUBLIC) > 0;
			
			output.add(new UMLMethod(sig, returnType, params, p));
			
			// In code inspection
			if (m.instructions.size() != 0) {
				System.out.println(m.instructions.get(0));

			}
		}
		
		return output;
	}
	
	public void setRecursion(boolean r) {
		
		/**
		 * Sets the modeler to model super classes recursively.
		 * @param r - Boolean, true for recursive property
		 */
		
		this.recursion = r;
	}
	
	public List<ModelObject> getObjects() {
		return this.models;
	}
	
}

