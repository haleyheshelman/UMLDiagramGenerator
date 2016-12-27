package Runners;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Runner {
	public void launchApp();
	public int writeToFile(String s) throws FileNotFoundException, IOException;
}
