package Parsers;

public class HTMLViolateCompositionOverInheritanceClassParser extends ClassParserDecorator {

	public HTMLViolateCompositionOverInheritanceClassParser(IParser htmlClassParser) {
		this.underlying = htmlClassParser;
	}

	@Override
	public String addConfig() {
		return "color=orange,";
	}

	@Override
	public String addHeader() {
		return "";
	}

}
