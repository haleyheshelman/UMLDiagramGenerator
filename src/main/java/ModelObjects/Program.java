package ModelObjects;

import java.util.Collections;
import java.util.List;

public class Program {

	List<Vizable> files;

	public List<Vizable> getFiles() {
		return Collections.unmodifiableList(files);
	}
	
}
