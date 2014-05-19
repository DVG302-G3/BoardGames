package boardgames.g3.core.Ludo;

import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Move;
import game.impl.Player;

import java.util.List;

public class BaseController {
	LudoGameState state;

	public BaseController(LudoGameState state) {
		this.state = state;
	}

	public boolean checkIfPieceInbase(Move move) {
		LudoPlayer player = state.getLudoPlayerFromPlayer(move.getPlayer());
		return checkIfPieceInbase(player, move.getSource());
	}

	public boolean canPlayerMakeAMoveFromBase(LudoPlayer player) {

		LudoMoveResult result;
		for (GamePiece gp : player.getPlayerObject().getPieces()) {
			BoardLocation source = HelpMethodsFinaMedKnuff
					.getBoardLocationFromPiece(gp, state.getBoard());

			if (checkIfPieceInbase(player, source)) {
				result = checkPlayerColorAndGetValidMoveForPiece(player, gp,
						source);
				if (result == LudoMoveResult.MOVE_VALID
						|| result == LudoMoveResult.MOVE_VALID_INBASE_TWO_PIECES) {
					return true;
				}
			}
		}

		return false;
	}

	private LudoMoveResult checkPlayerColorAndGetValidMoveForPiece(
			LudoPlayer player, GamePiece gp, BoardLocation source) {

		LudoMoveResult result = LudoMoveResult.MOVE_NULLOBJECT;

		BoardLocation destination;

		switch (getDiceResult()) {
		case 1:
			destination = HelpMethodsFinaMedKnuff
					.getBoardLocationFromCoordinate(
							player.getStartCoordinateOne(), state.getBoard());
			result = checkValidMoveFromBase(new Move(
					player.getPlayerObject(), source, destination));
			break;
		case 6:
			destination = HelpMethodsFinaMedKnuff
					.getBoardLocationFromCoordinate(
							player.getStartCoordinateSix(), state.getBoard());
			result = checkValidMoveFromBase(new Move(
					player.getPlayerObject(), source, destination));
			break;

		default:
			return LudoMoveResult.MOVE_NULLOBJECT;
		}

		return result;
	}

	public boolean checkIfPieceInbase(LudoPlayer player, BoardLocation source) {
		return player.getHomePositions().contains(source.getId());
	}

	public LudoMoveResult checkValidMoveFromBase(Move move) {
		LudoPlayer player = state.getLudoPlayerFromPlayer(move.getPlayer());
		String start = player.getStartCoordinateOne();
		String startSix = player.getStartCoordinateSix();

		switch (getDiceResult()) {
		case 1:
			if (move.getDestination().getId().equals(start)) {
				if (destinationDoesNotAlreadyContainTwoPieces(move))
					return LudoMoveResult.MOVE_VALID;
				else
					return LudoMoveResult.MOVE_NO_MOVES_AVAILABLE;
			}

			else
				return LudoMoveResult.MOVE_INCORRECTNUMBEROFSTEPS;

		case 6:
			if (move.getDestination().getId().equals(startSix)) {
				if (destinationDoesNotAlreadyContainTwoPieces(move))
					return LudoMoveResult.MOVE_VALID;
			} else if (move.getDestination().getId().equals(start)) {
				if (destinationDoesNotAlreadyContainTwoPieces(move)) {
					System.out.println("Does not contain two pieces");
					for (String basePositions : player.getHomePositions()) {
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
		default:
			if (HelpMethodsFinaMedKnuff.doesPlayerHaveAnyPiecesOnTheBoard(
					move.getPlayer(), state.getBoard()))
				return LudoMoveResult.MOVE_INCORRECTNUMBEROFSTEPS;
			else
				return LudoMoveResult.MOVE_IN_BASE_DID_NOT_GET_THE_CORRECT_EYES_ON_THE_DICE_TO_MOVE_OUT;

		}
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
