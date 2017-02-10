package Parsers;

public class HTMLAdaptsRelationshipParser extends RelationshipParserDecorator {

	
	
	public HTMLAdaptsRelationshipParser(IParser underlying) {
		this.underlying = underlying;
	}

	@Override
	public String addConfig() {
		return "label=\"adapts\"" ;
	}

}
