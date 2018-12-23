package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Algorithms.Range;

class RangeTest {

	static Range range;
	static double epsilon = Double.MIN_VALUE;

	@BeforeEach
	void setUp() throws Exception {
		range = new Range(100, 200);
	}

	@Test
	public void relation() {
		double expectedRelation = 0.5;

		double res = range.relation(150);

		assertEquals(expectedRelation, res, epsilon);
	}

	@Test
	public void distance() {
		double expectedDistance = 100;

		double res = range.distance();

		assertEquals(expectedDistance, res, epsilon);
	}

	@Test
	public void valueFromRelation() {
		double expectedValue = 180;

		double relation = 0.8;

		double res = range.getValueFromRelation(relation);

		assertEquals(expectedValue, res, epsilon);
	}

	@Test
	public void isIn() {
		boolean notIn = range.isIn(300);
		boolean notIn_2 = range.isIn(50);
		boolean in = range.isIn(120);

		assertFalse(notIn);
		assertFalse(notIn_2);
		assertTrue(in);
	}
}
