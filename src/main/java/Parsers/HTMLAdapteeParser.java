package Parsers;

public class HTMLAdapteeParser extends ClassParserDecorator {

	public HTMLAdapteeParser(IParser underlying) {
		this.underlying = underlying;
	}
	
	@Override
	public String addConfig() {
		return "color=red,";
	}

	@Override
	public String addHeader() {
		return "adaptee<br />";
	}

}
