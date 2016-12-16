package ModelObjects;

import java.util.Collections;
import java.util.List;

public class UMLAbstractClass implements Vizable{

	List<UMLMethod> methods;
	List<UMLInstanceVariable> instVars;
	List<Vizable> supClassInterface;
	
	public List<UMLMethod> getMethods() {
		return Collections.unmodifiableList(methods);
	}
	
	public List<UMLInstanceVariable> getInstanceVars() {
		return Collections.unmodifiableList(instVars);
	}
	
	public List<Vizable> getSuperClassAndInterfaces() {
		return Collections.unmodifiableList(supClassInterface);
	}

	@Override
	public String toGraphViz() {
		// TODO Auto-generated method stub
		return null;
	}
}
