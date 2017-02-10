package ModelObjects;

public class Adaptee extends PatternDecorator {

	public Adaptee(ModelObject underlying) {
		this.underlying = underlying;
	}
	
	@Override
	public String getSelector() {
		return "adap" + this.underlying.getSelector();
	}

	@Override
	public String getName() {
		return "Adaptee" + this.underlying.getName();
	}

}
