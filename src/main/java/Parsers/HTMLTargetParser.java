package Parsers;

public class HTMLTargetParser extends ClassParserDecorator {

	public HTMLTargetParser(IParser underlying) {
		this.underlying = underlying;
	}
	
	@Override
	public String addConfig() {
		return "color=red,";
	}

	@Override
	public String addHeader() {
		return "target<br />";
	}

}
