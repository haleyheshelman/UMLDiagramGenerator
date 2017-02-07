package Drivers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import PatternDetectors.PatternDetector;
import PatternDetectors.SingletonDetector;
import Runners.GraphVizRunner;
import Runners.Runner;

public class TheGreatFacadeOfChandan {
	
	private Runner runner;
	private DesignParser designParser;
	private Modeler modeler;
	public static String DEFAULT_SETTINGS = "default";
	private List<String> names = new ArrayList<String>();
	private String outputFile = "docs/target.dot";
	private String encoding = DesignParser.TYPE_HTML;
	
	public void initialize(String settings) {
		
		this.designParser = new DesignParser();
		this.modeler = new Modeler();		
		File file = new File(settings);
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Recursion Setting
		String recurse = scan.nextLine();
		recurse = recurse.substring(recurse.indexOf("=") + 1);
		this.modeler.setRecursion(Boolean.parseBoolean(recurse));
		
		//Read from package names setting
		String pack = scan.nextLine();
		System.out.println(pack);
		pack = pack.substring(pack.indexOf('=') + 1);
		boolean fromPackage = Boolean.parseBoolean(pack);

		//Get classes or package names to parse
		String whitelist = scan.nextLine();
		whitelist = whitelist.substring(whitelist.indexOf("="));
		if (whitelist.equals("=")) {
			System.out.println("Nothing to parse");
			System.exit(1);
		}
		String[] args = whitelist.substring(1).split(",");
		if (fromPackage) {
			for (int i = 0; i < args.length; i ++) {
				File directory = null;
				ClassLoader cld = Thread.currentThread().getContextClassLoader();
				String path = args[i];
				URL resource = cld.getResource(path);
				directory = new File(resource.getFile());
				if (directory.exists()) {
					String[] files = directory.list();
					for (int j = 0; j < files.length; j++) {
						if (files[j].endsWith(".class")) {
							names.add(args[i] + '.' + files[j].substring(0, files[j].length() - 6));
						}
					}
				}
			}
		} else {
			for (int i = 0; i < args.length; i++) {
				names.add(args[i]);
			}
		}
		
		//Get blacklist
		String[] blackList;
		String blacklist = scan.nextLine();
		blacklist = blacklist.substring(blacklist.indexOf("="));
		if (blacklist.equals("=")) {
			blackList = new String[0];
		} else {
			blacklist = blacklist.substring(1);
			blackList = blacklist.split(",");
		}
		
		
		//Get Synthetic
		String synth = scan.nextLine();
		synth = synth.substring(synth.indexOf("=") + 1);
		boolean synthetic = Boolean.parseBoolean(synth);
		
		this.modeler.setBlacklist(blackList);
		this.modeler.setSynthetic(synthetic);
		scan.close();
	}

	public void addPatternDetector(PatternDetector pd) {
		this.modeler.addPatternDetector(pd);
	}

	public void go() throws Exception {
		
		if (this.names.isEmpty()) {
			System.out.println("You have not initialized the program with settings. Rest in peace you");
			System.exit(1);
		}
		this.modeler.createClassModels(this.names);
		Runner gRunner = new GraphVizRunner(this.outputFile);
		this.designParser.setFactory(this.encoding);
		if (gRunner.writeToFile(this.designParser.parseObjects(this.modeler.getModels())) == -1) return;
		gRunner.launchApp();

	}
	
	public void setOutputFile(String target) {
		this.outputFile = target;
	}
	
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public Modeler getModeler() {
		return this.modeler;
	}

	public  List<String> getNames() {
		return this.names;
	}

	public String getOutputFile() {
		return this.outputFile;
	}

	public String getEncoding() {
		return this.encoding;
	}

}
