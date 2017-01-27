package Testers;

public class LazyBoiler {

private static LazyBoiler instance;
	
	public static LazyBoiler getInstance() {
		if (instance == null) {
			instance = new LazyBoiler();
		}
		return instance;
	}
}
