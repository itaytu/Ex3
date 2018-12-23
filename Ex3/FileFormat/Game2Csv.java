package FileFormat;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Game;
import GIS.Fruit;
import GIS.Pacman;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import Algorithms.toGIS_layer;

/**
 * Class that converts a game to a CSV file
 */
public class Game2Csv {

	// GIS_layer categoriesLayer;
	private GIS_layer pacmanLayer;
	private GIS_layer fruitLayer;

	private Game myGame;

	/**
	 * Constructs a conversation from a given Game. Then writes the file to the
	 * given target.
	 * 
	 * @param game
	 *            to convert to CSV
	 * @param output
	 *            target path
	 */
	public void game2CSV(Game p, String output) {
		ArrayList<String> content = new ArrayList<String>();

		String title = "Type& Id& Lat& Lon& Alt& Speed/Weight& Radius \n";
		content.add(title);

		this.pacmanLayer = new toGIS_layer(new HashSet<>());
		this.fruitLayer = new toGIS_layer(new HashSet<>());
		this.myGame = p;

		this.pacmanLayer = myGame.getPacmanLayer();
		this.fruitLayer = myGame.getFruitLayer();
		Iterator<GIS_element> fit = fruitLayer.iterator();
		Iterator<GIS_element> pit = pacmanLayer.iterator();

		try {
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);

			while (pit.hasNext()) {
				Pacman ptemp = (Pacman) pit.next();
				content.add(ptemp.getType() + "&" + ptemp.getID() + "&" + ptemp.getPoint().get_y() + "&"
						+ ptemp.getPoint().get_x() + "&" + ptemp.getPoint().get_z() + "&" + ptemp.getSpeed() + "&"
						+ ptemp.getEatRadius() + "\n");
			}

			while (fit.hasNext()) {
				Fruit ftemp = (Fruit) fit.next();
				content.add(ftemp.getType() + "&" + ftemp.getID() + "&" + ftemp.getPoint().get_y() + "&"
						+ ftemp.getPoint().get_x() + "&" + ftemp.getPoint().get_z() + "&" + ftemp.getFruitWeight()
						+ "\n");
			}

			String data = content.toString().replace("[", "").replace("]", "").replaceAll(" ", "").replaceAll(",", "")
					.replaceAll("&", ",");
			bw.write(data);
			bw.close();

			System.out.println("CSV file was saved succesfully in: " + output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
