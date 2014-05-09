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
		BoardLocation sourceForPuschPiece;
		
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
//		System.out.println("0 pieces on the location EA: "+destination.getPieces().size());
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getLastPlayer(), sourceforPiece, destination));
//		System.out.println("1 pieces on the location EA: "+destination.getPieces().size());
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;

		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BC", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
//		System.out.println("2 pieces on the location EA:: "+destination.getPieces().size());
//		System.out.println(destination.getPiece().getId());		
		Assert.assertTrue(destination.getPieces().size()==2);
//		sourceForPuschPiece = HelpMethodsFinaMedKnuff.
//				getBoardLocationFromPiece(ludoGameState.getPlayerInTurn().getPieces().get(0), ludoGameState.getBoard());
//		System.out.println(ludoGameState.getPlayerInTurn().getPieces().get(0).getId());
//		System.out.println(sourceForPuschPiece.getId());
		
	}
	
	@Test
	public void testCanPlayerPushAnotherPlayersPiece(){
		
	}
	
}
