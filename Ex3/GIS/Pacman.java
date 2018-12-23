package GIS;

import java.util.ArrayList;

import Coords.MyCoords;
import Geom.GeomElement;
import Geom.Geom_element;
import Geom.Point3D;

/**
 * Class that defines a pacman in the game, and it's properties
 */
public class Pacman implements GIS_element {

	private int ID;
	private char type;

	private double x, y, z;
	private double eatRadius;
	private double speed;
	private double time;

	private String[] pacmanAllData;
	private Point3D pacmanPoint;
	private MyCoords pacmanCoords;

	private static int pacmanCount = -1;

	private long timeStamp;

	private ArrayList<Fruit> fruitPath;
	private ArrayList<Double> totalPathTime;
	private ArrayList<Double> timePath;
	private ArrayList<Integer> fruitPoints;

	/**
	 * Constructs a Pacman from a CSV file
	 * 
	 * @param pacmanData
	 *            line in the CSV file
	 * @param ind_x
	 *            index of the x coordinate in the file
	 * @param ind_y
	 *            index of the y coordinate in the file
	 * @param ind_z
	 *            index of the z coordinate in the file
	 */
	public Pacman(String[] pacmanData, int ind_x, int ind_y, int ind_z) {
		pacmanCount++;
		this.timeStamp = 0;
		this.type = 'P';

		this.pacmanAllData = pacmanData;
		this.pacmanPoint = new Point3D(pacmanAllData, ind_x, ind_y, ind_z);
		this.ID = Integer.parseInt(pacmanAllData[1]);
		this.time = 0;

		this.x = this.pacmanPoint.get_x();
		this.y = this.pacmanPoint.get_y();
		this.z = this.pacmanPoint.get_z();

		this.setPacmanCoords(new MyCoords());
		this.fruitPath = new ArrayList<>();
		this.timePath = new ArrayList<>();
		this.totalPathTime = new ArrayList<>();
		this.setFruitPoints(new ArrayList<>());

		this.eatRadius = Double.parseDouble(pacmanAllData[6]);
		this.speed = Double.parseDouble(pacmanAllData[5]);
	}

	/**
	 * Constructs a Pacman from the GUI
	 * 
	 * @param x
	 *            coordinate
	 * @param y
	 *            coordinate
	 */
	public Pacman(double x, double y) {
		pacmanCount++;
		this.ID = pacmanCount;
		this.type = 'P';
		this.timeStamp = 0;

		this.x = x;
		this.y = y;
		this.z = 0;

		this.pacmanPoint = new Point3D(x, y, 0);
		this.time = 0;
		this.setPacmanCoords(new MyCoords());
		this.fruitPath = new ArrayList<>();
		this.timePath = new ArrayList<>();
		this.totalPathTime = new ArrayList<>();
		this.setFruitPoints(new ArrayList<>());

		this.eatRadius = 1;
		this.speed = 1;
	}

	/**
	 * Copy constructor
	 * 
	 * @param p
	 *            pacman to copy
	 */
	public Pacman(Pacman p) {
		this.timeStamp = 0;
		this.type = 'P';
		this.ID = p.ID;

		this.x = p.x;
		this.y = p.y;
		this.z = p.z;

		this.pacmanPoint = new Point3D(x, y, z);

		this.eatRadius = p.eatRadius;
		this.speed = p.speed;
		this.time = p.time;

		this.pacmanCoords = new MyCoords();

		this.fruitPath = new ArrayList<>(p.fruitPath);
		this.fruitPoints = new ArrayList<>(p.fruitPoints);
		this.timePath = new ArrayList<>(p.timePath);
		this.totalPathTime = new ArrayList<>(p.totalPathTime);

	}

	@Override
	public Geom_element getGeom() {
		GeomElement pacmanGeom = new GeomElement(pacmanPoint);
		return pacmanGeom;
	}

	@Override
	public Meta_data getData() {
		if (pacmanAllData != null) {
			pacmanMetaData pacmanData = new pacmanMetaData(pacmanAllData);
			return pacmanData;
		} else
			return null;
	}

	@Override
	public void translate(Point3D vec) {
		this.pacmanPoint = getPacmanCoords().add(this.pacmanPoint, vec);
	}

	public double getEatRadius() {
		return eatRadius;
	}

	public double getSpeed() {
		return speed;
	}

	public int getID() {
		return ID;
	}

	public Point3D getPoint() {
		return pacmanPoint;
	}

	/**
	 * Sets the current geographical point of the pacman
	 * 
	 * @param p
	 */
	public void setPoint(Point3D p) {
		this.x = p.get_x();
		this.y = p.get_y();
		this.z = p.get_z();
		this.pacmanPoint = new Point3D(x, y, z);
	}

	public String[] getAllData() {
		return pacmanAllData;
	}

	/**
	 * Sets the time that currently took the pacman to eat the fruits
	 * 
	 * @param time
	 */
	public void setTime(double time) {
		this.time = time;
	}

	public double getTime() {
		return this.time;
	}

	public MyCoords getPacmanCoords() {
		return pacmanCoords;
	}

	/**
	 * Sets the current gps point of the pacman
	 * 
	 * @param p
	 *            current point
	 */
	public void setPacmanCoords(MyCoords pacmanCoords) {
		this.pacmanCoords = pacmanCoords;
	}

	/**
	 * Adds the time of pacman to the total time path
	 * 
	 * @param time
	 */
	public void addTimeToTotalPath(double time) {
		this.totalPathTime.add(time);
	}

	public ArrayList<Double> getTotalTimePath() {
		return this.totalPathTime;
	}

	/**
	 * Adds the time of pacman to the time path
	 * 
	 * @param time
	 */
	public void addTimeToPath(double time) {
		this.timePath.add(time);
	}

	public ArrayList<Double> getTimePath() {
		return this.timePath;
	}

	public void setFruitPoints(ArrayList<Integer> fruitPoints) {
		this.fruitPoints = fruitPoints;
	}

	public ArrayList<Integer> getFruitPoints() {
		return fruitPoints;
	}

	/**
	 * Add a fruit value to the current sum of the fruits that eaten by the pacman
	 * 
	 * @param point
	 *            value of the eaten fruit
	 */
	public void addToFruitPoints(int point) {
		if (fruitPoints.size() < 2) {
			if (fruitPoints.isEmpty())
				this.fruitPoints.add(point);
			else
				this.fruitPoints.add(point + fruitPoints.get(0));
		} else {
			int index = fruitPoints.size();
			int sum = point + calculatePointsATM(index);
			this.fruitPoints.add(sum);
		}
	}

	/**
	 * Calculate the sum of point values that the pacman ate until a given index
	 * 
	 * @param index
	 *            until to calculate
	 * @return the sum
	 */
	public int calculatePointsATM(int index) {
		int sum = 0;
		sum += fruitPoints.get(index - 1);
		return sum;
	}

	/**
	 * Sets the pacman fruit path
	 * 
	 * @param fruitPath
	 *            list of fruits
	 */
	public void setFruitPath(ArrayList<Fruit> fruitPath) {
		this.fruitPath = fruitPath;
	}

	public void addToFruitPath(Fruit f) {
		this.fruitPath.add(f);
	}

	public ArrayList<Fruit> getFruitPath() {
		return fruitPath;
	}

	public char getType() {
		return this.type;
	}

	public void setTimeStamp(long timestamp) {
		this.timeStamp = timestamp;
	}

	public long getTimeStamp() {
		return this.timeStamp;
	}

	public static int getPacmanCount() {
		return pacmanCount;
	}

	public static void setPacmanCount(int pacmanCount) {
		Pacman.pacmanCount = pacmanCount;
	}

	/**
	 * Resets pacman's data to re-run the game demonstration
	 */
	public void resetData() {
		this.fruitPath = new ArrayList<>();
		this.fruitPoints = new ArrayList<>();
		this.timePath = new ArrayList<>();
		this.totalPathTime = new ArrayList<>();
		this.time = 0;
		this.timeStamp = 0;
	}

}
