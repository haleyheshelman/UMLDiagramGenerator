package ModelObjects;

import java.util.Collections;
import java.util.List;

public class UMLAbstractClass implements ModelObject{

	List<UMLMethod> methods;
	List<UMLInstanceVariable> instVars;
	String name;
	
	public UMLAbstractClass(String name, List<UMLMethod> methods, List<UMLInstanceVariable> instVars) {
		this.methods = methods;
		this.instVars = instVars;
		this.name = name;
	}
	
	public List<UMLMethod> getMethods() {
		return Collections.unmodifiableList(methods);
	}
	
	public List<UMLInstanceVariable> getInstanceVars() {
		return Collections.unmodifiableList(instVars);
	}

	@Override
	public String getName() {
		return this.name;
	}

}
