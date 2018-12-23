package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Algorithms.pathObject;

class pathObjectTest {

	private int fruitID = 3;
	private int pacmanID = 0;
	private double timeToFruit = 9.0;
	private double currentTime = 0;
	private int fruitWeight = 1;

	pathObject pathobject;

	@BeforeEach
	void setUp() throws Exception {
		pathobject = new pathObject(fruitID, pacmanID, timeToFruit, currentTime, fruitWeight);
	}

	@Test
	void testGetFruitID() {
		assertEquals(fruitID, pathobject.getFruitID(), "Function is wrong");
	}

	@Test
	void testGetPacmanID() {
		assertEquals(pacmanID, pathobject.getPacmanID(), "Function is wrong");
	}

	@Test
	void testGetTimeToFruit() {
		assertEquals(timeToFruit, pathobject.getTimeToFruit(), "Function is wrong");
	}

	@Test
	void testGetFruitWeight() {
		assertEquals(fruitWeight, pathobject.getFruitWeight(), "Function is wrong");
	}

}
