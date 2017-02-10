package ModelObjects;

public class ViolateDependencyInversion extends PatternDecorator {
	
	public ViolateDependencyInversion(ModelObject underlying) {
		this.underlying = underlying;
	}

	@Override
	public String getSelector() {
		return "vdi" + this.underlying.getSelector();
	}

	@Override
	public String getName() {
		return "violate dependency inversion" + this.underlying.getName();
	}

}
