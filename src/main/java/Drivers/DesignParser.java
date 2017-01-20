package Drivers;

import java.rmi.NoSuchObjectException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Factories.HTMLParserFactory;
import Factories.IParserFactory;
import ModelObjects.ModelObject;

public class DesignParser {
	
	public static String TYPE_HTML = "HTML";
	private Map<String, IParserFactory> factories;
	private IParserFactory iFactory;
	
	public DesignParser() {
		
		factories = new HashMap<String, IParserFactory>();
		factories.put(TYPE_HTML, new HTMLParserFactory());
		this.iFactory = factories.get(TYPE_HTML);
		
	}
	
	public String parseObjects(List<ModelObject> objects) throws Exception {

		StringBuilder s = new StringBuilder();
		s.append("digraph uml{rankdir=BT; concentrate=true;node[shape = box];");
		
		for (ModelObject o : objects) {
			s.append(parseModelObject(o));
		}
		s.append("}");
		String output = s.toString();
		output = output.replace("$", "");
		return output;
		
	}
	
	private String parseModelObject(ModelObject o) throws Exception {
		return iFactory.makeParser(o.getClass()).parse(o);
	}
	
	public void addFactory(String key, IParserFactory factory) {
		this.factories.put(key, factory);
	}
	
	public void setFactory(String key) {
		this.iFactory = factories.get(key);
	}
	
}
