package ModelObjects;

import java.util.Collections;
import java.util.List;

import org.objectweb.asm.tree.InsnList;

public class UMLMethod implements ModelObject {
	
	String sig;
	String returnType;
	List<UMLParameter> params;
	List<String> inLineDependencies;
	InsnList instructions;
	boolean isPublic;
	boolean isStatic;
	
	public UMLMethod(String sig, String returnType, List<UMLParameter> params, List<String> inLines, InsnList instructions, boolean isPublic, boolean isStatic) {
		this.sig = sig;
		this.returnType = returnType;
		this.params = params;
		this.instructions = instructions;
		this.inLineDependencies = inLines;
		this.isPublic = isPublic;
		this.isStatic = isStatic;
	}
	
	public List<String> getInLineDependencies() {
		return inLineDependencies;
	}

	public String getSignature() {
		return this.sig;
	}
		
	public String getReturnType() {
		return this.returnType;
	}
	
	public String getParsedReturnType() {
		String output = this.returnType.substring(this.returnType.lastIndexOf('.') + 1);
		output = output.substring(output.lastIndexOf('/') + 1);
		return output;
	}
	
	public List<UMLParameter> getParameters() {
		return Collections.unmodifiableList(params);
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append(returnType + " " + sig + "(");
		for(int i = 0; i<params.size();i++){
			s.append(params.get(i).toString());
		}
		s.append(")");
		return s.toString();
	}

	@Override
	public String getName() {
		return this.sig;
	}
	
	public boolean getIsPublic() {
		return this.isPublic;
	}

	public boolean getIsStatic() {
		return this.isStatic;
	}

	@Override
	public String getSelector() {
		return "method";
	}

	public InsnList getInstructions() {
		return this.instructions;
	}

}
