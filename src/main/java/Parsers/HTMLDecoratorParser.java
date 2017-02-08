package Parsers;

public class HTMLDecoratorParser extends ClassParserDecorator {

	public HTMLDecoratorParser(IParser p) {
		this.underlying = p;
	}

	@Override
	public String addConfig() {
		return "style=filled,fillcolor=green,";
	}

	@Override
	public String addHeader() {
		return "decorator<br />";
	}

}
