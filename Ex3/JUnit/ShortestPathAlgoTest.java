package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Algorithms.ShortestPathAlgo;
import GIS.GIS_element;
import GIS.Game;
import GIS.Pacman;

class ShortestPathAlgoTest {

	private Game game;

	private ShortestPathAlgo shortestPathAlgo;

	@BeforeEach
	void setUp() throws Exception {
		game = new Game("C:\\Test2\\game_1543684662657.csv");

		shortestPathAlgo = new ShortestPathAlgo(game);

	}

	@Test
	void testCreateCopy() {
		shortestPathAlgo.createCopy(game);
		assertNotEquals(shortestPathAlgo.getGame().getPacmanLayer(), shortestPathAlgo.getCopyPacmans(),
				"Function is wrong");
	}

	@Test
	void testINIT() {
		shortestPathAlgo.INIT();

		Iterator<GIS_element> pacmanIT = game.getPacmanLayer().iterator();
		while (pacmanIT.hasNext()) {
			Pacman tmp = (Pacman) pacmanIT.next();
			if (tmp.getID() == 0) {
				assertEquals(3, tmp.getFruitPath().get(0).getID(), "Function is wrong");
				assertEquals(2, tmp.getFruitPath().get(1).getID(), "Function is wrong");
				assertEquals(1, tmp.getFruitPath().get(2).getID(), "Function is wrong");
				assertEquals(0, tmp.getFruitPath().get(3).getID(), "Function is wrong");
			} else if (tmp.getID() == 1) {
				assertEquals(11, tmp.getFruitPath().get(0).getID(), "Function is wrong");
				assertEquals(6, tmp.getFruitPath().get(1).getID(), "Function is wrong");
				assertEquals(5, tmp.getFruitPath().get(2).getID(), "Function is wrong");
				assertEquals(4, tmp.getFruitPath().get(3).getID(), "Function is wrong");
			} else if (tmp.getID() == 2) {
				assertEquals(10, tmp.getFruitPath().get(0).getID(), "Function is wrong");
				assertEquals(9, tmp.getFruitPath().get(1).getID(), "Function is wrong");
				assertEquals(8, tmp.getFruitPath().get(2).getID(), "Function is wrong");
				assertEquals(7, tmp.getFruitPath().get(3).getID(), "Function is wrong");
			}
		}
	}

}
