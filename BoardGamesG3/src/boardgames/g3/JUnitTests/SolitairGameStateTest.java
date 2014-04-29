package boardgames.g3.JUnitTests;

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

		assertFalse(gameState.proposeMove(new Move(
				gameState.getPlayerInTurn(), SolitarHelpMethods
						.getBoardLocationFromCoordinate("14",
								gameState.getBoard()), SolitarHelpMethods
						.getBoardLocationFromCoordinate("34",
								gameState.getBoard()))));

	}

	@Test
	public void hasEnded() {
		assertFalse(gameState.hasEnded());

		for (BoardLocation b : gameState.getBoard().getLocations()) {
			if(b != null)
				b.setPiece(null);
		}
		assertTrue(gameState.hasEnded());

		
		
		int index = 0;
		for (BoardLocation b : gameState.getBoard().getLocations()) {
			if(b != null){
			if (index % 2 == 0)
				b.setPiece(null);
			else
				b.setPiece(new GamePiece("O"));
		}
		}
		assertTrue(gameState.hasEnded());
		
	
		
		for(BoardLocation board : gameState.getBoard().getLocations()){
			if(board != null)
				board.setPiece(null);
		}
		gameState.getBoard().getLocations().get(4).setPiece(new GamePiece("O"));
		gameState.getBoard().getLocations().get(15).setPiece(new GamePiece("O"));
		gameState.getBoard().getLocations().get(20).setPiece(new GamePiece("O"));
		gameState.getBoard().getLocations().get(23).setPiece(new GamePiece("O"));
		gameState.getBoard().getLocations().get(27).setPiece(new GamePiece("O"));
		gameState.getBoard().getLocations().get(32).setPiece(new GamePiece("O"));
		gameState.getBoard().getLocations().get(34).setPiece(new GamePiece("O"));
		gameState.getBoard().getLocations().get(46).setPiece(new GamePiece("O"));
		
		assertTrue(gameState.hasEnded());
		
		
    for(BoardLocation board : gameState.getBoard().getLocations()){
     if(board != null)
       board.setPiece(null);
   }
    
    //Bör ej resultera rätt
    gameState.getBoard().getLocations().get(2).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(14).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(17).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(20).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(25).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(27).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(30).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(33).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(34).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(44).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(45).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(46).setPiece(new GamePiece("O"));
    
    assertTrue(gameState.hasEnded());
		
    
    for(BoardLocation board : gameState.getBoard().getLocations()){
     if(board != null)
       board.setPiece(null);
   }
    
    //Bör ej resultera rätt
    gameState.getBoard().getLocations().get(16).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(17).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(18).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(19).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(20).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(25).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(26).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(27).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(28).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(32).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(33).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(34).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(38).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(39).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(44).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(45).setPiece(new GamePiece("O"));
    gameState.getBoard().getLocations().get(46).setPiece(new GamePiece("O"));
    
    assertTrue(gameState.hasEnded());

	}

}
