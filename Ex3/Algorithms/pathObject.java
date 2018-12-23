package Algorithms;

/**
 * Class that defines the next fruit in the pacman path
 */
public class pathObject {

	private int fruitID;
	private int pacmanID;
	private double timeToFruit;
	private double currentTime;
	private int fruitWeight;

	/**
	 * Constructs the next fruit in the path
	 * 
	 * @param fID
	 *            Fruit ID
	 * @param pID
	 *            Pacman ID
	 * @param timeToFruit
	 *            to get to the next fruit
	 * @param currentTime
	 *            of pacman in the path
	 * @param fruitWeight
	 *            value
	 */
	public pathObject(int fID, int pID, double timeToFruit, double currentTime, int fruitWeight) {
		this.fruitID = fID;
		this.pacmanID = pID;
		this.timeToFruit = timeToFruit;
		this.currentTime = currentTime;
		this.fruitWeight = fruitWeight;
	}

	public int getFruitID() {
		return fruitID;
	}

	public int getPacmanID() {
		return pacmanID;
	}

	public double getTimeToFruit() {
		return timeToFruit;
	}

	public void setTimeToFruit(double timeToFruit) {
		this.timeToFruit = timeToFruit;
	}

	public double getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(double currentTime) {
		this.currentTime = currentTime;
	}

	public int getFruitWeight() {
		return fruitWeight;
	}

	public void setFruitWeight(int fruitWeight) {
		this.fruitWeight = fruitWeight;
	}

}
