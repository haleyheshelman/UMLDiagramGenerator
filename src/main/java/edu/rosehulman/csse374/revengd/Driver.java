package edu.rosehulman.csse374.revengd;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import Runners.GraphVizRunner;
import Runners.Runner;

public class Driver {

	public static void main(String[] args) throws IOException,
	ClassNotFoundException {
		
		if (args.length == 0) {
			System.out.println("Nothing to parse");
			System.exit(1);
		}
		
		Modeler m = new Modeler();
		
		List<String> names = new ArrayList<String>();
		if (args[args.length - 1].equals("r")) {
			m.setRecursion(true);
			for (int i = 0; i < args.length - 1; i++) {
				names.add(args[i]);
			}
		} else if (args[args.length - 1].equals("p")) {
			for (int i = 0; i < args.length - 1; i ++) {
				File directory = null;
				ClassLoader cld = Thread.currentThread().getContextClassLoader();
				String path = '/' + args[i];
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
		}
		else {
			for (int i = 0; i < args.length; i++) {
				names.add(args[i]);
			}
		}
		m.createClassModels(names);
		
		String fileName = "docs/target.dot";
		
		Runner gRunner = new GraphVizRunner(fileName);
		DesignParser parser = new DesignParser();
		parser.setFactory(DesignParser.TYPE_HTML);
		if (gRunner.writeToFile(parser.parseObjects(m.getObjects())) == -1) return;
		gRunner.launchApp();
	}

}
