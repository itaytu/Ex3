package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Coords.MyCoords;
import Geom.Point3D;
/**
 * This class represents a simple JUnit test for MyCoords. the tests included are:
 * Add
 * Distance3D 
 * Vector3D	
 * Azimuth_elevation_dist
 * IsValid_GPS_Point
 * 
 * @author Itayt and Sagi Oshri
 *
 */
class MyCoordsTest {

	Point3D p1, p2, v;
	MyCoords mc = new MyCoords();
	double eps=0.001;

	@BeforeEach
	void setUp() throws Exception {
		p1 = new Point3D(32.103315, 35.209039, 670);
		p2 = new Point3D(32.106352, 35.205225, 650);
		v = new Point3D(337.6989921, -359.2492069, -20);
	}

	@Test
	void testAdd() {
		Point3D actual;
		actual = mc.add(p1, v);
		assertEquals(p2.x(), actual.x(),eps,"The Point Latitude is wrong");
		assertEquals(p2.y(), actual.y(),eps,"The Point longtidude is wrong");
		assertEquals(p2.z(), actual.z(),eps,"The Point atitude is wrong");
	}

	@Test
	void testDistance3d() {
		double distanceExpected = 493.0523318;
		double distanceActual = mc.distance3d(p1, p2);
		assertEquals(distanceExpected, distanceActual, eps, "The distance calculated is wrong" );
	}

	@Test
	void testVector3D() {
		Point3D actual;
		actual = mc.vector3D(p1, p2);
		assertEquals(v.x(), actual.x(),eps,"The Vector Latitude is wrong");
		assertEquals(v.y(), actual.y(),eps,"The Vector longtidude is wrong");
		assertEquals(v.z(), actual.z(),eps,"The Vector atitude is wrong");	
	}

	@Test
	void testAzimuth_elevation_dist() {
		double [] aziExpected= {313.2304, -2.3247, 493.0523};
		double [] aziActual = mc.azimuth_elevation_dist(p1, p2);
		assertEquals(aziExpected[0],aziActual[0],eps, "The Azimuth is wrong");
		assertEquals(aziExpected[1],aziActual[1],eps,"The Elevation is wrong");
		assertEquals(aziExpected[2],aziActual[2],eps,"The Distance is wrong");
	}

	@Test
	void testIsValid_GPS_Point() {
		Point3D invalidP = new Point3D(190, 30, 200);		
		assertEquals(false, mc.isValid_GPS_Point(invalidP),"The function for Valid GPS Point is wrong");
		assertEquals(true, mc.isValid_GPS_Point(p1),"The function for Valid GPS Point is wrong");
	}
}
