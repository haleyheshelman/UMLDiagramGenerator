package ModelObjects;

import java.util.Collections;
import java.util.List;

public class UMLInterface implements Vizable {

	List<UMLMethod> methods;
	String name;
	
	public UMLInterface(String name, List<UMLMethod> methods) {
		this.name = name;
		this.methods = methods;
	}

	public List<UMLMethod> getMethods() {
		return Collections.unmodifiableList(methods);
	}

	@Override
	public String toGraphViz() {
		StringBuilder s = new StringBuilder();
		s.append(this.name + "[shape = \"record\",");
		s.append("label=<{ ");
		s.append("<i>" + this.name + "</i>" + "|");
		
		for (UMLMethod m : this.methods) {
			String add = m.toGraphViz();
			s.append(m.toGraphViz());
		}
		
		s.append("}>];");
		String output = s.toString();
//		output = output.replace("<", "");
//		output = output.replace(">", "");
		return output;
	}
}
