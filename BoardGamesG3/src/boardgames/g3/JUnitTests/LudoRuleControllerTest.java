package boardgames.g3.JUnitTests;
import static org.junit.Assert.*;
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
import boardgames.g3.core.Ludo.LudoRuleController;
import boardgames.g3.core.Ludo.StrategyMove.MoveStrategy;
import boardgames.g3.core.Ludo.StrategyMove.MoveValidImplementation;


public class LudoRuleControllerTest {
	
	LudoGameState state;
	DieRollFactory die;
	Player player;
	LudoGameState ludoGameState;
	LudoGameState lGameState;
	LudoRuleController ludoRuleController;
	MoveStrategy ludoMoveResult; 
	
	@Before
	public void setup(){
		
		ludoGameState = new LudoGameState(1);
		lGameState = new LudoGameState(2);
		ludoRuleController = new LudoRuleController(ludoGameState);
	}
	
		
	
	
	@Test
	public void testCheckAndReturnValidMoves(){
		
		BoardLocation sourceforPiece;
		BoardLocation destination;
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", ludoGameState.getBoard());
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("DE", ludoGameState.getBoard());
//		System.out.println(ludoGameState.getPlayerInTurn());
		ludoMoveResult = ludoRuleController.checkAndReturnValidMoves
				(new Move(ludoGameState.getPlayerInTurn(), sourceforPiece, destination));
//		System.out.println("HÄR!!: "+ludoMoveResult);
		Assert.assertTrue(ludoMoveResult instanceof MoveValidImplementation);
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BC", ludoGameState.getBoard());
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("AE", ludoGameState.getBoard());
		ludoMoveResult = ludoRuleController.checkAndReturnValidMoves
				(new Move(ludoGameState.getLastPlayer(), sourceforPiece, destination));
//		System.out.println("HÄR!!: "+ludoMoveResult);
	}
	
	@Test
	public void testEvaluateMove(){
		fail();
	}
	
//	@Test
//	public void testMoveOutOfHome() {
//		
//		while(state.getDieRollFactory().getNewRoll(state.getPlayerInTurn()).getResult() != 1)
//			continue;
//		BoardLocation source = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", state.getBoard());
//		BoardLocation destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", state.getBoard());
//		state.proposeMove(new Move(state.getLastPlayer(), source, destination));
//		
//		assertNull(HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", state.getBoard()).getPiece());
//		GamePiece gp1 = destination.getPiece();
//		destination.removePiece(gp1);
//		assertNotNull(destination.getPiece());
//		
//	}

	
	@Test
	public void testCheckIfDiceIsSIXorONE(){
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
//		assertTrue(ludoRuleController.checkIfDiceIsSIXorONE());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 2)
			continue;
//		assertFalse(ludoRuleController.checkIfDiceIsSIXorONE());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
			continue;
//		assertTrue(ludoRuleController.checkIfDiceIsSIXorONE());
		
	}
	
	@Test
	public void testNeedToPush(){
		BoardLocation sourceforPiece;
		BoardLocation destination;
		BoardLocation sourceForPuschPiece;
		
		//Röd
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", lGameState.getBoard());
		while(lGameState.getDieRollFactory().getNewRoll(lGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", lGameState.getBoard());
		lGameState.proposeMove(new Move(lGameState.getPlayerInTurn(),sourceforPiece,destination));
		//Blå
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("AG", lGameState.getBoard());
		while(lGameState.getDieRollFactory().getNewRoll(lGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BI", lGameState.getBoard());
		lGameState.proposeMove(new Move(lGameState.getPlayerInTurn(),sourceforPiece,destination));
		//Röd
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("CE", lGameState.getBoard());
		while(lGameState.getDieRollFactory().getNewRoll(lGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", lGameState.getBoard());
		lGameState.proposeMove(new Move(lGameState.getPlayerInTurn(),sourceforPiece,destination));
		//Röd
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("AG", lGameState.getBoard());
		while(lGameState.getDieRollFactory().getNewRoll(lGameState.getPlayerInTurn()).getResult() != 4)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("CE", lGameState.getBoard());
		//lGameState.proposeMove(new Move(lGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		assertTrue(ludoRuleController.needToPush(new Move(lGameState.getPlayerInTurn(), sourceforPiece, destination)));	
		
	}
	
	@Test
	public void testPushOtherPiece(){
		
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
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", lGameState.getBoard());
		while(lGameState.getDieRollFactory().getNewRoll(lGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", lGameState.getBoard());
		lGameState.proposeMove(new Move(lGameState.getPlayerInTurn(),sourceforPiece,destination));
		//Blå
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("AG", lGameState.getBoard());
		while(lGameState.getDieRollFactory().getNewRoll(lGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BI", lGameState.getBoard());
		lGameState.proposeMove(new Move(lGameState.getPlayerInTurn(),sourceforPiece,destination));
		//Röd
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("CE", lGameState.getBoard());
		while(lGameState.getDieRollFactory().getNewRoll(lGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", lGameState.getBoard());
		lGameState.proposeMove(new Move(lGameState.getPlayerInTurn(),sourceforPiece,destination));
		//Röd
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("AG", lGameState.getBoard());
		while(lGameState.getDieRollFactory().getNewRoll(lGameState.getPlayerInTurn()).getResult() != 4)
			continue;
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("CE", lGameState.getBoard());
		sourceForPuschPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromPiece(lGameState.getPlayerInTurn().getPieces().get(0), lGameState.getBoard());
		Assert.assertTrue(lGameState.getPlayerInTurn().getPieces().get(0).getId()== sourceForPuschPiece.getPiece().getId());
		Assert.assertFalse(lGameState.getPlayerInTurn().getPieces().get(0).getId() == destination.getPiece().getId());
		
	}
	
	@Test
	public void testCanPieceMoveInToGoalLine(){
		BoardLocation sourceforPiece;
		BoardLocation destination;
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("DE", ludoGameState.getBoard());
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));

		
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BG", ludoGameState.getBoard());
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("DE", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EJ", ludoGameState.getBoard());
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BG", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("GH", ludoGameState.getBoard());
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EJ", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("KF", ludoGameState.getBoard());
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("GH", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("GD", ludoGameState.getBoard());
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("KF", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("FC", ludoGameState.getBoard());
		sourceforPiece = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("GD", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
				
	}
}