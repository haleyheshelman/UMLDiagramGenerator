package ModelObjects;

import java.util.Collections;
import java.util.List;

public class UMLInterface implements ModelObject {

	List<UMLMethod> methods;
	String name;
	
	public UMLInterface(String name, List<UMLMethod> methods) {
		this.name = name;
		this.methods = methods;
	}

	public List<UMLMethod> getMethods() {
		return Collections.unmodifiableList(methods);
	}

	@Override
	public String getName() {
		return this.name;
	}

}
