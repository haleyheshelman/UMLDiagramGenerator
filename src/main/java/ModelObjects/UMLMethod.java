package ModelObjects;

import java.util.Collections;
import java.util.List;

public class UMLMethod {
	
	String sig;
	String returnType;
	List<UMLParameter> params;
	
	public UMLMethod(String sig, String returnType, List<UMLParameter> params) {
		this.sig = sig;
		this.returnType = returnType;
		this.params = params;
	}
	
	public String getSigniture() {
		return this.sig;
	}
	
	public String getReturnType() {
		return this.returnType;
	}
	
	public List<UMLParameter> getParameters() {
		return Collections.unmodifiableList(params);
	}

}
