package Parsers;

public class HTMLAdapterParser extends ClassParserDecorator {

	public HTMLAdapterParser(IParser underlying) {
		this.underlying = underlying;
	}
	
	@Override
	public String addConfig() {
		return "color=red,";
	}

	@Override
	public String addHeader() {
		return "adapter<br />";
	}

}
