package Parsers;

import java.rmi.NoSuchObjectException;

import ModelObjects.ModelObject;

public class HTMLSingletonParser extends ClassParserDecorator {

	public HTMLSingletonParser(IParser p) {
		this.underlying = p;
	}

	/**
	 * Override this method to add your own configurations in graphviz. Make
	 * sure to put a comma at the end of each config.
	 */

	@Override
	public String addConfig() {
		return "color=blue,";
	}

	@Override
	public String addHeader() {
		return "singleton<br/ >";
	}
	
	

}
