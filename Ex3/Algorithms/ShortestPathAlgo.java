package Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import GIS.GIS_element;
import GIS.Game;
import GIS.Fruit;
import GIS.Pacman;
import Geom.Point3D;
import Utils.MyComparator;

/**
 * Algorythm class that defines the shortest path from a pacman to a set of
 * fruits
 */
public class ShortestPathAlgo {

	private ArrayList<Path> rearrangedPath;
	private ArrayList<Path> pathPerPacman;
	private ArrayList<Path> tempPathPerPacman;

	private ArrayList<Pacman> OriginalPacmans;
	private ArrayList<Pacman> copyPacmans;
	private ArrayList<Fruit> copyFruits;

	private Game game;
	private double currentTime;

	public ShortestPathAlgo(Game game) {
		currentTime = 0;
		this.setGame(game);
		createCopy(game);
	}

	/**
	 * Creates a copy from exisiting Game object
	 * 
	 * @param game
	 */
	public void createCopy(Game game) {

		OriginalPacmans = new ArrayList<>();
		copyFruits = new ArrayList<>();
		copyPacmans = new ArrayList<>();

		Iterator<GIS_element> fruitIT = game.getFruitLayer().iterator();
		while (fruitIT.hasNext()) {
			Fruit tmpF = (Fruit) fruitIT.next();
			Fruit copyF = new Fruit(tmpF);
			copyFruits.add(copyF);
		}

		Iterator<GIS_element> pacmanIT = game.getPacmanLayer().iterator();
		while (pacmanIT.hasNext()) {
			Pacman tmpP = (Pacman) pacmanIT.next();
			Pacman copyP = new Pacman(tmpP);
			copyPacmans.add(copyP);
			OriginalPacmans.add(tmpP);
		}

	}

	/**
	 * Initialize algorythm methods
	 */
	public void INIT() {

		// while we still have fruits
		while (!copyFruits.isEmpty()) {

			// calculate for each pacman the shortest time for a given fruit
			calculatePaths();

			// insert all the paths for the pacmans into the new Array sorted by minimum
			// time
			RearrangeMin(pathPerPacman);

			// calculate the next step for each pacman
			calculateNextStep();

			// initiate the next step for each pacman
			initiateNextStep();

			// delete all fruits that were eaten
			removeFruits(tempPathPerPacman);
		}

	}

	/**
	 * Calculate for each pacman the shortest time for a given fruit
	 */
	private void calculatePaths() {
		pathPerPacman = new ArrayList<>();

		for (int i = 0; i < OriginalPacmans.size(); i++) {
			currentTime = copyPacmans.get(i).getTime();
			Path pacmanPath = new Path(copyPacmans.get(i), copyFruits, currentTime);
			pathPerPacman.add(pacmanPath);
		}
	}

	/**
	 * Insert all the paths for the pacmans into the new Array sorted by minimum
	 * time
	 * 
	 * @param pathPerpacman
	 */
	private void RearrangeMin(ArrayList<Path> pathPerpacman) {
		rearrangedPath = new ArrayList<>();
		for (int i = 0; i < pathPerpacman.size(); i++) {
			rearrangedPath.add(pathPerpacman.get(i));
		}

		Collections.sort(rearrangedPath, new MyComparator());
	}

	/**
	 * calculate the next step for each pacman
	 */
	private void calculateNextStep() {
		// temporary list to hold the pacmans with their next move
		tempPathPerPacman = new ArrayList<>();

		// check that there are no pacmans with a move to the same fruit
		for (int j = 0; j < rearrangedPath.size(); j++) {
			Path QuickestPath = rearrangedPath.get(j);

			if (!isIn(tempPathPerPacman, QuickestPath.getFruitID())) {
				tempPathPerPacman.add(QuickestPath);
			} else
				removePacmans(tempPathPerPacman);
		}
	}

	/**
	 * Initiate the next step for each pacman
	 */
	private void initiateNextStep() {
		for (int i = 0; i < tempPathPerPacman.size(); i++) {
			Pacman Ptmp = getThisPacman(tempPathPerPacman.get(i).getPacmanID());
			Pacman OriginalP = getOriginalPacman(tempPathPerPacman.get(i).getPacmanID());
			Fruit Ftmp = new Fruit(getThisFruit(tempPathPerPacman.get(i).getFruitID()));

			OriginalP.addToFruitPath(Ftmp);
			OriginalP.addToFruitPoints(Ftmp.getFruitWeight());

			Ptmp.addToFruitPath(Ftmp);
			Ptmp.addToFruitPoints(Ftmp.getFruitWeight());

			Point3D vector = Ptmp.getPacmanCoords().vector3D(Ptmp.getPoint(), Ftmp.getPoint());
			Point3D tmp = Ptmp.getPacmanCoords().add(Ptmp.getPoint(), vector);
			Ptmp.setPoint(tmp);
			// Ftmp.setIsEaten(true);
			double time = tempPathPerPacman.get(i).getTimeToFruit();
			Ptmp.setTime(tempPathPerPacman.get(i).getTimeToFruit() + Ptmp.getTime());
			Ftmp.setTime(tempPathPerPacman.get(i).getTimeToFruit());

			OriginalP.addTimeToTotalPath(Ptmp.getTime());
			OriginalP.addTimeToPath(time);
			Ptmp.addTimeToTotalPath(Ptmp.getTime());
		}
	}

	/**
	 * Delete all fruits that were eaten
	 * 
	 * @param nextStep
	 */
	private void removeFruits(ArrayList<Path> nextStep) {
		for (int i = 0; i < nextStep.size(); i++) {
			int id = nextStep.get(i).getFruitID();
			for (int j = 0; j < copyFruits.size(); j++) {
				if (copyFruits.get(j).getID() == id) {
					copyFruits.remove(j);
					j--;
				}
			}
		}
	}

	/**
	 * Checks if specified fruit is in the path
	 * 
	 * @param tmpPacmanPath
	 *            path
	 * @param fruitID
	 * @return true if is in, false if not
	 */
	private boolean isIn(ArrayList<Path> tmpPacmanPath, int fruitID) {
		for (int i = 0; i < tmpPacmanPath.size(); i++) {
			if (tmpPacmanPath.get(i).getFruitID() == fruitID)
				return true;
		}
		return false;
	}

	/**
	 * Gets a pacman by ID
	 * 
	 * @param pacmanID
	 * @return null if pacman not found
	 */
	private Pacman getThisPacman(int pacmanID) {
		for (int i = 0; i < copyPacmans.size(); i++) {
			if (copyPacmans.get(i).getID() == pacmanID)
				return copyPacmans.get(i);
		}
		return null;
	}

	/**
	 * Gets the original pacman from an ID
	 * 
	 * @param pacmanID
	 * @return original pacman, null if not found
	 */
	private Pacman getOriginalPacman(int pacmanID) {
		for (int i = 0; i < OriginalPacmans.size(); i++) {
			if (OriginalPacmans.get(i).getID() == pacmanID)
				return OriginalPacmans.get(i);
		}
		return null;
	}

	/**
	 * Gets a fruit from an ID
	 * 
	 * @param fruitID
	 * @return fruit, null if not found
	 */
	private Fruit getThisFruit(int fruitID) {
		for (int i = 0; i < copyFruits.size(); i++) {
			if (copyFruits.get(i).getID() == fruitID)
				return copyFruits.get(i);
		}
		return null;
	}

	/**
	 * Remove the pacmans that gets to the same fruit in the next step list
	 * 
	 * @param nextStep
	 *            list of the pacmans that go to a specific fruit
	 */
	private void removePacmans(ArrayList<Path> nextStep) {
		for (int i = 0; i < nextStep.size(); i++) {
			int id = nextStep.get(i).getPacmanID();
			for (int j = 0; j < rearrangedPath.size(); j++) {
				if (rearrangedPath.get(j).getPacmanID() == id)
					rearrangedPath.remove(j);
			}
		}
	}

	public ArrayList<Pacman> getCopyPacmans() {
		return this.copyPacmans;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
