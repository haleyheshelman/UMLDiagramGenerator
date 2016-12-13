package Parsers;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import ModelObjects.UMLInstanceVariable;
import ModelObjects.UMLMethod;

public class ClassParser {

	public List<UMLMethod> parseMethods(ClassNode c) {
		
		List<UMLMethod> methods = new ArrayList<UMLMethod>();
		
		List<MethodNode> methodNodes = (List<MethodNode>) c.methods;
		for (MethodNode m : methodNodes) {
			methods.add((UMLMethod) MethodParser.parseMethod(m));
		}
		
		return methods;
	}

	public List<UMLInstanceVariable> parseInstanceVariables(ClassNode c) {
		
		List<UMLInstanceVariable> vars = new ArrayList<UMLInstanceVariable>();
		List<FieldNode> fields = (List<FieldNode>) c.fields;

		for (FieldNode f : fields) {
			vars.add(new UMLInstanceVariable(Type.getType(f.desc).toString(), f.name));
		}
		return vars;
	}
}
