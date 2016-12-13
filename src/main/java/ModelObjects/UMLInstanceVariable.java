package ModelObjects;

import java.util.Collections;
import java.util.List;

public class UMLInstanceVariable {

	String type;
	String name;
	
	public UMLInstanceVariable(String type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getType() {
		return this.type;
	}
}
