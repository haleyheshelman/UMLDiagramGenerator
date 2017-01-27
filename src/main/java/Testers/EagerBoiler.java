package Testers;

public class EagerBoiler {
	private static EagerBoiler instance = new EagerBoiler();
	
	public static EagerBoiler getInstance() {
		return instance;
	}
}
