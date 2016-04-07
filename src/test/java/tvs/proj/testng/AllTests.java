package tvs.proj.testng;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.*;
import static org.testng.Assert.*; 
import tvs.proj.testng.exceptions.*;

public class AllTests {

	private Zoo zoo;
	private Animal tiger1;
	private Animal tiger2;
	private Animal tiger3;
	private Animal tiger4;
	private Habitat habitat;
	private List<Animal> animals;
	
	@BeforeClass
	public void setUp() {
		zoo = new Zoo("Lisboa", 500, 20);
		tiger1 = new Animal("Supah", "tiger");
		tiger2 = new Animal("Gumbo", "tiger");
		tiger3 = new Animal("Rambo", "tiger");
		tiger4 = new Animal("Lambo", "tiger");
		animals = new ArrayList<Animal>();
		animals.add(tiger1);
		
		try {
			zoo.add(tiger1);
			zoo.add(tiger2);
			zoo.add(tiger3);
		} catch (InvalidOperationException e) {}
	}
	
	@Test
	public void consTestCase1() {
		try {
			habitat= new Habitat(4, animals, 4, zoo);
		} catch (InvalidOperationException e) {}
	}
	
	@Test(expectedExceptions=InvalidOperationException.class)
	public void consTestCase2() {
		try {
			habitat= new Habitat(3, animals, 2, zoo);
		} catch (InvalidOperationException e) {}
	}
	
	@Test(expectedExceptions=InvalidOperationException.class)
	public void consTestCase4() {
		animals.remove(tiger1);
		try {
			habitat= new Habitat(7, animals, 6, zoo);
		} catch (InvalidOperationException e) {}
	}
	
	@Test(expectedExceptions=InvalidOperationException.class)
	public void consTestCase6() {
		animals.add(tiger2);
		try {
			habitat= new Habitat(9, animals, 0, zoo);
		} catch (InvalidOperationException e) {}
	}
	
	@Test(expectedExceptions=InvalidOperationException.class)
	public void consTestCase8() {
		animals.add(tiger2);
		animals.add(tiger3);
		animals.add(tiger4);
		try {
			habitat= new Habitat(16, animals, 12, zoo);
		} catch (InvalidOperationException e) {}
	}
	
	@Test
	public void hasTestCase1() {
		try {
			habitat= new Habitat(4, animals, 4, zoo);
		} catch (InvalidOperationException e) {}
		assertTrue(habitat.has(tiger1));
	}
	
	@Test
	public void hasTestCase2() {
		try {
			habitat= new Habitat(4, animals, 4, zoo);
		} catch (InvalidOperationException e) {}
		assertFalse(habitat.has(tiger2));
	}
	
	
	@AfterClass
	public void tearDown()	{	
		zoo=null;
		tiger1=null;
		tiger2=null;
		tiger3=null;
		tiger4=null;
		animals=null;
		habitat=null;
	}	
}

