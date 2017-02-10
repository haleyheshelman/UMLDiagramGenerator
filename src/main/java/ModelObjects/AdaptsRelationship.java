package ModelObjects;

public class AdaptsRelationship extends PatternDecorator {

	@Override
	public String getSelector() {
		return "adaprel" + this.underlying.getSelector();
	}

	@Override
	public String getName() {
		return "Adapts Relation" + this.underlying.getName();
	}

}
