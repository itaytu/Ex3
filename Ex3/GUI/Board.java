package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Algorithms.Map;
import Algorithms.ShortestPathAlgo;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.Game;
import GIS.Pacman;
import Geom.Point3D;

/**
 * Class that defines the board behavior
 * 
 * @author Itay Tuson and Sagi Oshri
 *
 */
public class Board extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	private ShortestPathAlgo path;

	private Set<Line> lines = new HashSet<>();

	private Map mapProperties;
	private Game myGame;
	private BufferedImage map, pacman, fruit;

	private static int counterP;
	private static int counterF;

	private boolean add, pacmanOption, fruitOption = false;
	private boolean isRunning;

	/**
	 * Initialize board mouse listeners and counters
	 */
	public Board() {
		this.addMouseListener(this);
		myGame = new Game();
		counterP = 0;
		counterF = 0;
		INIT();
	}

	public void INIT() {
		loadImages();
	}

	/**
	 * Load map, pacman and fruit images from resources
	 */
	private void loadImages() {
		try {
			map = ImageIO.read(new File("Ariel1.png"));
			pacman = ImageIO.read(new File("pacman.png"));
			fruit = ImageIO.read(new File("fruit.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		repaint();
	}

	/**
	 * Paint method for painting graphics on board, Initializes Map Properties as
	 * well
	 */
	@Override
	public void paint(Graphics g) {

		int h = this.getHeight();
		int w = this.getWidth();

		mapProperties = new Map(map, w, h, 35.20238, 35.21236, 32.10190, 32.10569);

		g.drawImage(map, 0, 0, w, h, this);

		if (!myGame.getPacmanLayer().isEmpty()) {
			Iterator<GIS_element> pacmanIT = myGame.getPacmanLayer().iterator();
			while (pacmanIT.hasNext()) {
				Pacman p = (Pacman) pacmanIT.next();
				int[] arr = mapProperties.gps2Pixels(p.getPoint());
				g.drawImage(pacman, arr[0], arr[1], this);
			}
		}

		if (!myGame.getFruitLayer().isEmpty()) {
			Iterator<GIS_element> fruitIT = myGame.getFruitLayer().iterator();
			while (fruitIT.hasNext()) {
				Fruit f = (Fruit) fruitIT.next();
				int[] arr = mapProperties.gps2Pixels(f.getPoint());
				g.drawImage(fruit, arr[0], arr[1], this);
			}
		}

		if ((!lines.isEmpty())) {
			for (Line line : lines) {
				int[] from = mapProperties.gps2Pixels(line.getFrom());
				int[] to = mapProperties.gps2Pixels(line.getTo());
				Graphics2D g2d = (Graphics2D) g;
				g2d.setStroke(new BasicStroke(3));
				g2d.setColor(line.getColor());
				g2d.drawLine(from[0], from[1], to[0], to[1]);
			}
		}

	}

	/**
	 * Fired when mouse clicked, adds chosen objects to the list and repaints panel
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

		if (add) {

			int x = e.getX();
			int y = e.getY();

			mapProperties = new Map(map, getWidth(), getHeight(), 35.20238, 35.21236, 32.10190, 32.10569);

			Point3D tmp = mapProperties.toCoords(x, y);

			if (pacmanOption) {
				Pacman p = new Pacman(tmp.get_x(), tmp.get_y());
				myGame.addPacman(p);
				counterP++;
			} else if (fruitOption) {
				Fruit f = new Fruit(tmp.get_x(), tmp.get_y());
				myGame.addFruit(f);
				counterF++;
			}
			repaint();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public BufferedImage getMap() {
		return map;
	}

	/**
	 * Checks if user entered the 'Add Pacmans/Fruits' mode
	 * 
	 * @return
	 */
	public boolean isAdd() {
		return add;
	}

	/**
	 * Sets 'Add' mode
	 * 
	 * @param add
	 */
	public void setAdd(boolean add) {
		this.add = add;
	}

	public boolean isPacmanOption() {
		return pacmanOption;
	}

	public void setPacmanOption(boolean pacmanOption) {
		this.pacmanOption = pacmanOption;
	}

	public boolean isFruitOption() {
		return fruitOption;
	}

	public void setFruitOption(boolean fruitOption) {
		this.fruitOption = fruitOption;
	}

	/**
	 * Clears board from current active objects
	 */
	public void clearGame() {

		if (!myGame.getPacmanLayer().isEmpty())
			myGame.getPacmanLayer().clear();
		if (!myGame.getFruitLayer().isEmpty())
			myGame.getFruitLayer().clear();
		if (!lines.isEmpty())
			lines.clear();

		Pacman.setPacmanCount(0);
		Fruit.setFruitCount(0);

		counterP = counterF = 0;
		repaint();
	}

	/**
	 * Loads a game from a CSV file
	 * 
	 * @param path
	 */
	public void loadGame(String path) {
		this.myGame = new Game(path);
		counterP = myGame.getPacmanLayer().size();
		counterF = myGame.getFruitLayer().size();
		repaint();
	}

	/**
	 * Create .CSV or .KML game in a given path
	 * 
	 * @param path
	 */
	public void saveGame(String path) {
		if (path.endsWith(".csv")) {
			this.myGame.createCSVgame(path);
		}

		else if (path.endsWith(".kml")) {
			this.myGame.createKMLgame(path);
		}
	}

	/**
	 * Simple repaint function
	 */
	public synchronized void move() {
		repaint();
	}

	/**
	 * Run the game. Apply the shortest path algorythm for every pacman, then
	 * initialize the changes. After that, build the path of every pacman on screen,
	 * at last start every pacman thread.
	 */
	public void runGame() {

		isRunning = false;

		clearPaths();

		path = new ShortestPathAlgo(myGame);
		path.createCopy(myGame);
		path.INIT();

		buildPath();

		repaint();

		Iterator<GIS_element> pacmanIT = myGame.getPacmanLayer().iterator();
		while (pacmanIT.hasNext()) {
			Pacman p = (Pacman) pacmanIT.next();
			Runnable pacmanThread = new PacmanThread(p);
			Thread thread = new Thread(pacmanThread);
			thread.start();
		}

		isRunning = true;

		Iterator<GIS_element> pacmanIT2 = myGame.getPacmanLayer().iterator();
		while (pacmanIT2.hasNext()) {
			Pacman p = (Pacman) pacmanIT2.next();
			System.out.print("The path for Pacman " + p.getID() + " is: ");
			for (int i = 0; i < p.getFruitPath().size(); i++) {
				System.out.print(p.getFruitPath().get(i).getID() + " ");
			}
			System.out.println();
			System.out.print("The time took for each step is: ");
			for (int i = 0; i < p.getTimePath().size(); i++) {
				System.out.print(p.getTimePath().get(i) + " ");
			}
			System.out.println();
		}

	}

	/**
	 * Resets pacmans and fruits data to initiate new run of the game
	 */
	private void clearPaths() {
		Iterator<GIS_element> pacmanIT = myGame.getPacmanLayer().iterator();
		Iterator<GIS_element> fruitIT = myGame.getFruitLayer().iterator();

		while (pacmanIT.hasNext()) {
			Pacman tmp = (Pacman) pacmanIT.next();
			tmp.resetData();
		}

		while (fruitIT.hasNext()) {
			Fruit tmp = (Fruit) fruitIT.next();
			tmp.resetData();
		}

	}

	/**
	 * Build every pacman path of fruits, ordered, using lines
	 */
	private void buildPath() {
		Iterator<GIS_element> pacmanIT = myGame.getPacmanLayer().iterator();
		while (pacmanIT.hasNext()) {
			Pacman p = (Pacman) pacmanIT.next();
			Color randomColor = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());

			for (int i = 0; i < p.getFruitPath().size(); i++) {
				drawPathLines(p.getPoint(), p.getFruitPath(), randomColor);
			}
		}
	}

	/**
	 * Method to draw a line. Adding line to the list, and repaints the frame
	 * 
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
	private void drawLineFromPixels(int x1, int y1, int x2, int y2, Color color) {

		Line line = new Line(mapProperties.getConverter(), x1, y1, x2, y2, color);
		lines.add(line);
	}

	/**
	 * Drawing single line from 2 coordinates
	 * 
	 * @param from
	 *            start point
	 * @param to
	 *            end point
	 * @param c
	 *            color of line
	 */
	private void drawLineFromCoords(Point3D from, Point3D to, Color c) {

		mapProperties = new Map(map, getWidth(), getHeight(), 35.20238, 35.21236, 32.10190, 32.10569);

		int[] FromArr = mapProperties.gps2Pixels(from);
		int[] toArr = mapProperties.gps2Pixels(to);

		drawLineFromPixels(FromArr[0], FromArr[1], toArr[0], toArr[1], c);
	}

	/**
	 * Drawing full path of lines
	 * 
	 * @param startPoint
	 *            in coordinates
	 * @param path
	 *            of fruits
	 * @param c
	 *            color
	 */
	private void drawPathLines(Point3D startPoint, ArrayList<Fruit> path, Color c) {

		for (int i = 0; i < path.size(); i++) {

			Point3D to = path.get(i).getPoint();

			// Draw line - From first pacman point to the first fruit
			if (i == 0)
				drawLineFromCoords(startPoint, to, c);

			// Draw line - From the first fruit to the next fruit
			else {
				Point3D from = path.get(i - 1).getPoint();
				drawLineFromCoords(from, to, c);
			}
		}
	}

	public Game getGame() {
		return this.myGame;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	/**
	 * Pacman thread that will define the behavior of the pacman when running the
	 * game. Every pacman will move in parallel.
	 */
	public class PacmanThread implements Runnable {

		Pacman pacman;
		pacmanMovement movement;
		Point3D startPoint;

		PacmanThread(Pacman pacman) {
			this.pacman = pacman;
			this.movement = new pacmanMovement(pacman);
			this.startPoint = new Point3D(pacman.getPoint());
		}

		/**
		 * Every pacman will translate his point to the fruit points on his path. The
		 * thread will sleep for 20 millis, after calling the move/repaint method.
		 */
		@Override
		public void run() {

			for (int i = 0; i < movement.getMovement().size(); i++) {
				pacman.setPoint(movement.getMovement().get(i));
				move();
				// repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			pacman.setPoint(startPoint);
			move();
			// repaint();
		}

	}

}
