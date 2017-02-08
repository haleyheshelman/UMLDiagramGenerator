package ModelObjects;

public class DecoratesRelationship extends PatternDecorator {

	public DecoratesRelationship(IRelationship m) {
		this.underlying = m;
	}

	@Override
	public String getSelector() {
		return "decrel" + this.underlying.getSelector();
	}

	@Override
	public String getName() {
		return this.underlying.getName() + "Decorates Relationship";
	}

}
