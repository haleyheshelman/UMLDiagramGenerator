package edu.rosehulman.csse374.revengd;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;

import ModelObjects.Extend;
import ModelObjects.Implement;
import ModelObjects.ModelObject;
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
	
	public Modeler() {
		this.models = new ArrayList<ModelObject>();
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
			
			// Use the following line to get just the name of the class rather than its full extension name.
			String superNameParsed = superName.substring(superName.lastIndexOf('/') + 1);
			if (!superNameParsed.equals("Object")) {
				superNames.add(superName);
				models.add((ModelObject) new Extend(classNode.name, classNode.superName));
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
			} else {
				List<UMLInstanceVariable> vars = createInstanceVariableModels(classNode.fields);
				models.add(new UMLClass(name, methodObjects, vars));
			}
		}
		
		if (recursion) createClassModels(superNames);
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
			
//			Type[] types = Type.getArgumentTypes(m.desc);
//			List<String> names = new ArrayList<String>(types.length);
//			List<LocalVariableNode> variables = m.localVariables;
			
			// Later get the names of the params rather than just using param.
			for (Type argType : Type.getArgumentTypes(m.desc)) {
				String s = argType.getClassName();
				s = s.substring(s.lastIndexOf('.') + 1);
				params.add(new UMLParameter(s, "param"));
			}
			
			boolean p = (m.access & Opcodes.ACC_PUBLIC) > 0;
			
			output.add(new UMLMethod(sig, returnType, params, p));
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
	
//			s.append(newClass.toGraphViz());
//			s.append("\n");
////			printClass(classNode);
////
////			printFields(classNode);
////
////			printMethods(classNode);
//
//			// TODO: Use GOOD DESIGN to parse the classes of interest and store
//			// them.
//		}
//		for (ModelObject r : rels) {
//			s.append(r.toGraphViz());
//			s.append("\n");
//		}
//		s.append("}");
//		String graphInput = s.toString();
//		graphInput = graphInput.replace("$", "");
//		graphInput = graphInput.replace("\\l", "<br/>");
//		
//		String fileName = "docs/target.dot";
//		File targetFile = new File(fileName);
//		OutputStream outputStream = new FileOutputStream(targetFile);
//		outputStream.write(graphInput.getBytes());
//		outputStream.close();
//		
//		String OS = System.getProperty("os.name");
//		String command = "";
//		if (OS.contains("Windows")){
//			command = "explorer";
//			command = "C:/Program Files (x86)/Graphviz2.38/bin/gvedit.exe";
//		} else if (OS.contains("Mac")) {
//			command = "/Applications/GraphViz.app/Contents/MacOS/GraphViz";
//		} else {
//			System.out.println("Error: Unknown operating system. Unable to open GraphViz");
//		}
//		
//		try{
//			ProcessBuilder processBuilder = new ProcessBuilder(command, fileName);
//		
//			// Start and add the process to the processes list
//			Process process = processBuilder.start();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

//	// FIXME: is it GOOD DESIGN to have a class where everything is static?
//	private static void printClass(ClassNode classNode) {
//		System.out.println("Class's JVM internal name: " + classNode.name);
//		System.out.println("User-friendly name: "
//				+ Type.getObjectType(classNode.name).getClassName());
//		System.out.println("public? "
//				+ ((classNode.access & Opcodes.ACC_PUBLIC) > 0));
//		System.out.println("Extends: " + classNode.superName);
//		System.out.println("Implements: " + classNode.interfaces);
//
//	}
//
//	private static void printFields(ClassNode classNode) {
//		// Print all fields (note the cast; ASM doesn't store generic
//		// data with its Lists)
//		@SuppressWarnings("unchecked")
//		List<FieldNode> fields = (List<FieldNode>) classNode.fields;
//		for (FieldNode field : fields) {
//			System.out.println("	Field: " + field.name);
//			System.out.println("	Internal JVM type: " + field.desc);
//			System.out.println("	User-friendly type: "
//					+ Type.getType(field.desc));
//			// Query the access modifiers with the ACC_* constants.
//
//			System.out.println("	public? "
//					+ ((field.access & Opcodes.ACC_PUBLIC) > 0));
//			// How do you tell if something has default access? (ie no
//			// access modifiers?)
//
//			System.out.println();
//		}
//	}
//
//	private static void printMethods(ClassNode classNode) {
//		@SuppressWarnings("unchecked")
//		List<MethodNode> methods = (List<MethodNode>) classNode.methods;
//		for (MethodNode method : methods) {
//			System.out.println("	Method: " + method.name);
//			System.out
//					.println("	Internal JVM method signature: " + method.desc);
//
//			System.out.println("	Return type: "
//					+ Type.getReturnType(method.desc).getClassName());
//
//			System.out.println("	Args: ");
//			for (Type argType : Type.getArgumentTypes(method.desc)) {
//				System.out.println("		" + argType.getClassName());
//				// FIXME: what is the argument's VARIABLE NAME?
//			}
//
//			// Query the access modifiers with the ACC_* constants.
//			System.out.println("	public? "
//					+ ((method.access & Opcodes.ACC_PUBLIC) > 0));
//			System.out.println("	static? "
//					+ ((method.access & Opcodes.ACC_STATIC) > 0));
//			// How do you tell if something has default access? (ie no
//			// access modifiers?)
//
//			System.out.println();
//
//			// Print the method's instructions
//			printInstructions(method);
//		}
//	}
//
//	private static void printInstructions(MethodNode methodNode) {
//		InsnList instructions = methodNode.instructions;
//		for (int i = 0; i < instructions.size(); i++) {
//
//			// We don't know immediately what kind of instruction we have.
//			AbstractInsnNode insn = instructions.get(i);
//
//			// Now we have to cast the instruction to its correct type based on
//			// the opCode.
//			// FIXME: this code has POOR DESIGN.
//			if (insn instanceof MethodInsnNode) {
//				// A method call of some sort; what other useful fields does
//				// this object have?
//				MethodInsnNode methodCall = (MethodInsnNode) insn;
//				System.out.println("		Call method: " + methodCall.owner + " "
//						+ methodCall.name);
//			} else if (insn instanceof VarInsnNode) {
//				// Some some kind of variable *LOAD or *STORE operation.
//				VarInsnNode varInsn = (VarInsnNode) insn;
//				int opCode = varInsn.getOpcode();
//				// See VarInsnNode.setOpcode for the list of possible values of
//				// opCode. These are from a variable-related subset of Java
//				// opcodes.
//			}
//			// There are others...
//			// This list of direct known subclasses may be useful:
//			// http://asm.ow2.org/asm50/javadoc/user/org/objectweb/asm/tree/AbstractInsnNode.html
//		}
//	}
}

