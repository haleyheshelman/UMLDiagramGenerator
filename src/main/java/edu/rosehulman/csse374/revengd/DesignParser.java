package edu.rosehulman.csse374.revengd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Factories.HTMLParserFactory;
import Factories.IParserFactory;
import ModelObjects.Extend;
import ModelObjects.Implement;
import ModelObjects.ModelObject;
import ModelObjects.UMLAbstractClass;
import ModelObjects.UMLClass;
import ModelObjects.UMLInterface;

public class DesignParser {
	
	public static String TYPE_HTML = "HTML";
	private Map<String, IParserFactory> factories;
	private IParserFactory iFactory;
	
	public DesignParser() {
		
		factories = new HashMap<String, IParserFactory>();
		factories.put(TYPE_HTML, new HTMLParserFactory());
		this.iFactory = factories.get(TYPE_HTML);
		
	}
	
	public String parseObjects(List<ModelObject> objects) {

		StringBuilder s = new StringBuilder();
		s.append("digraph uml{rankdir=BT;");
		
		for (ModelObject o : objects) {
			if (o instanceof UMLClass) {
				s.append(parseClass(o));
			} else if (o instanceof UMLAbstractClass) {
				s.append(parseAbstractClass(o));
			} else if (o instanceof UMLInterface) {
				s.append(parseInterface(o));
			}
			s.append("\n");
		}
		for (ModelObject o : objects) {
			if (o instanceof Extend) {
				s.append(parseExtend(o));
			} else if (o instanceof Implement) {
				s.append(parseImplement(o));
			}
			s.append("\n");
		}
		s.append("}");
		String output = s.toString();
		output = output.replace("$", "");
		return output;
		
	}
	
	private String parseModelObject(ModelObject o) {
		return iFactory.makeParser(o.getClass().getName()).parse(o);
	}
	
	private String parseClass(ModelObject o) {
		return iFactory.makeClassParser().parse(o);
	}
	
	private String parseMethod(ModelObject o) {
		return iFactory.makeMethodParser().parse(o);
	}
	
	private String parseInstanceVariable(ModelObject o) {
		return iFactory.makeInstanceVariableParser().parse(o);
	}
	
	private String parseInterface(ModelObject o) {
		return iFactory.makeInterfaceParser().parse(o);
	}
	
	private String parseAbstractClass(ModelObject o) {
		return iFactory.makeAbstractClassParser().parse(o);
	}
	
	private String parseExtend(ModelObject o) {
		return iFactory.makeExtendParser().parse(o);
	}
	
	private String parseImplement(ModelObject o) {
		return iFactory.makeImplementParser().parse(o);
	}
	
	private String parseParameter(ModelObject o) {
		return iFactory.makeParameterParser().parse(o);
	}
	
	public void addFactory(String key, IParserFactory factory) {
		this.factories.put(key, factory);
	}
	
	public void setFactory(String key) {
		this.iFactory = factories.get(key);
	}
	
}
