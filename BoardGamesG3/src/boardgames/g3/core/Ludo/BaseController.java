package boardgames.g3.core.Ludo;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Move;

import java.util.List;

public class BaseController {
	GameState state;
	
	public BaseController(GameState state)
	{
		this.state = state;
	}

	public boolean checkIfPieceInbase(Move move) {
		if (move.getPlayer().getName().equals(LudoStaticValues.REDPLAYER))
			return LudoStaticValues.REDHOME.contains(move.getSource().getId());
		else if (move.getPlayer().getName().equals(LudoStaticValues.BLUEPLAYER))
			return LudoStaticValues.BLUEHOME.contains(move.getSource().getId());

		else if (move.getPlayer().getName().equals(LudoStaticValues.YELLOWPLAYER))
			return LudoStaticValues.YELLOWHOME.contains(move.getSource().getId());		
		else
			return LudoStaticValues.GREENHOME.contains(move.getSource().getId());
	}
	
	
	public LudoMoveResult isValidMoveFromBase(Move move) {

		return isDestinationPlayersStartPosition(move);
	}
	
		private LudoMoveResult isDestinationPlayersStartPosition(Move move) {
		if (move.getPlayer().getName().equals("Red")) {
			return checkValidMoveFromStartForPlayer(move,
					LudoStaticValues.REDSTART, LudoStaticValues.REDSTARTSIXES,
					LudoStaticValues.REDHOME);
		}

		else if (move.getPlayer().getName().equals("Blue")) {
			return checkValidMoveFromStartForPlayer(move,
					LudoStaticValues.BLUESTART,
					LudoStaticValues.BLUESTARTSIXES, LudoStaticValues.BLUEHOME);
		}

		else if (move.getPlayer().getName().equals("Yellow")) {
			return checkValidMoveFromStartForPlayer(move,
					LudoStaticValues.YELLOWSTART,
					LudoStaticValues.YELLOWSTARTSIXES,
					LudoStaticValues.YELLOWHOME);
		}

		else {
			return checkValidMoveFromStartForPlayer(move,
					LudoStaticValues.GREENSTART,
					LudoStaticValues.GREENSTARTSIXES,
					LudoStaticValues.GREENHOME);
		}

	}
		
		private LudoMoveResult checkValidMoveFromStartForPlayer(Move move,
				String start, String startSix, List<String> homeValues) {
			if (getDiceResult() == 1) {
				if (move.getDestination().getId().equals(start)
						&& destinationDoesNotAlreadyContainTwoPieces(move)) {
					return LudoMoveResult.MOVE_VALID;
				} else
					return LudoMoveResult.MOVE_INCORRECTNUMBEROFSTEPS;
			}

			else if (getDiceResult() == 6
					&& destinationDoesNotAlreadyContainTwoPieces(move)) {
				if (move.getDestination().getId().equals(startSix)) {
					return LudoMoveResult.MOVE_VALID;
				} else if (move.getDestination().getId().equals(start)) {

					for (String basePositions : homeValues) {
						BoardLocation home = HelpMethodsFinaMedKnuff
								.getBoardLocationFromCoordinate(basePositions,
										state.getBoard());
						if (home.getPiece() != null
								&& home.getPiece() != move.getPiece()) {
							if (move.getDestination().getPieces().isEmpty())
								return LudoMoveResult.MOVE_VALID_INBASE_TWO_PIECES;
						}
					}

					return LudoMoveResult.MOVE_INVALID_INBASE_TWO_PIECES_NOT_AVAILABLE;
				}

			}

			if (HelpMethodsFinaMedKnuff.doesPlayerHaveAnyPiecesOnTheBoard(
					move.getPlayer(), state.getBoard()))
				return LudoMoveResult.MOVE_INCORRECTNUMBEROFSTEPS;
			else
				return LudoMoveResult.MOVE_IN_BASE_DID_NOT_GET_THE_CORRECT_EYES_ON_THE_DICE_TO_MOVE_OUT;

		}

		private boolean destinationDoesNotAlreadyContainTwoPieces(Move move) {
			List<GamePiece> destinationPieces = move.getDestination().getPieces();
			if (destinationPieces.size() > 1)
				return false;
			else
				return true;
		}
		
		private int getDiceResult() {
			return state.getDieRollFactory().getLastRoll().getResult();
		}
	
	
}
