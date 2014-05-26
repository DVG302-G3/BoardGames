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

import boardgames.g3.core.Ludo.LudoHelpMethods;
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
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BB", ludoGameState.getBoard());
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("DE", ludoGameState.getBoard());
		ludoMoveResult = ludoRuleController.checkAndReturnValidMoves
				(new Move(ludoGameState.getPlayerInTurn(), sourceforPiece, destination));
		Assert.assertTrue(ludoMoveResult instanceof MoveValidImplementation);
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BC", ludoGameState.getBoard());
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("AE", ludoGameState.getBoard());
		ludoMoveResult = ludoRuleController.checkAndReturnValidMoves
				(new Move(ludoGameState.getLastPlayer(), sourceforPiece, destination));
	}

	// Used for testing of method. Changed visibilty to private.
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
		
		//R�d
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("EA", lGameState.getBoard());
		while(lGameState.getDieRollFactory().getNewRoll(lGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BB", lGameState.getBoard());
		lGameState.proposeMove(new Move(lGameState.getPlayerInTurn(),sourceforPiece,destination));
		//Bl�
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("AG", lGameState.getBoard());
		while(lGameState.getDieRollFactory().getNewRoll(lGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BI", lGameState.getBoard());
		lGameState.proposeMove(new Move(lGameState.getPlayerInTurn(),sourceforPiece,destination));
		//R�d
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("CE", lGameState.getBoard());
		while(lGameState.getDieRollFactory().getNewRoll(lGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("EA", lGameState.getBoard());
		lGameState.proposeMove(new Move(lGameState.getPlayerInTurn(),sourceforPiece,destination));
		//R�d
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("AG", lGameState.getBoard());
		while(lGameState.getDieRollFactory().getNewRoll(lGameState.getPlayerInTurn()).getResult() != 4)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("CE", lGameState.getBoard());
		//lGameState.proposeMove(new Move(lGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		assertTrue(ludoRuleController.needToPush(new Move(lGameState.getPlayerInTurn(), sourceforPiece, destination)));	
		
	}
	
	@Test
	public void testPushOtherPiece(){
		
		BoardLocation sourceforPiece;
		BoardLocation destination;
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("EA", ludoGameState.getBoard());
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BB", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getLastPlayer(), sourceforPiece, destination));
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 1)
			continue;

		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BC", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));

		Assert.assertTrue(destination.getPieces().size()==2);
		
		
	}
	
	@Test
	public void testCanPlayerPushAnotherPlayersPiece(){
		BoardLocation sourceforPiece;
		BoardLocation destination;
		BoardLocation sourceForPuschPiece;
		
		//R�d
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("EA", lGameState.getBoard());
		while(lGameState.getDieRollFactory().getNewRoll(lGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BB", lGameState.getBoard());
		lGameState.proposeMove(new Move(lGameState.getPlayerInTurn(),sourceforPiece,destination));
		//Bl�
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("AG", lGameState.getBoard());
		while(lGameState.getDieRollFactory().getNewRoll(lGameState.getPlayerInTurn()).getResult() != 1)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BI", lGameState.getBoard());
		lGameState.proposeMove(new Move(lGameState.getPlayerInTurn(),sourceforPiece,destination));
		//R�d
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("CE", lGameState.getBoard());
		while(lGameState.getDieRollFactory().getNewRoll(lGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("EA", lGameState.getBoard());
		lGameState.proposeMove(new Move(lGameState.getPlayerInTurn(),sourceforPiece,destination));
		//R�d
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("AG", lGameState.getBoard());
		while(lGameState.getDieRollFactory().getNewRoll(lGameState.getPlayerInTurn()).getResult() != 4)
			continue;
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("CE", lGameState.getBoard());
		sourceForPuschPiece = LudoHelpMethods.getBoardLocationFromPiece(lGameState.getPlayerInTurn().getPieces().get(0), lGameState.getBoard());
		Assert.assertTrue(lGameState.getPlayerInTurn().getPieces().get(0).getId()== sourceForPuschPiece.getPiece().getId());
		Assert.assertFalse(lGameState.getPlayerInTurn().getPieces().get(0).getId() == destination.getPiece().getId());
		
	}
	
	@Test
	public void testCanPieceMoveInToGoalLine(){
		BoardLocation sourceforPiece;
		BoardLocation destination;
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("DE", ludoGameState.getBoard());
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BB", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));

		
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("BG", ludoGameState.getBoard());
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("DE", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("EJ", ludoGameState.getBoard());
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("BG", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("GH", ludoGameState.getBoard());
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("EJ", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("KF", ludoGameState.getBoard());
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("GH", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("GD", ludoGameState.getBoard());
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("KF", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
		
		while(ludoGameState.getDieRollFactory().getNewRoll(ludoGameState.getPlayerInTurn()).getResult() != 6)
			continue;
		destination = LudoHelpMethods.getBoardLocationFromCoordinate("FC", ludoGameState.getBoard());
		sourceforPiece = LudoHelpMethods.getBoardLocationFromCoordinate("GD", ludoGameState.getBoard());
		ludoGameState.proposeMove(new Move(ludoGameState.getPlayerInTurn(),sourceforPiece,destination));
				
	}
}