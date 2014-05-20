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
import boardgames.g3.core.Ludo.LudoMoveResult;
import boardgames.g3.core.Ludo.LudoRuleController;


public class LudoRuleControllerTest {
	
	LudoGameState state;
	DieRollFactory die;
	Player player;
	LudoGameState ludoGameState;
	LudoRuleController ludoRuleController;
	LudoMoveResult ludoMoveResult; 
	
	@Before
	public void setup(){
		
		ludoGameState = new LudoGameState(1);
		ludoRuleController = new LudoRuleController(ludoGameState);
	}
	
//	@Test
//	public void testIsGameFinished(){
//		state.getPlayers().remove(0);
//		state.getPlayers().remove(0);
//		state.getPlayers().remove(0);
//		Assert.assertTrue(ludoRuleController.isGameFinished(state));
//	}
	
	@Test
	public void testInitiateStepCounterMap(){
		
	}
	
	@Test
	public void testCheckAndReturnValidMoves(){
		
		BoardLocation sourceforPiece;
		BoardLocation destination;
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", ludoGameState.getBoard());
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("DE", ludoGameState.getBoard());
		System.out.println(ludoGameState.getPlayerInTurn());
		ludoMoveResult = ludoRuleController.checkAndReturnValidMoves
				(new Move(ludoGameState.getPlayerInTurn(), sourceforPiece, destination));
		System.out.println("H�R!!: "+ludoMoveResult);
		Assert.assertTrue(ludoMoveResult == LudoMoveResult.MOVE_VALID_INBASE_SIX);
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BC", ludoGameState.getBoard());
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("AE", ludoGameState.getBoard());
		ludoMoveResult = ludoRuleController.checkAndReturnValidMoves
				(new Move(ludoGameState.getLastPlayer(), sourceforPiece, destination));
		System.out.println("H�R!!: "+ludoMoveResult);
	}
	
	@Test
	public void testIsValidMove(){
		
	}
	
	@Test
	public void testPlayerCantMakeAMove(){
		
	}
	
	@Test
	public void testCanAnyPieceMakeAMove(){
		
	}
	
	@Test
	public void testCanPieceMove(){
		
	}
	
	@Test
	public void testDestinationIsAlreadyOccupado(){
		
	}
	
//	@Test
//	public void testMoveOutOfHome() {
//		
//		while(state.getDieRollFactory().getNewRoll(state.getPlayerInTurn()).getResult() != 6)
//			continue;
//		BoardLocation source = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", state.getBoard());
//		BoardLocation destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", state.getBoard());
//		state.proposeMove(new Move(state.getLastPlayer(), source, destination));
//		
//		assertNull(HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", state.getBoard()).getPiece());
//		GamePiece gp1 = destination.getPiece();
//		destination.removePiece(gp1);
//		assertNotNull(destination.getPiece());
//	}
//	
//	
//	@Test
//	public void testDoNotPuschYourOwnPiece(){
//		
//		BoardLocation sourceforPiece;
//		BoardLocation destination;
//		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", ludoGameState.getBoard());
//		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
//			continue;
//		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", ludoGameState.getBoard());
//		ludoGameState.proposeMove(new Move(ludoGameState.getLastPlayer(), sourceforPiece, destination));
//		
//		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
//			continue;
//
//		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BC", ludoGameState.getBoard());
//		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
//
//		Assert.assertTrue(destination.getPieces().size()==2);
//		
//		
//	}
//	
//	@Test
//	public void testCanPlayerPushAnotherPlayersPiece(){
//		BoardLocation sourceforPiece;
//		BoardLocation destination;
//		BoardLocation sourceForPuschPiece;
//		
//		//R�d
//		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", state.getBoard());
//		while(state.getDieRollFactory().getNewRoll(state.getPlayerInTurn()).getResult() != 1)
//			continue;
//		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", state.getBoard());
//		state.proposeMove(new Move(state.getPlayerInTurn(),sourceforPiece,destination));
//		System.out.println(destination.getPiece().getId());
//		//Bl�
//		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EH", state.getBoard());
//		while(state.getDieRollFactory().getNewRoll(state.getPlayerInTurn()).getResult() != 6)
//			continue;
//		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BI", state.getBoard());
//		state.proposeMove(new Move(state.getPlayerInTurn(),sourceforPiece,destination));
//		
//		//Gul
//		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("GK", state.getBoard());
//		while(state.getDieRollFactory().getNewRoll(state.getPlayerInTurn()).getResult() != 1)
//			continue;
//		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("II", state.getBoard());
//		state.proposeMove(new Move(state.getPlayerInTurn(),sourceforPiece,destination));
//		
//		//Gr�n
//		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("KE", state.getBoard());
//		while(state.getDieRollFactory().getNewRoll(state.getPlayerInTurn()).getResult() != 1)
//			continue;
//		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("IB", state.getBoard());
//		state.proposeMove(new Move(state.getPlayerInTurn(),sourceforPiece,destination));
//		
//		//R�d
//		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EB", state.getBoard());
//		while(state.getDieRollFactory().getNewRoll(state.getPlayerInTurn()).getResult() != 1)
//			continue;
//		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", state.getBoard());
//		state.proposeMove(new Move(state.getPlayerInTurn(),sourceforPiece,destination));
//		
//		
//		//Bl�
//		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("GK", state.getBoard());
//		while(state.getDieRollFactory().getNewRoll(state.getPlayerInTurn()).getResult() != 5)
//			continue;
//		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EH", state.getBoard());
//		state.proposeMove(new Move(state.getPlayerInTurn(),sourceforPiece,destination));
//		
//		sourceForPuschPiece = HelpMethodsFinaMedKnuff.
//				getBoardLocationFromPiece(state.getPlayerInTurn().getPieces().get(0), state.getBoard());
//		
//		Assert.assertTrue(state.getPlayerInTurn().getPieces().get(0).getId()== sourceForPuschPiece.getPiece().getId());
//		Assert.assertFalse(state.getPlayerInTurn().getPieces().get(0).getId() == destination.getPiece().getId());
//	}
//	
//	@Test
//	public void testCanPieceMoveInToGoalLine(){
//		BoardLocation sourceforPiece;
//		BoardLocation destination;
//		
//		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
//			continue;
//		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("DE", ludoGameState.getBoard());
//		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", ludoGameState.getBoard());
//		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
//		System.out.println("AAA");
//		
//		
//		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
//			continue;
//		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BG", ludoGameState.getBoard());
//		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("DE", ludoGameState.getBoard());
//		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
//		System.out.println(destination.getId());
//		System.out.println(destination.getPiece().getId());
//		System.out.println("BBB");
//		
//		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
//			continue;
//		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EJ", ludoGameState.getBoard());
//		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BG", ludoGameState.getBoard());
//		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
//		System.out.println("CCC");
//		
//		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
//			continue;
//		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("GH", ludoGameState.getBoard());
//		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EJ", ludoGameState.getBoard());
//		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
//		System.out.println("DDD");
//		
//		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
//			continue;
//		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("KF", ludoGameState.getBoard());
//		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("GH", ludoGameState.getBoard());
//		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
//		System.out.println("EEE");
//		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
//			continue;
//		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("GD", ludoGameState.getBoard());
//		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("KF", ludoGameState.getBoard());
//		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
//		System.out.println("FFF");
//		
//		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
//			continue;
//		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("FC", ludoGameState.getBoard());
//		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("GD", ludoGameState.getBoard());
//		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
//		System.out.println(destination.getId());
//		System.out.println("GGG");
//				
//	}
}