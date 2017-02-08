package Parsers;

public class HTMLDecoratesRelationshipParser extends RelationshipParserDecorator {

	public HTMLDecoratesRelationshipParser(IParser p) {
		this.underlying = p;
	}

	@Override
	public String addConfig() {
		return "label=\"decorates\" ";
	}

}
