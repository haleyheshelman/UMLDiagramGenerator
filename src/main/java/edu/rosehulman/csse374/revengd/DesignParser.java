package edu.rosehulman.csse374.revengd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import ModelObjects.Extend;
import ModelObjects.Implement;
import ModelObjects.UMLClass;
import ModelObjects.Vizable;
import Parsers.ClassParser;

// FIXME: everything about this class is completely terribly designed.
// If your code even remotely resembles this class, you will be sad.
public class DesignParser {
	/**
	 * Reads in a list of Java Classes and reverse engineers their design.
	 * 
	 * @param args
	 *            : the names of the classes, separated by spaces. For example:
	 *            java example.DesignParser java.lang.String
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	
	String hello;
	String goodbye;
	int k;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException,
			ClassNotFoundException {

		// TODO: Learn how to create separate Run Configurations so you can run
		// this code without changing the arguments each time.

		// FIXME: this code has POOR DESIGN. If you keep this code as-is for
		// your main method, you will be sad about your grade.

		List<Vizable> classes = new ArrayList<Vizable>();
		List<Vizable> rels = new ArrayList<Vizable>();
		ClassParser newClassParser = new ClassParser();
		StringBuilder s = new StringBuilder();
		s.append("digraph uml{rankdir=BT;");
		
		for (String className : args) {
			ClassReader reader = new ClassReader(className);

			ClassNode classNode = new ClassNode();
			reader.accept(classNode, ClassReader.EXPAND_FRAMES);

			// Now we can navigate the classNode and look for things we are
			// interested in.
			String superName = classNode.superName;
			superName = superName.substring(superName.lastIndexOf('/') + 1);
			if(!superName.equals("Object") && classNode.superName != null) {
				rels.add(new Extend(classNode.name, classNode.superName));
			}
			
			for (String interfaceName : (List<String>) classNode.interfaces) {
				rels.add(new Implement(classNode.name, interfaceName));
			}
			
			// This makes the class node
			String name = classNode.name;
			name = name.substring(name.lastIndexOf('/') + 1);
			System.out.println(classNode.signature);
			System.out.println(classNode.attrs);
			Vizable newClass = new UMLClass(name, newClassParser.parseMethods(classNode), newClassParser.parseInstanceVariables(classNode));
			classes.add(newClass);
			s.append(newClass.toGraphViz());
			s.append("\n");
//			printClass(classNode);
//
//			printFields(classNode);
//
//			printMethods(classNode);

			// TODO: Use GOOD DESIGN to parse the classes of interest and store
			// them.
		}
		for (Vizable r : rels) {
			s.append(r.toGraphViz());
			s.append("\n");
		}
		s.append("}");
		String graphInput = s.toString();
		graphInput = graphInput.replace("$", "");
		
		String fileName = "docs/target.dot";
		File targetFile = new File(fileName);
		OutputStream outputStream = new FileOutputStream(targetFile);
		outputStream.write(graphInput.getBytes());
		outputStream.close();
		
		String OS = System.getProperty("os.name");
		String command = "";
		if (OS.contains("Windows")){
			//TODO: write the windows code
		} else if (OS.contains("Mac")) {
			command = "/Applications/GraphViz.app/Contents/MacOS/GraphViz";
		} else {
			System.out.println("Error: Unknown operating system. Unable to open GraphViz");
		}
		
		try{
			ProcessBuilder processBuilder = new ProcessBuilder(command, fileName);
		
			// Start and add the process to the processes list
			Process process = processBuilder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// FIXME: is it GOOD DESIGN to have a class where everything is static?
	private static void printClass(ClassNode classNode) {
		System.out.println("Class's JVM internal name: " + classNode.name);
		System.out.println("User-friendly name: "
				+ Type.getObjectType(classNode.name).getClassName());
		System.out.println("public? "
				+ ((classNode.access & Opcodes.ACC_PUBLIC) > 0));
		System.out.println("Extends: " + classNode.superName);
		System.out.println("Implements: " + classNode.interfaces);

	}

	private static void printFields(ClassNode classNode) {
		// Print all fields (note the cast; ASM doesn't store generic
		// data with its Lists)
		@SuppressWarnings("unchecked")
		List<FieldNode> fields = (List<FieldNode>) classNode.fields;
		for (FieldNode field : fields) {
			System.out.println("	Field: " + field.name);
			System.out.println("	Internal JVM type: " + field.desc);
			System.out.println("	User-friendly type: "
					+ Type.getType(field.desc));
			// Query the access modifiers with the ACC_* constants.

			System.out.println("	public? "
					+ ((field.access & Opcodes.ACC_PUBLIC) > 0));
			// How do you tell if something has default access? (ie no
			// access modifiers?)

			System.out.println();
		}
	}

	private static void printMethods(ClassNode classNode) {
		@SuppressWarnings("unchecked")
		List<MethodNode> methods = (List<MethodNode>) classNode.methods;
		for (MethodNode method : methods) {
			System.out.println("	Method: " + method.name);
			System.out
					.println("	Internal JVM method signature: " + method.desc);

			System.out.println("	Return type: "
					+ Type.getReturnType(method.desc).getClassName());

			System.out.println("	Args: ");
			for (Type argType : Type.getArgumentTypes(method.desc)) {
				System.out.println("		" + argType.getClassName());
				// FIXME: what is the argument's VARIABLE NAME?
			}

			// Query the access modifiers with the ACC_* constants.
			System.out.println("	public? "
					+ ((method.access & Opcodes.ACC_PUBLIC) > 0));
			System.out.println("	static? "
					+ ((method.access & Opcodes.ACC_STATIC) > 0));
			// How do you tell if something has default access? (ie no
			// access modifiers?)

			System.out.println();

			// Print the method's instructions
			printInstructions(method);
		}
	}

	private static void printInstructions(MethodNode methodNode) {
		InsnList instructions = methodNode.instructions;
		for (int i = 0; i < instructions.size(); i++) {

			// We don't know immediately what kind of instruction we have.
			AbstractInsnNode insn = instructions.get(i);

			// Now we have to cast the instruction to its correct type based on
			// the opCode.
			// FIXME: this code has POOR DESIGN.
			if (insn instanceof MethodInsnNode) {
				// A method call of some sort; what other useful fields does
				// this object have?
				MethodInsnNode methodCall = (MethodInsnNode) insn;
				System.out.println("		Call method: " + methodCall.owner + " "
						+ methodCall.name);
			} else if (insn instanceof VarInsnNode) {
				// Some some kind of variable *LOAD or *STORE operation.
				VarInsnNode varInsn = (VarInsnNode) insn;
				int opCode = varInsn.getOpcode();
				// See VarInsnNode.setOpcode for the list of possible values of
				// opCode. These are from a variable-related subset of Java
				// opcodes.
			}
			// There are others...
			// This list of direct known subclasses may be useful:
			// http://asm.ow2.org/asm50/javadoc/user/org/objectweb/asm/tree/AbstractInsnNode.html
		}
	}
}

