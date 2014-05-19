package boardgames.g3.core.Ludo;

import game.impl.GamePiece;
import game.impl.Move;

public class LudoMoveController {

	LudoGameState gameState;
	LudoRuleController ruler;
	MoveValidExecutor moveValidExec;
	MoveValidInbaseTwoPiecesExecutor moveInBaseExec;

	public LudoMoveController(LudoGameState gameState) {
		this.gameState = gameState;

		moveValidExec = new MoveValidExecutor();
		moveInBaseExec = new MoveValidInbaseTwoPiecesExecutor(gameState);
		ruler = new LudoRuleController(gameState);

	}

	public Boolean proposeMove(Move move) {
		LudoMoveResult result = ruler.isValidMove(move);
		switch (result) {
		case MOVE_VALID:
			if (ruler.needToPush(move))
				ruler.pushOtherPiece(move.getDestination());
			gameState.setMessage("");
			ruler.addStepsToCounter(move);
			moveValidExec.executeAndMakeSureThatNoPieceWillBeDeleted(move);
			gameState.nextTurn();
			return true;

		case MOVE_LAPSED:
			gameState.setMessage(LudoStaticValues.MOVE_LAPSED);
			move.execute();
			System.out.println("Hallå!");
			gameState.nextTurn();
			return true;
		case MOVE_INVALID_CANT_LAPSE_AGAIN:
			gameState
					.setMessage(LudoStaticValues.MOVE_INVALID_CANT_LAPSE_AGAIN);
			return true;
		case MOVE_NOGAMEPIECE:
			gameState.setMessage(LudoStaticValues.MOVE_NOGAMEPIECE);
			return false;
		case MOVE_INCORRECTNUMBEROFSTEPS:
			gameState.setMessage(LudoStaticValues.MOVE_INCORRECTNUMBEROFSTEPS);
			return false;
		case MOVE_IN_BASE_DID_NOT_GET_THE_CORRECT_EYES_ON_THE_DICE_TO_MOVE_OUT:
			gameState
					.setMessage(LudoStaticValues.MOVE_IN_BASE_DID_NOT_GET_THE_CORRECT_EYES_ON_THE_DICE_TO_MOVE_OUT);
			gameState.nextTurn();
			return false;
		case MOVE_VALID_INBASE_ONE:
			if (ruler.needToPush(move))
				ruler.pushOtherPiece(move.getDestination());
			gameState.setMessage("");
			ruler.setStepsForPiece(move.getSource().getPiece(), 1);
			moveValidExec.executeAndMakeSureThatNoPieceWillBeDeleted(move);
			gameState.nextTurn();
			return true;
		case MOVE_VALID_INBASE_SIX:
			if (ruler.needToPush(move))
				ruler.pushOtherPiece(move.getDestination());
			gameState.setMessage("");
			ruler.setStepsForPiece(move.getSource().getPiece(), 6);
			moveValidExec.executeAndMakeSureThatNoPieceWillBeDeleted(move);
			gameState.nextTurn();
			return true;
		case MOVE_VALID_INBASE_TWO_PIECES:
			if (ruler.needToPush(move))
				ruler.pushOtherPiece(move.getDestination());
			move.execute();
			moveInBaseExec.moveSecondPieceToStartPosition(move);
			for (GamePiece gp : move.getSource().getPieces()) {
				ruler.setStepsForPiece(gp, 1);
			}
			gameState.setMessage("");
			gameState.nextTurn();
			return true;
		case MOVE_PIECE_IN_TO_GOAL:
			move.execute();
			return false;
		case MOVE_INVALID_CANT_LAPSE_YOUR_OWN_PIECE:
			gameState
					.setMessage(LudoStaticValues.MOVE_INVALID_CANT_LAPSE_YOUR_OWN_PIECE);
			return false;
		case MOVE_INVALID_CANT_PASS_A_BLOCK:
			gameState
					.setMessage(LudoStaticValues.MOVE_INVALID_CANT_PASS_A_BLOCK);
			return false;
		case MOVE_NO_MOVES_AVAILABLE:
			gameState.setMessage(LudoStaticValues.MOVE_NO_MOVES_AVAILABLE);
			gameState.nextTurn();
			return false;
		case MOVE_INVALIDA_BOARDLOCATION_ALREADY_OCCUPIED:
			gameState
					.setMessage(LudoStaticValues.MOVE_INVALIDA_BOARDLOCATION_ALREADY_OCCUPIED);
			return false;
		default: {
			gameState.setMessage("");
			return false;
		}
		}
	}

}
