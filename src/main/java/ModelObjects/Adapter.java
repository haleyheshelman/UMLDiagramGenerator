package ModelObjects;

public class Adapter extends PatternDecorator {

	public Adapter(ModelObject underlying) {
		this.underlying = underlying;
	}
	
	@Override
	public String getSelector() {
		return "adapter" + this.underlying.getSelector();
	}

	@Override
	public String getName() {
		return "adapter" + this.underlying.getName();
	}

}
