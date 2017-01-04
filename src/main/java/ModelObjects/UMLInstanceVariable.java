package ModelObjects;

import java.util.Collections;
import java.util.List;

public class UMLInstanceVariable implements ModelObject{

	private String type;
	private String name;
	private boolean isPublic;
	
	public UMLInstanceVariable(String type, String name, boolean isPublic) {
		this.type = type;
		this.name = name;
		this.isPublic = isPublic;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append(isPublic + " " + type + " " + name);
		return s.toString();
	}

	public boolean getIsPublic() {
		return this.isPublic;
	}

//	public String toGraphViz() {
//		StringBuilder s = new StringBuilder();
//		
//		if (this.isPublic) {
//			s.append("+ ");
//		} else {
//			s.append("- ");
//		}
//		
//		s.append(this.name + " : ");
//		s.append(this.type +"\\l");
//	
//		return s.toString();
//	}
}
