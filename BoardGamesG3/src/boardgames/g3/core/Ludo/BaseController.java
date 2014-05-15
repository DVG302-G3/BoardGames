package boardgames.g3.core.Ludo;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Move;
import game.impl.Player;

import java.util.List;

public class BaseController {
	GameState state;

	public BaseController(GameState state) {
		this.state = state;
	}

	public boolean checkIfPieceInbase(Move move) {
		return checkIfPieceInbase(move.getPlayer(), move.getSource());
	}

	public boolean canPlayerMakeAMoveFromBase(Player player) {

		LudoMoveResult result;
		for (GamePiece gp : player.getPieces()) {
			BoardLocation source = HelpMethodsFinaMedKnuff
					.getBoardLocationFromPiece(gp, state.getBoard());
			
			System.out.println(source+" hej");
			if (checkIfPieceInbase(player, source)) {
				result = checkPlayerColorAndGetValidMoveForPiece(player, gp,
						source);
				if (result == LudoMoveResult.MOVE_VALID
						|| result == LudoMoveResult.MOVE_VALID_INBASE_TWO_PIECES) {
					System.out.println("Plats som kan göra drag: "+source.getId());
					return true;
				}
			}
		}

		return false;
	}

	private LudoMoveResult checkPlayerColorAndGetValidMoveForPiece(
			Player player, GamePiece gp, BoardLocation source) {

		LudoMoveResult result = LudoMoveResult.MOVE_NULLOBJECT;
		BoardLocation destination;
		switch (player.getName()) {
		case LudoStaticValues.REDPLAYER:
			if (getDiceResult() == 1) {
				destination = HelpMethodsFinaMedKnuff
						.getBoardLocationFromCoordinate(
								LudoStaticValues.REDSTART, state.getBoard());
				result = isValidMoveFromBase(new Move(player, source,
						destination));
			} else if (getDiceResult() == 6) {
				destination = HelpMethodsFinaMedKnuff
						.getBoardLocationFromCoordinate(
								LudoStaticValues.REDSTARTSIXES,
								state.getBoard());
				result = isValidMoveFromBase(new Move(player, source,
						destination));
			}
			break;

		case LudoStaticValues.BLUEPLAYER:
			if (getDiceResult() == 1) {
				destination = HelpMethodsFinaMedKnuff
						.getBoardLocationFromCoordinate(
								LudoStaticValues.BLUESTART, state.getBoard());
				result = isValidMoveFromBase(new Move(player, source,
						destination));
			} else if (getDiceResult() == 6) {
				destination = HelpMethodsFinaMedKnuff
						.getBoardLocationFromCoordinate(
								LudoStaticValues.BLUESTARTSIXES,
								state.getBoard());
				result = isValidMoveFromBase(new Move(player, source,
						destination));
			}
			break;

		case LudoStaticValues.YELLOWPLAYER:
			if (getDiceResult() == 1) {
				destination = HelpMethodsFinaMedKnuff
						.getBoardLocationFromCoordinate(
								LudoStaticValues.YELLOWSTART, state.getBoard());
				result = isValidMoveFromBase(new Move(player, source,
						destination));
			} else if (getDiceResult() == 6) {
				destination = HelpMethodsFinaMedKnuff
						.getBoardLocationFromCoordinate(
								LudoStaticValues.YELLOWSTARTSIXES,
								state.getBoard());
				result = isValidMoveFromBase(new Move(player, source,
						destination));
			}
			break;
		case LudoStaticValues.GREENPLAYER:
			if (getDiceResult() == 1) {
				destination = HelpMethodsFinaMedKnuff
						.getBoardLocationFromCoordinate(
								LudoStaticValues.GREENSTART, state.getBoard());
				result = isValidMoveFromBase(new Move(player, source,
						destination));
			} else if (getDiceResult() == 6) {
				destination = HelpMethodsFinaMedKnuff
						.getBoardLocationFromCoordinate(
								LudoStaticValues.GREENSTARTSIXES,
								state.getBoard());
				result = isValidMoveFromBase(new Move(player, source,
						destination));
			}
			break;

		default:
			result = LudoMoveResult.MOVE_NULLOBJECT;
		}

		return result;
	}

	public boolean checkIfPieceInbase(Player player, BoardLocation source) {
		if (player.getName().equals(LudoStaticValues.REDPLAYER))
			return LudoStaticValues.REDHOME.contains(source.getId());
		else if (player.getName().equals(LudoStaticValues.BLUEPLAYER))
			return LudoStaticValues.BLUEHOME.contains(source.getId());
		else if (player.getName().equals(LudoStaticValues.YELLOWPLAYER))
			return LudoStaticValues.YELLOWHOME.contains(source.getId());
		else
			return LudoStaticValues.GREENHOME.contains(source.getId());
	}

	public LudoMoveResult isValidMoveFromBase(Move move) {

		return isDestinationPlayersStartPosition(move);
	}

	private LudoMoveResult isDestinationPlayersStartPosition(Move move) {
		if (move.getPlayer().getName().equals(LudoStaticValues.REDPLAYER)) {
			return checkValidMoveFromStartForPlayer(move,
					LudoStaticValues.REDSTART, LudoStaticValues.REDSTARTSIXES,
					LudoStaticValues.REDHOME);
		}

		else if (move.getPlayer().getName().equals(LudoStaticValues.BLUEPLAYER)) {
			return checkValidMoveFromStartForPlayer(move,
					LudoStaticValues.BLUESTART,
					LudoStaticValues.BLUESTARTSIXES, LudoStaticValues.BLUEHOME);
		}

		else if (move.getPlayer().getName().equals(LudoStaticValues.YELLOWPLAYER)) {
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
			if (move.getDestination().getId().equals(start)) {
				if (destinationDoesNotAlreadyContainTwoPieces(move))
					return LudoMoveResult.MOVE_VALID;
				else
					return LudoMoveResult.MOVE_NO_MOVES_AVAILABLE;
			}

			else
				return LudoMoveResult.MOVE_INCORRECTNUMBEROFSTEPS;
		}

		else if (getDiceResult() == 6) {
			if (move.getDestination().getId().equals(startSix)) {
				if (destinationDoesNotAlreadyContainTwoPieces(move))
					return LudoMoveResult.MOVE_VALID;
			} else if (move.getDestination().getId().equals(start)) {
				if (destinationDoesNotAlreadyContainTwoPieces(move)) {
					System.out.println("Does not contain two pieces");
					for (String basePositions : homeValues) {
						BoardLocation home = HelpMethodsFinaMedKnuff
								.getBoardLocationFromCoordinate(basePositions,
										state.getBoard());
						if (home.getPiece() != null
								&& home.getPiece() != move.getSource()
										.getPiece()) {
							if (checkIfDestinationIsEmptyOrContainsOpponentsPieces(
									move.getPlayer(), move.getDestination()))
								return LudoMoveResult.MOVE_VALID_INBASE_TWO_PIECES;
							else
								return LudoMoveResult.MOVE_INVALIDA_BOARDLOCATION_ALREADY_OCCUPIED;
						}
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

	private boolean checkIfDestinationIsEmptyOrContainsOpponentsPieces(
			Player player, BoardLocation destination) {
		if (destination.getPieces().isEmpty())
			return true;
		else
			return !player.getPieces().contains(destination.getPiece());

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
