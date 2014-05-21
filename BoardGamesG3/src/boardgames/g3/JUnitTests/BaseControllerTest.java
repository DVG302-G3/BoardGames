package boardgames.g3.JUnitTests;

import static org.junit.Assert.assertTrue;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Move;
import game.impl.Player;

import org.junit.Before;
import org.junit.Test;

import boardgames.g3.core.Ludo.BaseController;
import boardgames.g3.core.Ludo.HelpMethodsFinaMedKnuff;
import boardgames.g3.core.Ludo.LudoGameState;
import boardgames.g3.core.Ludo.LudoMoveResult;
import boardgames.g3.core.Ludo.LudoPlayer;
import boardgames.g3.core.Ludo.LudoRuleController;
import boardgames.g3.core.Ludo.MoveNoMovesAvailableImplementation;
import boardgames.g3.core.Ludo.MoveValidInbaseTwoPiecesImplementation;

public class BaseControllerTest {
	LudoGameState gameState;
	BaseController base;
	LudoRuleController ruler;
	
	
	@Before
	public void setup(){
		gameState = new LudoGameState(1);
		ruler = new LudoRuleController(gameState);
		base = new BaseController(gameState, ruler);
	}

	@Test
	public void testCanPlayerMakeAMoveFromBase() {
		Player player = gameState.getPlayers().get(0);
		LudoPlayer ludoPlayer = gameState.getLudoPlayerFromPlayer(player);
		BoardLocation source1 = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BC", gameState.getBoard());
		BoardLocation source2 = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("CB", gameState.getBoard());
		BoardLocation destination1 = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", gameState.getBoard());
		BoardLocation destination6 = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("DE", gameState.getBoard());
		
		while(gameState.getDieRollFactory().getNewRoll(player).getResult() != 6)
			continue;
		
		assertTrue(base.canPlayerMakeAMoveFromBase(ludoPlayer));

		
		while(gameState.getDieRollFactory().getNewRoll(player).getResult() != 1)
			continue;
		destination1.addPiece(player.getPieces().get(1));
		destination1.addPiece(player.getPieces().get(2));
		source1.clear();
		source2.clear();
		
		assertTrue(!base.canPlayerMakeAMoveFromBase(ludoPlayer));
		
		
		while(gameState.getDieRollFactory().getNewRoll(player).getResult() != 2)
			continue;
		assertTrue(!base.canPlayerMakeAMoveFromBase(ludoPlayer));
		
		gameState = new LudoGameState(1);
		
		destination6.addPiece(player.getPieces().get(1));
		source1.clear();
		assertTrue(!base.canPlayerMakeAMoveFromBase(ludoPlayer));
		
		
		
		
	}

	@Test
	public void testCheckIfPieceInbasePlayerBoardLocation() {
		Player player = gameState.getPlayers().get(0);
		LudoPlayer ludoPlayer = gameState.getLudoPlayerFromPlayer(player);
		assertTrue(base.checkIfPieceInbase(ludoPlayer, HelpMethodsFinaMedKnuff.getBoardLocationFromPiece(gameState.getPlayers().get(0).getPieces().get(0), gameState.getBoard())));
	
		GamePiece piece = player.getPieces().get(0);
		BoardLocation pieceLocation = HelpMethodsFinaMedKnuff.getBoardLocationFromPiece(piece, gameState.getBoard());
		pieceLocation.clear();
		
		BoardLocation outsideOfStart = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", gameState.getBoard());
		outsideOfStart.addPiece(piece);

		assertTrue(!base.checkIfPieceInbase(ludoPlayer, outsideOfStart));

	}

	@Test
	public void testIsValidMoveFromBase() {
		Player player = gameState.getPlayers().get(0);
		BoardLocation source1 = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", gameState.getBoard());
		BoardLocation source2 = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BC", gameState.getBoard());
		BoardLocation source3 = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("CB", gameState.getBoard());
		BoardLocation source4 = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("CC", gameState.getBoard());

		BoardLocation destination1 = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", gameState.getBoard());
		BoardLocation destination6 = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("DE", gameState.getBoard());
		
		while(gameState.getDieRollFactory().getNewRoll(player).getResult() != 6)
			continue;
		
		
		Move move = new Move(player, source1, destination1);
		assertTrue(base.checkValidMoveFromBase(move) instanceof MoveValidInbaseTwoPiecesImplementation);

		
		while(gameState.getDieRollFactory().getNewRoll(player).getResult() != 1)
			continue;
		
		destination1.addPiece(player.getPieces().get(1));
		destination1.addPiece(player.getPieces().get(2));
		source1.clear();
		source2.clear();
		
		move = new Move(player, source4, destination1);
		
		assertTrue(base.checkValidMoveFromBase(move) instanceof MoveNoMovesAvailableImplementation);
	}

}
