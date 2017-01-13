package RunnerTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import Runners.GraphVizRunner;
import Runners.Runner;

public class GraphVizRunnerTest {

	Runner r;
	
	@Before
	public void setUp() throws Exception {
		r = new GraphVizRunner("docs/testing.dot");
	}

	@Test
	public void testWriteToFile() throws FileNotFoundException, IOException {
		
		assertEquals(r.writeToFile("hello"), 1);
	}

}
