package GIS;

import Geom.Geom_element;
import Geom.Point3D;

/**
 * Class that defines a fruit in the game and it's properties
 */
public class Fruit implements GIS_element {

	private char type;
	private double x, y, z;
	private double time;

	private String[] fruitAllData;
	private Point3D fruitPoint;
	private int ID;
	private Boolean isEaten;
	int fruitWeight;

	private static int fruitCount = -1;
	private long timeStamp;

	/**
	 * Constructs a Fruit from a CSV file
	 * 
	 * @param fruitData
	 *            a line in a CSV
	 * @param ind_x
	 *            index of the x coordinate in the file
	 * @param ind_y
	 *            index of the y coordinate in the file
	 * @param ind_z
	 *            index of the z coordinate in the file
	 */
	public Fruit(String[] fruitData, int ind_x, int ind_y, int ind_z) {
		fruitCount++;
		this.type = 'F';

		this.fruitAllData = fruitData;
		this.fruitPoint = new Point3D(fruitAllData, ind_x, ind_y, ind_z);
		this.timeStamp = 0;

		this.x = this.fruitPoint.get_x();
		this.y = this.fruitPoint.get_y();
		this.z = this.fruitPoint.get_z();

		this.time = 0;
		this.ID = Integer.parseInt(fruitData[1]);
		this.isEaten = false;
		this.fruitWeight = Integer.parseInt(fruitData[5]);
	}

	/**
	 * Constructs a Fruit from the GUI, in 2 given pixels
	 * 
	 * @param x
	 *            coordinate
	 * @param y
	 *            coordinate
	 */
	public Fruit(double x, double y) {
		fruitCount++;
		this.type = 'F';
		this.timeStamp = 0;

		this.ID = fruitCount;

		this.x = x;
		this.y = y;
		this.z = 0;

		this.fruitPoint = new Point3D(x, y, 0);
		this.isEaten = false;

		this.fruitWeight = 1;
		this.time = 0;
	}

	/**
	 * Copy constructor
	 * 
	 * @param f
	 *            fruit object to copy
	 */
	public Fruit(Fruit f) {
		this.type = 'F';
		this.ID = f.ID;
		this.timeStamp = 0;

		this.x = f.x;
		this.y = f.y;
		this.z = f.z;

		this.fruitPoint = new Point3D(x, y, z);

		this.fruitAllData = f.fruitAllData;
		this.fruitWeight = f.fruitWeight;

		this.isEaten = f.isEaten;
		this.time = f.time;
	}

	@Override
	public Geom_element getGeom() {
		return null;
	}

	@Override
	public Meta_data getData() {
		fruitMetaData fruitData = new fruitMetaData(fruitAllData);
		return fruitData;
	}

	/**
	 * Fruit can't move hence translate function is empty
	 * 
	 * @param vec
	 */
	@Override
	public void translate(Point3D vec) {
	}

	public char getType() {
		return type;
	}

	public int getID() {
		return ID;
	}

	public Point3D getPoint() {
		return fruitPoint;
	}

	public String[] getAllData() {
		return fruitAllData;
	}

	public Boolean getIsEaten() {
		return isEaten;
	}

	public void setIsEaten(Boolean eatOrNot) {
		this.isEaten = eatOrNot;
	}

	public int getFruitWeight() {
		return fruitWeight;
	}

	public void setFruitWeight(int fruitWeight) {
		this.fruitWeight = fruitWeight;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public double getTime() {
		return this.time;
	}

	public void setTimeStamp(long timestamp) {
		this.timeStamp = timestamp;
	}

	public long getTimeStamp() {
		return this.timeStamp;
	}

	public static int getFruitCount() {
		return fruitCount;
	}

	public static void setFruitCount(int fruitCount) {
		Fruit.fruitCount = fruitCount;
	}

	/**
	 * Resets fruit data to re-run the game
	 */
	public void resetData() {
		this.time = 0;
		this.timeStamp = 0;
	}
}
