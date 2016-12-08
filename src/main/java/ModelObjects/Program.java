package ModelObjects;

import java.util.Collections;
import java.util.List;

public class Program {

	List<UMLFile> files;

	public List<UMLFile> getFiles() {
		return Collections.unmodifiableList(files);
	}
	
}
