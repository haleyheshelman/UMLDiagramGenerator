package ModelObjects;

import java.util.Collections;
import java.util.List;

public class UMLInterface implements Vizable {

	List<UMLMethod> methods;

	public List<UMLMethod> getMethods() {
		return Collections.unmodifiableList(methods);
	}

	@Override
	public String toGraphViz() {
		// TODO Auto-generated method stub
		return null;
	}
}
