package ModelObjects;

import java.util.Collections;
import java.util.List;

public class UMLClass implements Vizable{

	List<UMLMethod> methods;
	List<UMLInstanceVariable> instVars;
	String name;
	
	public UMLClass(String name, List<UMLMethod> methods, List<UMLInstanceVariable> instVars) {
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
	
	public void addMethod(UMLMethod method) {
		this.methods.add(method);
	}
	
	public void addInstanceVariable(UMLInstanceVariable ia) {
		this.instVars.add(ia);
	}
	
	public String toString(){
		StringBuilder m = new StringBuilder();
		m.append(name + "[");
		for(int i = 0; i< methods.size();i++){
			m.append(methods.get(i).toString()+ " ");
		}
		m.append("]\n");
		m.append("[");
		for(int i = 0; i< instVars.size();i++){
			m.append(instVars.get(i).toString() + ", ");
		}
		m.append("]\n");
		return m.toString();
		
	}
	
	public String toGraphViz() {
		StringBuilder s = new StringBuilder();
		s.append(this.name + "[shape = \"record\",");
		s.append("label=<{ ");
		s.append(this.name + "|");
		
		if (!this.instVars.isEmpty()) {
			for (UMLInstanceVariable var : this.instVars) {
				s.append(var.toGraphViz());
			}
			s.append("|");
		}
		
		for (UMLMethod m : this.methods) {
			s.append(m.toGraphViz());
		}
		
		s.append("}>];");
		String output = s.toString();
//		output = output.replace("<", "");
//		output = output.replace(">", "");
		return output;
	}
	
}
