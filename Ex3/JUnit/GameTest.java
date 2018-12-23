package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import GIS.Game;

class GameTest {

	private Game game;
	private String path = "C:\\Test2\\game_1543684662657.csv";
	private String savePathCSV = "C:\\Test2\\JUnittest.csv";
	private String savePathKML = "C:\\Test2\\JUnittest1.kml";

	@BeforeEach
	void setUp() throws Exception {
		game = new Game(path);
	}

	@Test
	void testGameString() {
		assertEquals(false, game.getPacmanLayer().isEmpty(), "Function is wrong");
		assertEquals(false, game.getFruitLayer().isEmpty(), "Function is wrong");
	}

	@Test
	void testGetPacmanLayer() {
		assertEquals(3, game.getPacmanLayer().size(), "Function is wrong");
	}

	@Test
	void testGetFruitLayer() {
		assertEquals(12, game.getFruitLayer().size(), "Function is wrong");
	}

	@Test
	void testCreateCSVgame() {
		game.createCSVgame(savePathCSV);
		File file = new File(savePathCSV);
		assertEquals(true, file.exists(), "Function is wrong");
	}

	@Test
	void testCreateKMLgame() {
		game.createKMLgame(savePathKML);
		File file = new File(savePathKML);
		assertEquals(true, file.exists(), "Function is wrong");
	}

}
