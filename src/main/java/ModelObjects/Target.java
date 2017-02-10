package ModelObjects;

public class Target extends PatternDecorator {

	public Target(ModelObject underlying) {
		this.underlying = underlying;
	}
	
	@Override
	public String getSelector() {
		return "tar" + this.underlying.getSelector();
	}

	@Override
	public String getName() {
		return "Target" + this.underlying.getName();
	}

}
