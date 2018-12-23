package GIS;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import Algorithms.toGIS_element;
import Algorithms.toGIS_layer;
import Algorithms.toGIS_project;
import FileFormat.Game2Csv;
import FileFormat.path2KML;
import File_format.CSVreader;
import File_format.Csv2Kml;

/**
 * Class that defines a game. The game consists layers of fruits, pacmans and
 * categories;
 */
public class Game extends Csv2Kml {

	private ArrayList<String[]> myData;

	private GIS_layer categoriesLayer;
	private GIS_layer fruitsLayer;
	private GIS_layer pacmansLayer;

	private GIS_project myProject;

	private String[] categories;
	private File CSVgame;

	/**
	 * Constructs a game from a given path that points to a CSV file
	 * 
	 * @param path
	 *            from
	 */
	public Game(String path) {

		categoriesLayer = new toGIS_layer(new HashSet<>());
		pacmansLayer = new toGIS_layer(new HashSet<>());
		fruitsLayer = new toGIS_layer(new HashSet<>());

		myProject = new toGIS_project(new HashSet<>());

		myData = CSVreader.reader(path, 0);
		categories = CSVreader.getElements();
		GIS_element Category = new toGIS_element(categories);
		categoriesLayer.add(Category);

		for (int i = 0; i < myData.size(); i++) {
			String[] tmp = myData.get(i);

			if (tmp[0].equals("P") || tmp[0].equals("p")) {
				Pacman newPacman = new Pacman(tmp, 3, 2, 4);
				pacmansLayer.add(newPacman);
			}

			else if (tmp[0].equals("F") || tmp[0].equals("f")) {
				Fruit newFruit = new Fruit(tmp, 3, 2, 4);
				fruitsLayer.add(newFruit);
			}
		}

		myProject.add(categoriesLayer);
		myProject.add(pacmansLayer);
		myProject.add(fruitsLayer);
	}

	/**
	 * Constructs a game from a GIS_Layer that defines a game project
	 * 
	 * @param gameProject
	 */
	/*
	 * public Game(ArrayList<GIS_layer> gameProject) { pacmansLayer = new
	 * toGIS_layer(new HashSet<>()); fruitsLayer = new toGIS_layer(new HashSet<>());
	 * 
	 * myProject = new toGIS_project(new HashSet<>());
	 * 
	 * this.pacmansLayer = gameProject.get(0); this.fruitsLayer =
	 * gameProject.get(1);
	 * 
	 * myProject.add(pacmansLayer); myProject.add(fruitsLayer);
	 * 
	 * }
	 */

	/**
	 * Construct a new empty game for the first run of the GUI
	 */
	public Game() {
		pacmansLayer = new toGIS_layer(new HashSet<>());
		fruitsLayer = new toGIS_layer(new HashSet<>());

		myProject = new toGIS_project(new HashSet<>());

		myProject.add(pacmansLayer);
		myProject.add(fruitsLayer);

	}

	public void createGame() {

	}

	public GIS_project getGameProject() {
		return myProject;
	}

	public GIS_layer getPacmanLayer() {
		return pacmansLayer;
	}

	public GIS_layer getFruitLayer() {
		return fruitsLayer;
	}

	public GIS_layer getCategoriesLayer() {
		return categoriesLayer;
	}

	public String[] getCategories() {
		return categories;
	}

	/**
	 * Saves a Game to a CSV on a given path
	 * 
	 * @param game
	 *            to save
	 * @param savePath
	 *            target
	 */
	public void createCSVgame(String savePath) {
		Game2Csv createCSVgame = new Game2Csv();
		createCSVgame.game2CSV(this, savePath);
	}

	/**
	 * Saves a Game to a KML on a given path
	 * 
	 * @param game
	 *            to save
	 * @param savePath
	 *            target
	 */
	public void createKMLgame(String savePath) {
		path2KML createKMLgame = new path2KML();
		createKMLgame.game2kml(this, savePath);
	}

	public File getCSVgame() {
		return CSVgame;
	}

	/**
	 * Sets the file of the CSV
	 * 
	 * @param cSVgame
	 */
	public void setCSVgame(File cSVgame) {
		CSVgame = cSVgame;
	}

	/**
	 * Adds a pacman to the pacmans layer
	 * 
	 * @param p
	 *            pacman to add
	 */
	public void addPacman(Pacman p) {
		this.pacmansLayer.add(p);
	}

	/**
	 * Adds a fruit to the fruits layer
	 * 
	 * @param f
	 *            fruit to add
	 */
	public void addFruit(Fruit f) {
		this.fruitsLayer.add(f);
	}
}
