package Parsers;

public class HTMLViolateCompositionOverInheritanceClassParser extends ClassParserDecorator {

	public HTMLViolateCompositionOverInheritanceClassParser(IParser htmlClassParser) {
		this.underlying = htmlClassParser;
	}

	@Override
	public String addConfig() {
		System.out.println("\n\nAdding color to object one or two\n\n");
		return "color=orange,";
	}

	@Override
	public String addHeader() {
		return "";
	}

}
