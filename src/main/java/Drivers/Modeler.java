package Drivers;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
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
import PatternDetectors.PatternDetector;

public class Modeler {
	/**
	 * Reads in a list of Java Classes and creates a list of model objects that
	 * can be retrieved later.
	 * 
	 * @param args
	 *            : the names of the classes, separated by spaces. For example:
	 *            java example.DesignParser java.lang.String
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	// TODO: Add stuff for generic type getting.
	private List<ModelObject> models;
	private boolean recursion = false;
	private List<String> primitives;
	private List<PatternDetector> pds;
	private boolean synthetic = false;
	private String[] blacklist = {};

	public Modeler() {
		this.models = new ArrayList<ModelObject>();
		this.primitives = new ArrayList<String>();
		this.pds = new ArrayList<PatternDetector>();
		this.primitives.add("byte");
		this.primitives.add("short");
		this.primitives.add("int");
		this.primitives.add("long");
		this.primitives.add("float");
		this.primitives.add("double");
		this.primitives.add("boolean");
		this.primitives.add("char");
		this.primitives.add("void");
		this.primitives.add("java.lang.String");
		this.primitives.add("java.lang.Object");
		this.primitives.add("String");
	}

	private void createClassModel(String s) throws ClassNotFoundException, IOException {
		List<String> names = new ArrayList<String>();
		names.add(s);
		createClassModels(names);
	}

	@SuppressWarnings("unchecked")
	public void createClassModels(List<String> classes) {

		/**
		 * This creates a list of model objects for the given list of class
		 * names. This will recurse through super classes if the recursion is
		 * set to true. NOTE: This does not return the list of model objects.
		 * 
		 * @param classes
		 *            - List of class names with full extension
		 *
		 */

		List<String> superNames = new ArrayList<String>();
		for (String className : classes) {
			ClassReader reader = null;
			try {
				reader = new ClassReader(className);
			} catch (IOException e) {
				e.printStackTrace();
			}

			ClassNode classNode = new ClassNode();
			reader.accept(classNode, ClassReader.EXPAND_FRAMES);

			String superName = classNode.superName;
			String parsedName = superName.substring(superName.lastIndexOf("/") + 1);
			// Use the following line to get just the name of the class rather
			// than its full extension name.
			if (!checkBlackList(superName)) {
				if (!parsedName.equals("Object")) {					
					superNames.add(superName);
					models.add((ModelObject) new Extend(classNode.name, classNode.superName));
					if (recursion) {
						try {
							createClassModel(superName);
						} catch (ClassNotFoundException | IOException e) {
							e.printStackTrace();
						}
					}
				}
			}

			// Now add the interfaces of the class
			for (String interfaceName : (List<String>) classNode.interfaces) {
				models.add((ModelObject) new Implement(classNode.name, interfaceName));
			}

			// Now actually model the class we were given.
			String name = classNode.name;
			name = name.substring(name.lastIndexOf('/') + 1);
			int modifier = classNode.access;

			// Get Method Objects and Instance Variables in the case of classes
			// and abstract classes
			List<UMLMethod> methodObjects = createMethodModels(classNode.methods);

			if (Modifier.isInterface(modifier)) {
				models.add(new UMLInterface(name, methodObjects));
			} else if (Modifier.isAbstract(modifier)) {
				List<UMLInstanceVariable> vars = createInstanceVariableModels(classNode.fields);
				models.add(new UMLAbstractClass(name, methodObjects, vars));

			} else {
				List<UMLInstanceVariable> vars = createInstanceVariableModels(classNode.fields);
				models.add(new UMLClass(name, methodObjects, vars));
			}
		}

		getAssociations();
		getDependencies();

		detectPatterns();

	}

	private boolean checkBlackList(String name) {
		
		if (this.blacklist.length == 0) {
			return false;
		}
		for (String s : this.blacklist) {
			if (name.startsWith(s) || name.endsWith(s)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkIfPrimitive(String type) {
		for (String prim : this.primitives) {
			if (prim.equals(type)) {
				return true;
			}
		}
		return false;
	}

	private List<String> getTypes(String s) {
		String[] types = s.split(" ");
		List<String> output = new ArrayList<String>();

		for (String t : types) {
			if (t.contains(":")) {
				t = t.substring(t.lastIndexOf(':') + 1);
			}

			if (!output.contains(t))
				output.add(t);
		}

		return output;
	}

	private boolean containsAssociation(Association a, List<ModelObject> associations) {
		for (ModelObject check : associations) {
			if (a.isEqual(check)) {
				return true;
			}
		}
		for (ModelObject check : this.models) {
			if (a.isEqual(check)) {
				return true;
			}
		}
		return false;
	}

	private boolean containsDependency(Dependency d, List<ModelObject> dependencies) {
		for (ModelObject check : dependencies) {
			if (d.isEqual(check)) {
				return true;
			}
		}
		for (ModelObject check : this.models) {
			if (d.isEqual(check)) {
				return true;
			}
		}
		return false;
	}

	private void getAssociations() {

		List<ModelObject> newModels = new ArrayList<ModelObject>();
		for (ModelObject o : this.models) {

			if (o instanceof UMLClass) {
				List<UMLInstanceVariable> vars = ((UMLClass) o).getInstanceVars();
				for (UMLInstanceVariable v : vars) {
					String type = v.getType();
					addAssociation(type, o, newModels);
				}

			}

			if (o instanceof UMLAbstractClass) {
				List<UMLInstanceVariable> vars = ((UMLAbstractClass) o).getInstanceVars();
				for (UMLInstanceVariable v : vars) {
					String type = v.getType();
					addAssociation(type, o, newModels);
				}
			}
		}
		this.models.addAll(newModels);
	}

	private void addAssociation(String type, ModelObject o, List<ModelObject> newModels) {
		if (checkBlackList(type)) {
			return;
		}
		if (type.contains("[]")) {
			String parsedType = type.substring(0, type.indexOf("["));
			Association d = new OneToManyAssociation(o.getName(), parsedType);
			if (!checkIfPrimitive(parsedType) && !checkAssociations(o.getName(), parsedType)) {
				newModels.add(d);
			}
		} else if (type.contains(":")) {
			List<String> types = getTypes(type);
			for (String t : types) {
				Association a = new OneToManyAssociation(o.getName(), t);
				if (!checkIfPrimitive(t) && !containsAssociation(a, newModels))
					newModels.add(a);

			}
		} else {
			Association a = new OneToOneAssociation(o.getName(), type);
			if (!checkIfPrimitive(type) && !containsAssociation(a, newModels))
				newModels.add(a);
		}
	}
	
	private boolean checkAssociations(String name, String type) {
		type = type.substring(type.lastIndexOf('.') + 1);
		type = type.substring(type.lastIndexOf('/') + 1);
		for (ModelObject o : this.models) {
			if (o instanceof Association) {
				boolean checkOne = ((Association) o).getFirst().equals(name);
				boolean checkTwo = ((Association) o).getSecond().equals(type);
				if (checkOne && checkTwo) {
					return true;
				}
			}
		}
		return false;
	}

	private void getDependencies() {
		List<ModelObject> newModels = new ArrayList<ModelObject>();
		for (ModelObject o : this.models) {

			if (o instanceof UMLClass) {

				List<UMLMethod> methods = ((UMLClass) o).getMethods();
				for (UMLMethod m : methods) {

					// Return Type Dependency
					String returnType = m.getReturnType();
					addDependency(returnType, o, newModels);
					
					// Params
					List<UMLParameter> params = m.getParameters();
					
					for (UMLParameter p : params) {
						String type = p.getType();
						addDependency(type, o, newModels);
					}
					
					List<String> inLines = m.getInLineDependencies();
					for (String type : inLines) {
						addDependency(type, o, newModels);
					}
				}
			}
			
			if (o instanceof UMLAbstractClass) {

				List<UMLMethod> methods = ((UMLAbstractClass) o).getMethods();
				for (UMLMethod m : methods) {
										
					// Return Type Dependency
					String returnType = m.getReturnType();
					addDependency(returnType, o, newModels);
					
					// Params
					List<UMLParameter> params = m.getParameters();
					
					for (UMLParameter p : params) {
						String type = p.getType();
						addDependency(type, o, newModels);
					}
					
					List<String> inLines = m.getInLineDependencies();
					for (String type : inLines) {
						addDependency(type, o, newModels);
					}
					
				}
			}
			
			if (o instanceof UMLInterface) {
				List<UMLMethod> methods = ((UMLInterface) o).getMethods();
				for (UMLMethod m : methods) {

					// Return Type Dependency
					String returnType = m.getReturnType();
					addDependency(returnType, o, newModels);
					
					// Params
					List<UMLParameter> params = m.getParameters();
					if (params.isEmpty()) continue;
					
					for (UMLParameter p : params) {
						String type = p.getType();
						addDependency(type, o, newModels);
					}
					
					List<String> inLines = m.getInLineDependencies();
					for (String type : inLines) {
						addDependency(type, o, newModels);
					}
				}
			}
		}
		this.models.addAll(newModels);
	}
	
	private void addDependency(String type, ModelObject o, List<ModelObject> newModels) {
		if (checkBlackList(type)) {
			return;
		}
		if (type.contains("[]")) {
			String parsedType = type.substring(0, type.indexOf("["));
			Dependency d = new OneToManyDependency(o.getName(), parsedType);
			if (!checkIfPrimitive(parsedType) && !checkAssociations(o.getName(), parsedType)) {
				newModels.add(d);
			}
		} else if (type.contains(":")) {
			List<String> types = getTypes(type);
			for (String t : types) {
				Dependency d = new OneToManyDependency(o.getName(), t);
				if (!checkIfPrimitive(t) && !checkAssociations(o.getName(), t)
						&& !containsDependency(d, newModels))
					newModels.add(d);
			}
		} else {
			Dependency d = new OneToOneDependency(o.getName(), type);

			if (!checkIfPrimitive(type) && !checkAssociations(o.getName(), type)
					&& !containsDependency(d, newModels))
				newModels.add(d);
		}
	}

	private List<ModelObject> createInstanceVariableModels(List<FieldNode> vars) {

		/**
		 * This creates a list of model objects for the instance variables in
		 * the given list of field nodes.
		 * 
		 * @param vars
		 *            - List of field nodes
		 * @return Returns a list of variableModels
		 */

		List<ModelObject> varModels = new ArrayList<ModelObject>();

		for (FieldNode f : vars) {
			Type t = Type.getType(f.desc);
			String s = t.getClassName();
			
			if (f.signature != null && f.signature.contains("<")) {
				String add = parseGeneric(f.signature);
				if (add.contains(";")) {
					add = add.replace(";", "");
				}
				s = add;
			}

			boolean p = (f.access & Opcodes.ACC_PUBLIC) > 0;
			boolean stat = (f.access & Opcodes.ACC_STATIC) > 0;

			varModels.add(new UMLInstanceVariable(s, f.name, p, stat));
		}
		return varModels;

	}

	@SuppressWarnings("unchecked")
	private List<ModelObject> createMethodModels(List<MethodNode> methods) {

		/**
		 * This returns a list of model objects for the methods in the list of
		 * method nodes.
		 * 
		 * @param methods
		 *            - List of methodNodes
		 * @return List of method model objects.
		 */

		List<ModelObject> output = new ArrayList<ModelObject>();

		for (MethodNode m : methods) {
			String returnType = Type.getReturnType(m.desc).getClassName();
			String sig = m.name;
			
			List<UMLParameter> params = new ArrayList<UMLParameter>();
			if (m.signature != null) {
				// TODO: Come back here. Fix param types for compound types
				String getReturnTypeFrom = m.signature.substring(m.signature.indexOf(")") + 1);
				returnType = parseGeneric(getReturnTypeFrom);
				if (returnType.equals("Z")) {
					returnType = "boolean";
				}
				if (returnType.equals("V")) {
					returnType = "void";
				}
				if (returnType.contains(";")) {
					returnType = returnType.replace(";", "");
				}

				String getParamsTypes = m.signature.substring(m.signature.indexOf("("), m.signature.indexOf(")") + 1);
				List<String> argTypes = parseParamGeneric(getParamsTypes);

				for (String t : argTypes) {
					if (t.contains(";")) {
						t = t.replace(";", "");
					}
					
					params.add(new UMLParameter(t, "param"));
				}
			} else {
				for (Type argType : Type.getArgumentTypes(m.desc)) {
					String s = argType.getClassName();

					params.add(new UMLParameter(s, "param"));
				}
			}

			boolean p = (m.access & Opcodes.ACC_PUBLIC) > 0;
			boolean stat = (m.access & Opcodes.ACC_STATIC) > 0;


			// In code inspection
			
			InsnList inst = m.instructions;
			Iterator<AbstractInsnNode> i = inst.iterator();
			AbstractInsnNode node = null;
			List<String> inLines = new ArrayList<String>();
			while (i.hasNext()) {
				node = (AbstractInsnNode) i.next();
				int type = node.getType();
				if (type == AbstractInsnNode.METHOD_INSN) {
					MethodInsnNode mNode = (MethodInsnNode) node;
					String toAdd = mNode.owner;
					if (!inLines.contains(toAdd)) {
						inLines.add(toAdd);
					}
				}
			}
			output.add(new UMLMethod(sig, returnType, params, inLines, inst, p, stat));
		}

		return output;
	}

	public void setRecursion(boolean r) {

		/**
		 * Sets the modeler to model super classes recursively.
		 * 
		 * @param r
		 *            - Boolean, true for recursive property
		 */

		this.recursion = r;
	}

	public void setSynthetic(boolean s) {
		this.synthetic = s;
	}

	public void setBlacklist(String[] black) {
		this.blacklist = black;
	}
	
	public List<ModelObject> getModels() {
		return this.models;
	}

	public void addPatternDetector(PatternDetector pd) {
		this.pds.add(pd);
	}

	private void detectPatterns() {

		if (this.pds.isEmpty()) {
			return;
		}

		for (PatternDetector p : this.pds) {
			this.models = p.check(this.models);
		}
	}

	private List<String> parseParamGeneric(String f) {
		int openCount = 0;
		String current = "";
		ArrayList<String> recurse = new ArrayList<String>();
		for (int i = 0; i < f.length(); i++) {
			current = current + f.charAt(i);
			if (f.charAt(i) == '<') {
				openCount = openCount + 1;
			}
			if (f.charAt(i) == '>') {
				openCount = openCount - 1;
			}

			if (f.charAt(i) == ';') {
				if (openCount == 0) {
					recurse.add(current);
					current = "";
				}
			}
		}
		ArrayList<String> output = new ArrayList<String>();
		for (String s : recurse) {
			output.add(parseGeneric(s));
		}
		return output;
	}

	private String parseGeneric(String f) {
		if (!f.contains("<")) {
			if (!f.contains("/")) {
				return f;
			}
			String toReturn = f.substring(Math.max(f.lastIndexOf("/"), 0) + 1, f.indexOf(";"));
			return toReturn;
		}

		String startName = f.substring(0, f.indexOf('<'));
		startName = startName.substring(startName.lastIndexOf('/') + 1);

		String searcher = f.substring(f.indexOf("<") + 1, f.lastIndexOf(">"));
		int openCount = 0;
		for (int i = 0; i < searcher.length(); i++) {
			if (searcher.charAt(i) == '<') {
				openCount = openCount + 1;
			}
			if (searcher.charAt(i) == '>') {
				openCount = openCount - 1;
			}
			if (searcher.charAt(i) == ';') {
				if (openCount == 0) {
					String partOne = searcher.substring(0, i + 1);
					String partTwo = searcher.substring(i + 1);

					if (partTwo.isEmpty()) {
						return startName + ":" + parseGeneric(partOne);
					}
					return startName + ":" + parseGeneric(partOne) + " " + parseGeneric(partTwo);
				}
			}
		}
		return "";
	}

	public  boolean getRecursion() {
		return this.recursion;
	}

	public  String[] getBlacklist() {
		return this.blacklist;
	}

	public boolean getSynthetic() { 
		return this.synthetic;
	}

	public List<PatternDetector> getPatternDetectors() {
		return this.pds;
	}

}
