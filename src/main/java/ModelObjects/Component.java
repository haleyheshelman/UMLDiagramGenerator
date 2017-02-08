package ModelObjects;

public class Component extends PatternDecorator {

	public Component(ModelObject m) {
		this.underlying = m;
	}

	@Override
	public String getSelector() {
		return "comp" + this.underlying.getSelector();
	}

	@Override
	public String getName() {
		return this.underlying.getName() + "Component";
	}

}
