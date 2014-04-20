package Test;

import static org.junit.Assert.*;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Move;

import org.junit.Before;
import org.junit.Test;

import boardgames.g3.core.Solitaire.SolitarGameState;
import boardgames.g3.core.Solitaire.SolitarHelpMethods;

public class SolitairGameStateTest {
	SolitarGameState gameState;

	@Before
	public void setup() {
		gameState = new SolitarGameState();
	}

	@Test
	public void proposeMoveTest() {
		assertTrue(gameState.proposeMove(new Move(gameState.getPlayerInTurn(),
				SolitarHelpMethods.getBoardLocationFromCoordinate("24",
						gameState.getBoard()), SolitarHelpMethods
						.getBoardLocationFromCoordinate("44",
								gameState.getBoard()))));
		assertEquals(
				null,
				SolitarHelpMethods.getBoardLocationFromCoordinate("24",
						gameState.getBoard()).getPiece());
		
		assertFalse(gameState.proposeMove(new Move(gameState.getPlayerInTurn(),
				SolitarHelpMethods.getBoardLocationFromCoordinate("24",
						gameState.getBoard()), SolitarHelpMethods
						.getBoardLocationFromCoordinate("34",
								gameState.getBoard()))));
	}

	@Test
	public void hasEnded() {
		assertFalse(gameState.hasEnded());

		for (BoardLocation b : gameState.getBoard().getLocations()) {
			b.setPiece(null);
		}

		assertTrue(gameState.hasEnded());
	
		int index = 0;
		for (BoardLocation b : gameState.getBoard().getLocations()) {
			if(index%2 == 0)
				b.setPiece(null);
			else
				b.setPiece(new GamePiece("O"));
		}

		assertTrue(gameState.hasEnded());

	
	}

}
