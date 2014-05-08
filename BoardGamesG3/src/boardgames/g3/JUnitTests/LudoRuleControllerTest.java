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
	
	@Before
	public void setup(){
		
		state = new LudoGameState();
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
	
	public void testDoNotPuschYourOwnPiece(){
		
		while(state.getDieRollFactory().getNewRoll(state.getPlayerInTurn()).getResult() != 1)
			continue;
		
		BoardLocation sourceForPieceOne = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", state.getBoard());
		BoardLocation sourceForPieceTwo = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("CB", state.getBoard());
		BoardLocation destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", state.getBoard());
		state.proposeMove(new Move(state.getLastPlayer(), sourceForPieceOne, destination));
		GamePiece gp1 = destination.getPiece();
		destination.removePiece(gp1);
		assertNotNull(destination.getPiece());
		
		while(state.getDieRollFactory().getNewRoll(state.getPlayerInTurn()).getResult() != 1)
			continue;
		
		state.proposeMove(new Move(state.getLastPlayer(), sourceForPieceTwo, destination));
		GamePiece gp2 = destination.getPiece();
		destination.removePiece(gp2);
		Assert.assertTrue(destination.getPieces().size()==2);
		
	}
}
