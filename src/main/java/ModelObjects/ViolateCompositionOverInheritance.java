package ModelObjects;

public class ViolateCompositionOverInheritance extends PatternDecorator {

	public ViolateCompositionOverInheritance(ModelObject o) {
		this.underlying = o;
	}
	
	@Override
	public String getName() {
		return "ViolateCompositionOverInheritance";
	}

	@Override
	public String getSelector() {
		return "compoverinher" + this.underlying.getSelector();
	}

}
