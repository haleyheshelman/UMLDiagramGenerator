package ModelObjects;

import java.util.Collections;
import java.util.List;

public class UMLClass implements UMLFile{

	List<UMLMethod> methods;
	List<UMLInstanceVariable> instVars;
	List<UMLFile> supClassInterface;
	
	@Override
	public List<UMLMethod> getMethods() {
		return Collections.unmodifiableList(methods);
	}
	
	public List<UMLInstanceVariable> getInstanceVars() {
		return Collections.unmodifiableList(instVars);
	}
	
	public List<UMLFile> getSuperClassAndInterfaces() {
		return Collections.unmodifiableList(supClassInterface);
	}
	
}
