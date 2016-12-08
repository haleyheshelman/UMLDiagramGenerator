package ModelObjects;

import java.util.Collections;
import java.util.List;

public class UMLInstanceVariable {

	String type;
	String name;
	List<UMLKeyWord> keys;
	
	public String getName() {
		return this.name;
	}
	
	public String getType() {
		return this.type;
	}
	
	public List<UMLKeyWord> getInstanceVars() {
		return Collections.unmodifiableList(keys);
	}
}
