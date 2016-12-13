package ModelObjects;

import java.util.Collections;
import java.util.List;

public class UMLClass implements UMLFile{

	List<UMLMethod> methods;
	List<UMLInstanceVariable> instVars;
	String name;
	
	public UMLClass(String name, List<UMLMethod> methods, List<UMLInstanceVariable> instVars) {
		this.methods = methods;
		this.instVars = instVars;
		this.name = name;
	}
	
	@Override
	public List<UMLMethod> getMethods() {
		return Collections.unmodifiableList(methods);
	}
	
	public List<UMLInstanceVariable> getInstanceVars() {
		return Collections.unmodifiableList(instVars);
	}
	
	public void addMethod(UMLMethod method) {
		this.methods.add(method);
	}
	
	public void addInstanceVariable(UMLInstanceVariable ia) {
		this.instVars.add(ia);
	}
	
}
