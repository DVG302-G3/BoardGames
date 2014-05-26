package boardgames.g3.JUnitTests;

import static org.junit.Assert.*;
import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.Move;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import boardgames.g3.core.Ludo.LudoHelpMethods;
import boardgames.g3.core.Ludo.LudoGameState;
import boardgames.g3.core.Ludo.LudoPlayer;

public class LudoGameStateTest {
	
	
	
	LudoGameState ludoGameState;
	BoardLocation boardLocation;
	List<BoardLocation> boardLocations;
	List<LudoPlayer> players;

	@Before
	public void setUp() throws Exception {
		ludoGameState = new LudoGameState();
		
	}
	
	@Test
	public void testGetLastPlayer(){
				
		BoardLocation sourceforPiece;
		BoardLocation destination;
		
		//Red
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("EA", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BB", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		assertEquals("Red", ludoGameState.getLastPlayer().getName());
		
		//Blue
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("AG", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BI", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		assertEquals("Blue", ludoGameState.getLastPlayer().getName());
		//Yellow
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("GK", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("II", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		assertEquals("Yellow", ludoGameState.getLastPlayer().getName());
		//Green
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("KE", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("IB", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		assertEquals("Green", ludoGameState.getLastPlayer().getName());	
	
	}
	
	@Test
	public void testGetPlayerInTurn(){
		BoardLocation sourceforPiece;
		BoardLocation destination;
		
		assertEquals("Red", ludoGameState.getPlayerInTurn().getName());
		
		//Red
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("EA", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BB", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		assertEquals("Blue", ludoGameState.getPlayerInTurn().getName());
		
		//Blue
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("AG", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BI", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		assertEquals("Yellow", ludoGameState.getPlayerInTurn().getName());		
		//Yellow
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("GK", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("II", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		assertEquals("Green", ludoGameState.getPlayerInTurn().getName());		
		//Green
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("KE", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("IB", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
	}
	
	
	@Test
	public void testHasEnded(){
		assertFalse(ludoGameState.hasEnded());
		assertTrue(ludoGameState.hasEnded());
	}
	
	@Test
	public void testProposeMove(){
		
		BoardLocation sourceforPiece;
		BoardLocation destination;
		
		//R�d
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("EA", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BB", ludoGameState.getBoard());
		assertTrue(ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(), sourceforPiece, destination)));
		

		//Blue
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("AG", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BI", ludoGameState.getBoard());
		assertTrue(ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(), sourceforPiece, destination)));	
		
		//Yellow
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("GK", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("II", ludoGameState.getBoard());
		assertTrue(ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(), sourceforPiece, destination)));
		
		//Green
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("EI", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 4)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("IB", ludoGameState.getBoard());
		assertFalse(ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(), sourceforPiece, destination)));
		
	}
	
	@Test
	public void testNextTurn(){
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		assertTrue(ludoGameState.getPlayerInTurn().getName() == "Red");
		ludoGameState.nextTurn();
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		assertTrue(ludoGameState.getPlayerInTurn().getName() == "Blue");
		ludoGameState.nextTurn();
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		assertTrue(ludoGameState.getPlayerInTurn().getName() == "Yellow");
		ludoGameState.nextTurn();
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		assertTrue(ludoGameState.getPlayerInTurn().getName() == "Green");
	}
	
	
	@Test
	public void testReset(){
		
		BoardLocation sourceforPiece;
		BoardLocation destination;
		
		//R�d
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("EA", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BB", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		assertTrue(ludoGameState.getPlayers().get(0).getPieces().get(0).getId() == destination.getPiece().getId());

		//Blue
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("AG", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BI", ludoGameState.getBoard());

		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		assertTrue(ludoGameState.getPlayers().get(1).getPieces().get(0).getId() == destination.getPiece().getId());
		
		ludoGameState.reset();
		
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BB", ludoGameState.getBoard());
		assertTrue(ludoGameState.getPlayers().get(0).getPieces().get(0).getId() == sourceforPiece.getPiece().getId());
		
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BI", ludoGameState.getBoard());
		assertTrue(ludoGameState.getPlayers().get(1).getPieces().get(0).getId() == sourceforPiece.getPiece().getId());
	}
	

	

}
