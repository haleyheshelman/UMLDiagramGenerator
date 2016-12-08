package ModelObjects;

import java.util.Collections;
import java.util.List;

public class UMLMethod {
	
	String sig;
	String type;
	List<UMLKeyWord> keys;
	List<UMLInstanceVariable> instVars;
	
	public String getSigniture() {
		return this.sig;
	}
	
	public String getType() {
		return this.type;
	}
	
	public List<UMLInstanceVariable> getInstanceVars() {
		return Collections.unmodifiableList(instVars);
	}
	
	public List<UMLKeyWord> getKeyWords() {
		return Collections.unmodifiableList(keys);
	}

}
