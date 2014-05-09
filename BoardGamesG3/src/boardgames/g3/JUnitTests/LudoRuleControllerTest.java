package boardgames.g3.JUnitTests;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import game.impl.BoardLocation;
import game.impl.DieRollFactory;
import game.impl.GamePiece;
import game.impl.Move;
import game.impl.Player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import boardgames.g3.core.Ludo.HelpMethodsFinaMedKnuff;
import boardgames.g3.core.Ludo.LudoGameState;


public class LudoRuleControllerTest {
	
	LudoGameState state;
	DieRollFactory die;
	Player player;
	LudoGameState ludoGameState;
	
	@Before
	public void setup(){
		
		state = new LudoGameState();
		ludoGameState = new LudoGameState(1);
	}
	
	
	@Test
	public void testMoveOutOfHome() {
		
		while(state.getDieRollFactory().getNewRoll(state.getPlayerInTurn()).getResult() != 6)
			continue;
		BoardLocation source = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", state.getBoard());
		BoardLocation destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", state.getBoard());
		state.proposeMove(new Move(state.getLastPlayer(), source, destination));
		
		assertNull(HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", state.getBoard()).getPiece());
		GamePiece gp1 = destination.getPiece();
		destination.removePiece(gp1);
		assertNotNull(destination.getPiece());
	}
	
	
	@Test
	public void testDoNotPuschYourOwnPiece(){
		
		BoardLocation sourceforPiece;
		BoardLocation destination;
		
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getLastPlayer(), sourceforPiece, destination));
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;

		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BC", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));		
		Assert.assertTrue(destination.getPieces().size()==2);
		
	}
	
	@Test
	public void testCanPlayerPushAnotherPlayersPiece(){
		BoardLocation sourceforPiece;
		BoardLocation destination;
		BoardLocation sourceForPuschPiece;
		
		//Röd
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", state.getBoard());
		while(state.getDieRollFactory().getNewRoll(state.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", state.getBoard());
		state.proposeMove(new Move(state.getPlayerInTurn(),sourceforPiece,destination));
		
		//Blå
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EH", state.getBoard());
		while(state.getDieRollFactory().getNewRoll(state.getPlayerInTurn()).getResult() != 6)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BI", state.getBoard());
		state.proposeMove(new Move(state.getPlayerInTurn(),sourceforPiece,destination));
		
		//Gul
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("GK", state.getBoard());
		while(state.getDieRollFactory().getNewRoll(state.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("II", state.getBoard());
		state.proposeMove(new Move(state.getPlayerInTurn(),sourceforPiece,destination));
		
		//Grön
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("KE", state.getBoard());
		while(state.getDieRollFactory().getNewRoll(state.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("IB", state.getBoard());
		state.proposeMove(new Move(state.getPlayerInTurn(),sourceforPiece,destination));
		
		//Röd
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EB", state.getBoard());
		while(state.getDieRollFactory().getNewRoll(state.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", state.getBoard());
		state.proposeMove(new Move(state.getPlayerInTurn(),sourceforPiece,destination));
		
		//Blå
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("GK", state.getBoard());
		while(state.getDieRollFactory().getNewRoll(state.getPlayerInTurn()).getResult() != 5)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EH", state.getBoard());
		state.proposeMove(new Move(state.getPlayerInTurn(),sourceforPiece,destination));
		
		sourceForPuschPiece = HelpMethodsFinaMedKnuff.
				getBoardLocationFromPiece(state.getPlayerInTurn().getPieces().get(0), state.getBoard());
		
		Assert.assertTrue(state.getPlayerInTurn().getPieces().get(0).getId()== sourceForPuschPiece.getPiece().getId());
		Assert.assertFalse(state.getPlayerInTurn().getPieces().get(0).getId() == destination.getPiece().getId());
	}
	
}