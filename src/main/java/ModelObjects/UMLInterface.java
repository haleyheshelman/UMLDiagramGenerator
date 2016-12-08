package ModelObjects;

import java.util.Collections;
import java.util.List;

public class UMLInterface implements UMLFile {

	List<UMLMethod> methods;

	@Override
	public List<UMLMethod> getMethods() {
		return Collections.unmodifiableList(methods);
	}
}
