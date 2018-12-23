package GUI;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Class that hosts the JPanel, the place where the menu and listeners are
 * created
 * 
 * @author Itay Tuson and Sagi Oshri
 *
 */
public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private Board board;
	private BufferedImage icon;

	private Menu run;

	private boolean isRun = false;

	/**
	 * Init frame methods
	 * 
	 * @throws IOException
	 */
	public MyFrame() throws IOException {
		loadImages();
		initGUI();
	}

	/**
	 * Load the icon of the application
	 * 
	 * @throws IOException
	 *             if file not found
	 */
	private void loadImages() {
		try {
			icon = ImageIO.read(new File("pacman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize frame menus and board JPanel
	 * 
	 * @throws IOException
	 */
	private void initGUI() throws IOException {
		board = new Board();
		this.setContentPane(board);
		this.setSize(1433, 642);
		this.setIconImage(icon);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MenuBar menuBar = new MenuBar();
		setMenuBar(menuBar);

		Menu file = new Menu("File");
		menuBar.add(file);

		Menu add = new Menu("Add");
		menuBar.add(add);

		run = new Menu("Run");
		menuBar.add(run);

		MenuItem runGame = new MenuItem("Run Game");
		run.add(runGame);

		MenuItem loadGame = new MenuItem("Load Game");
		file.add(loadGame);

		MenuItem saveGame = new MenuItem("Save Game");
		file.add(saveGame);

		MenuItem clearGame = new MenuItem("Clear Game");
		file.add(clearGame);

		MenuItem addPacman = new MenuItem("Pacmans");
		add.add(addPacman);

		MenuItem addFruit = new MenuItem("Fruits");
		add.add(addFruit);

		initListeners(loadGame, saveGame, clearGame, runGame, addPacman, addFruit);
	}

	/**
	 * Initialize menu item listeners, every listener will fire the specified method
	 * 
	 * @param loadGame
	 *            Menu Item
	 * @param saveGame
	 *            Menu Item
	 * @param clearGame
	 *            Menu Item
	 * @param runGame
	 *            Menu Item
	 * @param addPacmans
	 *            Menu Item
	 * @param addFruits
	 *            Menu Item
	 */
	private void initListeners(MenuItem loadGame, MenuItem saveGame, MenuItem clearGame, MenuItem runGame,
			MenuItem addPacmans, MenuItem addFruits) {
		loadGame.addActionListener(e -> loadGame());

		saveGame.addActionListener(e -> saveGame());

		clearGame.addActionListener(e -> clearGame());

		addPacmans.addActionListener(e -> addPacmans());

		addFruits.addActionListener(e -> addFruits());

		runGame.addActionListener(e -> runGame());

	}

	/**
	 * Fired when clicking 'Load Game'
	 */
	private void loadGame() {
		System.out.println("> Load Game");

		clearGame();
		String path = chooseFilePath();

		if (path != null) {
			board.loadGame(path);
		} else
			System.out.println("Path not entered");
	}

	/**
	 * Fired when clicking 'Save Game'
	 */
	private void saveGame() {
		System.out.println("> Save Game");

		if (isRun == false)
			JOptionPane.showMessageDialog(this, "Please run the game before saving in order to calculate paths");

		else {
			String filePath = saveFilePath();

			// save to file
			if (filePath != null) {
				board.saveGame(filePath);
			} else
				System.out.println("No path chosen");
		}
	}

	/**
	 * Fired when clicking 'Clear Game', or when loading new game
	 */
	private void clearGame() {
		System.out.println("> Clear Game");

		board.clearGame();
		isRun = false;
	}

	/**
	 * Fired when clicking the 'Add Pacmans' in the menu, will allow user to mouse
	 * click and add element
	 */
	private void addPacmans() {
		System.out.println("> Add Pacmans");

		board.setAdd(true);
		board.setPacmanOption(true);
		board.setFruitOption(false);
	}

	/**
	 * Fired when clicking the 'Add Fruits' in the menu, will allow user to mouse
	 * click and add element
	 */
	private void addFruits() {
		System.out.println("> Add Fruits");

		board.setAdd(true);
		board.setPacmanOption(false);
		board.setFruitOption(true);
	}

	/**
	 * Fired when clicking 'Run Game'
	 */
	private void runGame() {
		System.out.println("> Run Game");
		run.setEnabled(false);
		isRun = true;
		board.runGame();
		run.setEnabled(true);
	}

	/**
	 * Choose file prompt, used to load CSV file into the game
	 * 
	 * @return a String that defines the path of the file
	 */
	private String chooseFilePath() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
		fileChooser.setFileFilter(filter);

		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			return selectedFile.getAbsolutePath();
		}
		return null;
	}

	/**
	 * Save file prompt, used to save CSV or KML files from the game
	 * 
	 * @return a String that defines the save path of the file, null if not saved
	 */
	private String saveFilePath() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
		FileNameExtensionFilter filter2 = new FileNameExtensionFilter("KML files", "kml");
		fileChooser.setFileFilter(filter);
		fileChooser.setFileFilter(filter2);

		int result = fileChooser.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			String savePath = "";
			File selectedFile = fileChooser.getSelectedFile();
			String ext = fileChooser.getFileFilter().getDescription();
			if (ext.contains("KML"))
				savePath += selectedFile.getAbsolutePath() + ".kml";
			else
				savePath += selectedFile.getAbsolutePath() + ".csv";
			return savePath;
		}
		return null;
	}
}
