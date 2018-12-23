package FileFormat;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import Algorithms.toGIS_layer;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Game;
import GIS.Pacman;

/**
 * Class that converts a game to a KML file, that can be showed in Google Earth
 */
public class path2KML {
	private GIS_layer pacmanLayer;
	private GIS_layer fruitLayer;

	private Game myGame;

	private String StringTStampPacman;
	private String StringTStampFruit;

	private long PacmanTimeStamp;
	private long FruitTimeStamp;

	final Long GMT = (long) (((120 * 60)) * 1000);

	/**
	 * Constructs a conversation from a given Game. Then writes the file to the
	 * given target.
	 * 
	 * @param game
	 *            to convert to kml
	 * @param outputAddress
	 *            target path
	 */
	public void game2kml(Game game, String outputAddress) {
		ArrayList<String> content = new ArrayList<String>();

		this.pacmanLayer = new toGIS_layer(new HashSet<>());
		this.fruitLayer = new toGIS_layer(new HashSet<>());

		this.myGame = game;
		this.pacmanLayer = myGame.getPacmanLayer();
		this.fruitLayer = myGame.getFruitLayer();
		this.PacmanTimeStamp = System.currentTimeMillis() - GMT;

		Iterator<GIS_element> pacmanIT = pacmanLayer.iterator();
		fruitLayer.iterator();

		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n";
		String kmlDocumentStart = "<Document>";
		String kmlDocumentEnd = "</Document>";
		String kmlend = "</kml>";
		// String kmlFolderStart = "<Folder>\n"; String kmlFolderEnd = "</Folder>";
		// String kmlFolderName = "<name>"; String kmlFolderNameClose = "</name>\n";
		content.add(kmlstart);
		content.add(kmlDocumentStart);
		try {
			FileWriter fw = new FileWriter(outputAddress);
			BufferedWriter bw = new BufferedWriter(fw);
			// content.add(kmlFolderStart);
			// content.add(kmlFolderName);
			// content.add("Pacmans");
			// content.add(kmlFolderNameClose);
			while (pacmanIT.hasNext()) {
				StringTStampPacman = createTimeStamp(PacmanTimeStamp);
				Pacman tmpP = (Pacman) pacmanIT.next();
				String kmlPacman = "<Placemark>\n" + "<TimeStamp>\n" + "<when>" + StringTStampPacman + "</when>\n"
						+ "</TimeStamp>\n" + "<Style id=" + "\"Pacman\"" + ">" + "<IconStyle>" + " <Icon>"
						+ "<href>http://www.clker.com/cliparts/2/7/3/a/1225215149389018376mbtwms_Pacman.svg.med.png</href>"
						+ "</Icon>" + " </IconStyle>" + "</Style>" + "<name>" + "ID: " + tmpP.getID() + "</name>\n"
						+ "<description>\n" + "Eating Radius: " + tmpP.getEatRadius() + "\n" + "Speed: "
						+ tmpP.getSpeed() + "\n" + "</description>\n" + "<Point>\n" + "<coordinates>"
						+ tmpP.getPoint().get_x() + "," + tmpP.getPoint().get_y() + "," + tmpP.getPoint().get_z()
						+ "</coordinates>\n" + "</Point>\n" + "</Placemark>\n";

				content.add(kmlPacman);

				for (int i = 0; i < tmpP.getFruitPath().size(); i++) {
					Fruit tmpF = tmpP.getFruitPath().get(i);
					FruitTimeStamp = Math.round(tmpP.getTimePath().get(i));
					long newTimeStamp = PacmanTimeStamp + TimeUnit.SECONDS.toMillis(FruitTimeStamp);
					StringTStampFruit = createTimeStamp(newTimeStamp);
					String pacmanMoved = "<Placemark>\n" + "<TimeStamp>\n" + "<when>" + StringTStampFruit + "</when>\n"
							+ "</TimeStamp>\n" + "<Style id=" + "\"Pacman\"" + ">" + "<IconStyle>" + " <Icon>"
							+ "<href>http://www.clker.com/cliparts/7/y/w/c/T/e/red-pacman-md.png</href>" + "</Icon>"
							+ " </IconStyle>" + "</Style>" + "<name>" + "ID: " + tmpP.getID() + "</name>\n"
							+ "<description>\n" + "Eating Radius: " + tmpP.getEatRadius() + "\n" + "Speed: "
							+ tmpP.getSpeed() + "\n" + "Points for Pacman until this fruit: "
							+ tmpP.getFruitPoints().get(i) + "\n" + "</description>\n" + "<Point>\n" + "<coordinates>"
							+ tmpF.getPoint().get_x() + "," + tmpF.getPoint().get_y() + "," + tmpF.getPoint().get_z()
							+ "</coordinates>\n" + "</Point>\n" + "</Placemark>\n";

					String kmlFruit = "<Placemark>\n" + "<TimeStamp>\n" + "<when>" + StringTStampPacman + "</when>\n"
							+ "</TimeStamp>\n" + "<Style id=" + "\"cherry\"" + ">" + "<IconStyle>" +
							/* "<color>0000000</color>" + */
							"<Icon>"
							+ "<href>http://www.clker.com/cliparts/6/e/8/4/1206561522317245030Rocket000_fruit-cherries.svg.med.png</href>"
							+ "</Icon>" + " </IconStyle>" + "</Style>" + "<name>" + "ID: " + tmpF.getID() + "</name>\n"
							+ "<description>\n" + "Fruit Weight: " + tmpF.getFruitWeight() + "\n" + "</description>\n"
							+ "<Point>\n" + "<coordinates>" + tmpF.getPoint().get_x() + "," + tmpF.getPoint().get_y()
							+ "," + tmpF.getPoint().get_z() + "</coordinates>\n" + "</Point>\n" + "</Placemark>\n";
					content.add(kmlFruit);
					content.add(pacmanMoved);
				}
			}
			// content.add(kmlFolderEnd);
			// content.add(kmlFolderStart);
			// content.add(kmlFolderName);
			// content.add("Fruits");
			// content.add(kmlFolderNameClose);
			// content.add(kmlFolderEnd);
			pacmanIT = pacmanLayer.iterator();
			while (pacmanIT.hasNext()) {
				Pacman tmp = (Pacman) pacmanIT.next();
				String line = createPathLines(tmp);
				content.add(line);
			}
			content.add(kmlDocumentEnd);
			content.add(kmlend);
			for (int i = 0; i < content.size(); i++) {
				bw.write(content.get(i));
			}
			bw.close();
			System.out.println("KML file was saved succesfully in: " + outputAddress);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates String format from a long format of a timestamp
	 * 
	 * @param timestamp
	 *            in long
	 * @return timestamp as a String
	 */
	private String createTimeStamp(long timestamp) {
		Date date = new Date(timestamp);
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		timeStamp = timeStamp.replace(" ", "T");
		timeStamp += "Z";
		return timeStamp;
	}

	/**
	 * Creates the lines on the KML, via the pacman fruits path
	 * 
	 * @param p
	 *            pacman
	 * @return String as a KML format with path lines
	 */
	private String createPathLines(Pacman p) {
		String s = "";
		s += "<name>" + "Paths" + "</name>" + "<Style id=\"yellowLineGreenPoly\">" + "<LineStyle>"
				+ "<color>ffff0000</color>" + "<width>8</width>" + " </LineStyle>" + "<PolyStyle>"
				+ "<color>7f00ff00</color>" + " </PolyStyle>" + " </Style>" + "<Placemark>"
				+ " <name>Absolute Extruded</name>"
				+ "<description>Transparent green wall with yellow outlines</description>"
				+ " <styleUrl>#yellowLineGreenPoly</styleUrl>" + "<LineString>" + "<extrude>1</extrude>"
				+ " <tessellate>1</tessellate>" + "<altitudeMode>relativeToGround</altitudeMode>" + "<coordinates>"
				+ p.getPoint().get_x() + "," + p.getPoint().get_y() + "," + p.getPoint().get_z() + "\n";
		String cordinate = "";
		for (Fruit f : p.getFruitPath()) {
			cordinate += f.getPoint().get_x() + "," + f.getPoint().get_y() + "," + f.getPoint().get_z() + "\n";
		}
		s += cordinate;
		s += "</coordinates>" + "</LineString>" + "</Placemark>";

		return s;
	}

}
