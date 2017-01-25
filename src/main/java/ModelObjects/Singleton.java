package ModelObjects;

public class Singleton extends PatternDecorator {

	public Singleton(ModelObject m) {
		this.underlying = m;
	}

	@Override
	public String getName() {
		return "Singleton";
	}
	
	public ModelObject getUnderlying() {
		return this.underlying;
	}

}
