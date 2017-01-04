package Runners;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GraphVizRunner implements Runner {

	private String inputFile;
	
	public GraphVizRunner(String inputFile) {
		this.inputFile = inputFile;
	}
	
	@Override
	public void launchApp() {
		String OS = System.getProperty("os.name");
		String command = "";
		if (OS.contains("Windows")){
			command = "explorer";
			command = "C:/Program Files (x86)/Graphviz2.38/bin/gvedit.exe";
		} else if (OS.contains("Mac")) {
			command = "/Applications/GraphViz.app/Contents/MacOS/GraphViz";
		} else {
			System.out.println("Error: Unknown operating system. Unable to open GraphViz");
		}
		try{
			ProcessBuilder processBuilder = new ProcessBuilder(command, this.inputFile);
		
			// Start and add the process to the processes list
			processBuilder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	@SuppressWarnings("resource")
	@Override
	public int writeToFile(String s) throws IOException {
		
		/**
		 * This writes the contents of the string inputed to the file given on construction. This will return 1 when the file is
		 * successfully opened and written to. Otherwise, it will return -1.
		 */
		
		FileOutputStream stream;
		try {
			stream = new FileOutputStream(inputFile);
		} catch (FileNotFoundException e) {
			return -1;
		}
		try {
			stream.write(s.getBytes());
		} catch (IOException e) {
			return -1;
		}
		stream.close();
		return 1;
	}

}
