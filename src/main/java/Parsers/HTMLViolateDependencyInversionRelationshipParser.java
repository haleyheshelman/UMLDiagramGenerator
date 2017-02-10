package Parsers;

public class HTMLViolateDependencyInversionRelationshipParser extends RelationshipParserDecorator {
	
	public HTMLViolateDependencyInversionRelationshipParser(IParser underlying) {
		this.underlying = underlying;
	}

	@Override
	public String addConfig() {
		return "color=magenta ";
	}

}
