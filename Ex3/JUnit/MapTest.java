package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import Algorithms.Map;
import Coords.MyCoords;

class MapTest {

	static Map map;
	static MyCoords coords;
	static double epsilon = 0.0001;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		int frameWidth = 1433, frameHeight = 642;

		BufferedImage img = ImageIO.read(new File("Ariel1.png"));

		map = new Map(img, frameWidth, frameHeight, 35.20238, 35.21236, 32.10190, 32.10569);

		coords = new MyCoords();
	}

	@Test
	public void distanceBetweenPixelsTest() {

		double distance = map.distanceBetweenPixels(0, 642, 1433, 0);

		// double distance = coords.distance3d(new Point3D(35.20238,32.10190), new
		// Point3D(35.21236,32.10569));

		double expectedMeters = 1161.926419574481;

		assertEquals(expectedMeters, distance, epsilon);
	}

	@Test
	public void angleBetweenPixelsTest() {
		// double angle = map.angleBetweenPixels(0, 642, 1433, 0);
		double angle = map.angleBetweenPixels(642, 0, 0, 0);

		double expected = 180;

		assertEquals(expected, angle, epsilon);
	}

}
