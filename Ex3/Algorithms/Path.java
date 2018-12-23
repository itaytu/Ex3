package Algorithms;

import java.util.ArrayList;

import GIS.Fruit;
import GIS.Pacman;

/**
 * Class that defines a path
 */
public class Path {

	private int fruitID;
	private int pacmanID;
	private double timeToFruit;
	private double currentTime;

	private ArrayList<pathObject> allPaths;
	private Pacman Pacman;
	private Fruit fruit;

	/**
	 * Constructor of path from pacman and his fruits
	 * 
	 * @param p
	 * @param Fruits
	 * @param currentTime
	 */
	public Path(Pacman p, ArrayList<Fruit> Fruits, double currentTime) {
		this.Pacman = p;
		this.currentTime = p.getTime();
		addAllPaths(Fruits, Pacman);
		int index = MinPathIndex(allPaths);
		this.fruitID = allPaths.get(index).getFruitID();
		this.pacmanID = allPaths.get(index).getPacmanID();
		this.timeToFruit = allPaths.get(index).getTimeToFruit();
	}

	/**
	 * Adds all paths to a pathObject
	 * 
	 * @param Fruits
	 * @param p
	 */
	private void addAllPaths(ArrayList<Fruit> Fruits, Pacman p) {
		allPaths = new ArrayList<>();
		for (int i = 0; i < Fruits.size(); i++) {
			Fruit fruitTmp = Fruits.get(i);
			double timeTmp = timeToEachFruit(p, fruitTmp);
			pathObject pathobject = new pathObject(fruitTmp.getID(), p.getID(), timeTmp, currentTime,
					fruitTmp.getFruitWeight());
			allPaths.add(pathobject);
		}
	}

	/**
	 * Takes all paths and returns the index of the shortest path
	 * 
	 * @param allPaths
	 * @return index of path
	 */
	private int MinPathIndex(ArrayList<pathObject> allPaths) {
		int index = 0;
		double minFirstElement = allPaths.get(0).getTimeToFruit();
		for (int i = 1; i < allPaths.size(); i++) {
			if (allPaths.get(i).getTimeToFruit() < minFirstElement) {
				minFirstElement = allPaths.get(i).getTimeToFruit();
				index = i;
			}
		}
		return index;
	}

	/**
	 * Checks the time to specific fruit from a given pacman
	 * 
	 * @param p
	 *            pacman
	 * @param f
	 *            fruit
	 * @return the time as a double
	 */
	private double timeToEachFruit(Pacman p, Fruit f) {
		double time = 0;
		double distance = p.getPacmanCoords().distance3d(p.getPoint(), f.getPoint()) - p.getEatRadius();
		if (distance == 0 || distance == -p.getEatRadius()) {
			return time;
		} else
			time = distance / p.getSpeed();
		return time;
	}

	public int getFruitID() {
		return this.fruitID;
	}

	public int getPacmanID() {
		return this.pacmanID;
	}

	public Pacman getPacman() {
		return this.Pacman;
	}

	public Fruit getFruit() {
		return this.fruit;
	}

	public double getTimeToFruit() {
		return this.timeToFruit;
	}

	public ArrayList<pathObject> getAllPaths() {
		return this.allPaths;
	}

	/**
	 * Gets a fruit by an id
	 * 
	 * @param numOfFruits
	 *            array list of fruits
	 * @param fruitID
	 *            to search for
	 * @return a fruit, null if not found
	 */
	public Fruit getThisFruit(ArrayList<Fruit> numOfFruits, int fruitID) {
		for (int i = 0; i < numOfFruits.size(); i++) {
			if (numOfFruits.get(i).getID() == fruitID)
				return numOfFruits.get(i);
		}
		return null;
	}

}
