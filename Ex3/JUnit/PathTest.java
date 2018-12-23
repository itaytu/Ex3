package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Algorithms.Path;
import GIS.Fruit;
import GIS.Pacman;

class PathTest {

	String[] dataP = { "P", "0", "32.1045513", "35.2035022", "10", "1", "1" };

	String[] dataF0 = { "F", "0", "32.10462702", "35.20573393", "10", "1" };
	String[] dataF1 = { "F", "1", "32.10478793", "35.20498036", "0", "1" };
	String[] dataF2 = { "F", "2", "32.10458916", "35.20411086", "10", "1" };
	String[] dataF3 = { "F", "3", "32.1045513", "35.2035022", "0", "1" };

	Path path;
	ArrayList<Fruit> fruits;
	Pacman p;
	Fruit f0, f1, f2, f3;

	@BeforeEach
	void setUp() throws Exception {
		fruits = new ArrayList<>();

		p = new Pacman(dataP, 3, 2, 4);

		f0 = new Fruit(dataF0, 3, 2, 4);
		f1 = new Fruit(dataF1, 3, 2, 4);
		f2 = new Fruit(dataF2, 3, 2, 4);
		f3 = new Fruit(dataF3, 3, 2, 4);

		fruits.add(f0);
		fruits.add(f1);
		fruits.add(f2);
		fruits.add(f3);

		path = new Path(p, fruits, p.getTime());
	}

	@Test
	void testGetFruitID() {
		assertEquals(f3.getID(), path.getFruitID(), "Function is wrong");
	}

	@Test
	void testGetPacmanID() {
		assertEquals(p.getID(), path.getPacmanID(), "Function is wrong");
	}

	@Test
	void testGetTimeToFruit() {
		assertEquals(9.0, path.getTimeToFruit(), "Function is wrong");
	}

	@Test
	void testGetAllPaths() {
		assertEquals(4, path.getAllPaths().size(), "Function is wrong");
	}

}
