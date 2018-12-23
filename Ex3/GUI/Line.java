package GUI;

import java.awt.*;

import Algorithms.pixelsCoordsConverter;
import Geom.Point3D;

/**
 * Class that defines a simple line
 */
class Line {
	private final Color color;

	private Point3D from, to;
	private pixelsCoordsConverter converter;

	/**
	 * New line is constructed using a 1) converter, that wil help to convert the
	 * pixels to related coordinates, 2) 2 points in pixels 3) Color of the line
	 *
	 * @param converter
	 *            will convert from pixel to coords
	 * @param x1
	 *            pixel
	 * @param y1
	 *            pixel
	 * @param x2
	 *            pixel
	 * @param y2
	 *            pixel
	 * @param color
	 *            of line
	 */
	Line(pixelsCoordsConverter converter, int x1, int y1, int x2, int y2, Color color) {
		this.color = color;

		this.converter = converter;

		this.from = new Point3D(this.converter.toCoords(x1, y1));
		this.to = new Point3D(this.converter.toCoords(x2, y2));

	}

	Color getColor() {
		return color;
	}

	public Point3D getFrom() {
		return this.from;
	}

	public Point3D getTo() {
		return this.to;
	}
}