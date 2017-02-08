package ModelObjects;

public abstract class PatternDecorator implements ModelObject {

	protected ModelObject underlying;
	@Override
	public abstract String getName();
	
	public ModelObject getUnderlying() {
		return this.underlying;
	}
	
	public boolean contains(ModelObject m) {
		
		return this.underlying.equals(m);
		
	}
}
