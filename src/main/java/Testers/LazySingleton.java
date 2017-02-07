package Testers;

public class LazySingleton {

private static LazySingleton instance;
	
	public static LazySingleton getInstance() {
		if (instance == null) {
			instance = new LazySingleton();
		}
		return instance;
	}
}
