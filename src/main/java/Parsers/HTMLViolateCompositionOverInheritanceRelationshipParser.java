package Parsers;

public class HTMLViolateCompositionOverInheritanceRelationshipParser extends RelationshipParserDecorator {

	public HTMLViolateCompositionOverInheritanceRelationshipParser(IParser htmlExtendParser) {
		this.underlying = htmlExtendParser;
	}

	@Override
	public String addConfig() {
		return "color=orange ";
	}

}
