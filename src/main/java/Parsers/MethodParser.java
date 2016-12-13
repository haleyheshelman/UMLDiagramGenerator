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
		String sig = m.desc;
		
		List<UMLParameter> params = new ArrayList<UMLParameter>();
		for (Type argType : Type.getArgumentTypes(m.desc)) {
			params.add(new UMLParameter(argType.getClassName(), "param"));
		}
		
		return new UMLMethod(sig, returnType, params);
	}
}
