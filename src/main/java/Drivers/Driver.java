package Drivers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import PatternDetectors.AdapterDetector;
import PatternDetectors.DecoratorDetector;
import PatternDetectors.SingletonDetector;
import PatternDetectors.ViolateCompositionOverInheritanceDetector;
import PatternDetectors.ViolateDependencyInversionDetector;
import Runners.GraphVizRunner;
import Runners.Runner;

public class Driver {

	public static void main(String[] args) throws Exception {
		
		TheGreatFacadeOfChandan f = new TheGreatFacadeOfChandan();
		int argsLength = args.length;
		if (argsLength == 0) {
			f.initialize(TheGreatFacadeOfChandan.DEFAULT_SETTINGS);
		}  else {
			f.initialize(args[0]);
		}
				
//		f.addPatternDetector(new SingletonDetector());
//		f.addPatternDetector(new ViolateCompositionOverInheritanceDetector());
//		f.addPatternDetector(new DecoratorDetector());
//		f.addPatternDetector(new ViolateDependencyInversionDetector());
//		f.addPatternDetector(new AdapterDetector());
		f.go();
	}
}
