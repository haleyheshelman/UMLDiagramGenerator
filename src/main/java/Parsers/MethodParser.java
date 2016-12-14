package Parsers;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.MethodNode;

import ModelObjects.UMLMethod;
import ModelObjects.UMLParameter;

public class MethodParser {

	public static UMLMethod parseMethod(MethodNode m) {
		
		String returnType =Type.getReturnType(m.desc).getClassName();
		String sig = m.name;
		
		List<UMLParameter> params = new ArrayList<UMLParameter>();
		List<MethodNode> names = m.parameters;
		for (Type argType : Type.getArgumentTypes(m.desc)) {
			String s = argType.getClassName();
			s = s.substring(s.lastIndexOf('.') + 1);
			params.add(new UMLParameter(s, "param"));

		}
		
		return new UMLMethod(sig, returnType, params);
	}
}
