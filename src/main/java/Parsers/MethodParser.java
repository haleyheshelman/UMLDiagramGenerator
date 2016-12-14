package Parsers;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;

import ModelObjects.UMLMethod;
import ModelObjects.UMLParameter;

public class MethodParser {

	public static UMLMethod parseMethod(MethodNode m) {
		
		String returnType =Type.getReturnType(m.desc).getClassName();
		String sig = m.name;
		
		List<UMLParameter> params = new ArrayList<UMLParameter>();
		Type[] types = Type.getArgumentTypes(m.desc);
		List<String> names = new ArrayList<String>(types.length);
		List<LocalVariableNode> variables = m.localVariables;
		
//		for (int i = 0; i < types.length; i++) {
//			names.add(variables.get(i).name);
//		}
		
		// TODO: Make this get the names of the parameters rather than naming them param.
		
		int count = 0;
		for (Type argType : Type.getArgumentTypes(m.desc)) {
			String s = argType.getClassName();
			s = s.substring(s.lastIndexOf('.') + 1);
			params.add(new UMLParameter(s, "param"));
			count++;
		}
		
		boolean p = (m.access & Opcodes.ACC_PUBLIC) > 0;
		
		return new UMLMethod(sig, returnType, params, p);
	}
}
