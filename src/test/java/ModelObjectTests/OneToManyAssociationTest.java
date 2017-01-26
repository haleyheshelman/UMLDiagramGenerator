package ModelObjectTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ModelObjects.OneToManyAssociation;

public class OneToManyAssociationTest {

	OneToManyAssociation i = new OneToManyAssociation("this", "that");
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testGetName() {
		String expected = "One to many association";
		String actual = i.getName();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetFirst() {
		String expected = "this";
		String actual = i.getFirst();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetSecond() {
		String expected = "that";
		String actual = i.getSecond();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testToString() {
		String expected = "this->that";
		String actual = i.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testIsOpposite() {
		
		OneToManyAssociation newAssociation = new OneToManyAssociation("this", "that");
		OneToManyAssociation opposite = new OneToManyAssociation("that", "this");
		assertFalse(i.isOpposite(newAssociation));
		assertTrue(i.isOpposite(opposite));
		
	}

}
