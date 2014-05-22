package boardgames.g3.JUnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.Move;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import boardgames.g3.core.Ludo.HelpMethodsFinaMedKnuff;
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
	public void testGetBoard() {
		
		for(int i = 0; i<=72; i++){
			boardLocation = new BoardLocation("ID: "+i);
			System.out.println(boardLocation.getId());
			boardLocations.add(boardLocation);
		}
		
		System.out.println(ludoGameState.getBoard().getLocations().size());
		
		Board board = new Board(boardLocations);
		
//		assertTrue(board.getLocations().size() == ludoGameState.getBoard().getLocations().size());
	}
	
	@Test
	public void testGetLastPlayer(){
				
		BoardLocation sourceforPiece;
		BoardLocation destination;
		
		
		
		//R�d
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		assertEquals("Red", ludoGameState.getLastPlayer().getName());
		
		
		//Bl�
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("AG", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BI", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		assertEquals("Blue", ludoGameState.getLastPlayer().getName());
		
				
		//Gul
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("GK", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("II", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		assertEquals("Yellow", ludoGameState.getLastPlayer().getName());
		
			
		//Gr�n
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("KE", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("IB", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		assertEquals("Green", ludoGameState.getLastPlayer().getName());	
	
	}
	
	@Test
	public void testGetPlayerInTurn(){
		BoardLocation sourceforPiece;
		BoardLocation destination;
		
		assertEquals("Red", ludoGameState.getPlayerInTurn().getName());
		
		//R�d
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		assertEquals("Blue", ludoGameState.getPlayerInTurn().getName());
		
		//Bl�
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("AG", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BI", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		assertEquals("Yellow", ludoGameState.getPlayerInTurn().getName());		
		//Gul
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("GK", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("II", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		assertEquals("Green", ludoGameState.getPlayerInTurn().getName());		
		//Gr�n
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("KE", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("IB", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
	}
	
	
	@Test
	public void testGetPlayers(){
		
		List<LudoPlayer> players = new ArrayList<LudoPlayer>();
		
		assertEquals(players, ludoGameState.getPlayers());
	}
	
	@Test
	public void testHasEnded(){
		fail();
	}
	
	@Test
	public void testProposeMove(){
		fail();
	}
	
	@Test
	public void testNextTurn(){
		fail();
	}
	
	@Test
	public void testGetLudoPlayerFromPlayer(){
		fail();
	}
	
	@Test
	public void testReset(){
		fail();
	}
	
	@Test
	public void testGetDieRollFactory(){
		fail();
	}

}
