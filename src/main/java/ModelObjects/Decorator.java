package ModelObjects;

public class Decorator extends PatternDecorator {

	public Decorator(ModelObject m) {
		this.underlying = m;
	}

	@Override
	public String getSelector() {
		return "dec" + this.underlying.getSelector();
	}

	@Override
	public String getName() {
		return this.underlying.getName() + "Decorator";
	}

}
