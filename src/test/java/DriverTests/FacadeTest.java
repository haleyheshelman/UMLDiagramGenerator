package DriverTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Drivers.TheGreatFacadeOfChandan;
import PatternDetectors.SingletonDetector;
import PatternDetectors.ViolateCompositionOverInheritanceDetector;

public class FacadeTest {
	TheGreatFacadeOfChandan f;
	
	@Before
	public void setUp() throws Exception {
		f = new TheGreatFacadeOfChandan();
		f.initialize("docs/testsettings");
	}

	@Test
	public void testInitialize() {
		assertTrue(f.getNames().contains("Drivers.Driver"));
		assertTrue(f.getNames().contains("Drivers.Modeler"));
		assertTrue(f.getModeler().getBlacklist()[0].equals("GraphVizRunner"));
		assertFalse(f.getModeler().getSynthetic());
	}

	@Test
	public void testPatternDetectors() {
		f.addPatternDetector(new SingletonDetector());
		assertTrue(f.getModeler().getPatternDetectors().get(0) instanceof SingletonDetector);
	}
	
	@Test
	public void testSetOutput() {
		f.setOutputFile("docs/test.dot");
		assertEquals(f.getOutputFile(), "docs/test.dot");
	}
	
	@Test
	public void testSetEncoding() {
		f.setEncoding("TEST ENCODING");
		assertEquals(f.getEncoding(), "TEST ENCODING");
	}
}
