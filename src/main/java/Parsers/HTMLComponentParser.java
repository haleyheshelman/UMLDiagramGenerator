package Parsers;

public class HTMLComponentParser extends ClassParserDecorator{

	public HTMLComponentParser(IParser p) {
		this.underlying = p;
	}

	@Override
	public String addConfig() {
		return "style=filled,fillcolor=green,";
	}

	@Override
	public String addHeader() {
		return "component<br />";
	}

}
