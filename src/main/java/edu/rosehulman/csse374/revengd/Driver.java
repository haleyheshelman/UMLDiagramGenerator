package edu.rosehulman.csse374.revengd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Runners.GraphVizRunner;
import Runners.Runner;

public class Driver {

	public static void main(String[] args) throws IOException,
	ClassNotFoundException {
		
		Modeler m = new Modeler();
		
		List<String> names = new ArrayList<String>();
		if (args[args.length - 1].equals("r")) {
			m.setRecursion(true);
			for (int i = 0; i < args.length - 1; i++) {
				names.add(args[i]);
			}
		} else {
			for (int i = 0; i < args.length; i++) {
				names.add(args[i]);
			}
		}
		m.createClassModels(names);
		
		String fileName = "docs/target.dot";
		
		Runner gRunner = new GraphVizRunner(fileName);
		DesignParser parser = new DesignParser(DesignParser.TYPE_HTML);
		if (gRunner.writeToFile(parser.parseObjects(m.getObjects())) == -1) return;
		gRunner.launchApp();
	}

}
