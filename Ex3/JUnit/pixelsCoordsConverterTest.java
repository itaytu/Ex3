package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Algorithms.pixelsCoordsConverter;
import Geom.Point3D;

class pixelsCoordsConverterTest {

	static pixelsCoordsConverter converter;
	final static int maxFrameWidth = 1433;
	final static int maxFrameHeight = 642;
	final static double epsilon = 1;

	@BeforeEach
	void setUp() throws Exception {
		final JFrame frame = new JFrame();

		// Range of frame: x: 0 to 1433, y: 0 to 642
		frame.setSize(new Dimension(maxFrameWidth, maxFrameHeight));

		converter = new pixelsCoordsConverter(maxFrameWidth, maxFrameHeight, 35.20238, 35.21236, 32.10190, 32.10569);

	}

	@Test
	public void fromPixelsToCoords() {
		double expectedX = 35.20747794836008, expectedY = 32.102484439252336;

		int x = 732;
		int y = 543;

		Point3D res = converter.toCoords(x, y);

		double resX = res.get_x(), resY = res.get_y();

		System.out.print(resX + ", " + resY);
		assertEquals(expectedX, resX, epsilon);
		assertEquals(expectedY, resY, epsilon);
	}

	@Test
	public void fromCoordsToPixels() {
		double expectedX = 732, expectedY = 543;

		double x = 35.20747794836008;
		double y = 32.102484439252336;

		Point3D coordPoint = new Point3D(x, y, 0);

		int[] res = converter.gps2Pixels(coordPoint);

		double resX = res[0], resY = res[1];

		assertEquals(expectedX, resX, epsilon);
		assertEquals(expectedY, resY, epsilon);

	}

}
