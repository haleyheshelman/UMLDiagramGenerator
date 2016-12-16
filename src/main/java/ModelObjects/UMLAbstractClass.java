package ModelObjects;

import java.util.Collections;
import java.util.List;

public class UMLAbstractClass implements Vizable{

	List<UMLMethod> methods;
	List<UMLInstanceVariable> instVars;
	String name;
	
	public UMLAbstractClass(String name, List<UMLMethod> methods, List<UMLInstanceVariable> instVars) {
		this.methods = methods;
		this.instVars = instVars;
		this.name = name;
	}
	
	public List<UMLMethod> getMethods() {
		return Collections.unmodifiableList(methods);
	}
	
	public List<UMLInstanceVariable> getInstanceVars() {
		return Collections.unmodifiableList(instVars);
	}

	@Override
	public String toGraphViz() {
		StringBuilder s = new StringBuilder();
		s.append(this.name + "[shape = \"record\",");
		s.append("label=\"{ ");
		s.append("<I>" + this.name  + "</I>" + "|");
		
		if (!this.instVars.isEmpty()) {
			for (UMLInstanceVariable var : this.instVars) {
				s.append(var.toGraphViz());
			}
			s.append("|");
		}
		
		for (UMLMethod m : this.methods) {
			s.append(m.toGraphViz());
		}
		
		s.append("}\"];");
		String output = s.toString();
//		output = output.replace("<", "");
//		output = output.replace(">", "");
		return output;
	}
}
