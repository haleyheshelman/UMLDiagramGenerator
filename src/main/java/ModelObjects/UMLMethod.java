package ModelObjects;

import java.util.Collections;
import java.util.List;

public class UMLMethod {
	
	String sig;
	String returnType;
	List<UMLParameter> params;
	boolean isPublic;
	
	public UMLMethod(String sig, String returnType, List<UMLParameter> params, boolean isPublic) {
		this.sig = sig;
		this.returnType = returnType;
		this.params = params;
		this.isPublic = isPublic;
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
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append(returnType + " " + sig + "(");
		for(int i = 0; i<params.size();i++){
			s.append(params.get(i).toString());
		}
		s.append(")\n");
		return s.toString();
	}

	public String toGraphViz() {
		StringBuilder s = new StringBuilder();
		if (this.isPublic) {
			s.append("+ ");
		} else {
			s.append("- ");
		}
		
		s.append(sig + "(");
		for (UMLParameter param : this.params) {
			s.append(param.toGraphViz());
			s.append(", ");
		}
		s.delete(s.length()-2, s.length()-1);
		s.append(") : " + this.returnType);
		s.append("\\l");
		
		return s.toString();
	}

}
