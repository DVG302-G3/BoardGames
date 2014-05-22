package boardgames.g3.core.Ludo;

import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Move;
import game.impl.Player;

import java.util.List;

import boardgames.g3.core.Ludo.StrategyMove.MoveIncorrectStepsImplementation;
import boardgames.g3.core.Ludo.StrategyMove.MoveInvalidBaseCantMoveOut;
import boardgames.g3.core.Ludo.StrategyMove.MoveInvalidBoardLocationAlreadyOccupied;
import boardgames.g3.core.Ludo.StrategyMove.MoveNoMovesAvailableImplementation;
import boardgames.g3.core.Ludo.StrategyMove.MoveStrategy;
import boardgames.g3.core.Ludo.StrategyMove.MoveValidImplementation;
import boardgames.g3.core.Ludo.StrategyMove.MoveValidInbaseTwoPiecesImplementation;

public class BaseController {
	LudoGameState state;
	LudoRuleController ruler;
	
	public BaseController(LudoGameState state, LudoRuleController ruler) {
		this.state = state;
		this.ruler = ruler;
	}

	public boolean checkIfPieceInbase(Move move) {
		LudoPlayer player = state.getLudoPlayerFromPlayer(move.getPlayer());
		return checkIfPieceInbase(player, move.getSource());
	}

	public boolean canPlayerMakeAMoveFromBase(LudoPlayer player) {

		MoveStrategy result;
		for (GamePiece gp : player.getPlayerObject().getPieces()) {
			BoardLocation source = HelpMethodsFinaMedKnuff
					.getBoardLocationFromPiece(gp, state.getBoard());

			if (source == null)
				continue;

			if (checkIfPieceInbase(player, source)) {
				result = checkPlayerColorAndGetValidMoveForPiece(player, gp,
						source);
				if (resultIsValidMove(result)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean resultIsValidMove(MoveStrategy result) {
		return result instanceof MoveValidImplementation
				|| result instanceof MoveValidInbaseTwoPiecesImplementation;
	}

	private MoveStrategy checkPlayerColorAndGetValidMoveForPiece(
			LudoPlayer player, GamePiece gp, BoardLocation source) {

		MoveStrategy result = null;

		BoardLocation destination;

		switch (getDiceResult()) {
		case 1:
			destination = HelpMethodsFinaMedKnuff
					.getBoardLocationFromCoordinate(
							player.getStartCoordinateOne(), state.getBoard());
			result = checkValidMoveFromBase(new Move(player.getPlayerObject(),
					source, destination));
			break;
		case 6:
			destination = HelpMethodsFinaMedKnuff
					.getBoardLocationFromCoordinate(
							player.getStartCoordinateSix(), state.getBoard());
			result = checkValidMoveFromBase(new Move(player.getPlayerObject(),
					source, destination));
			break;

		default:
			return null;
		}

		return result;
	}

	public boolean checkIfPieceInbase(LudoPlayer player, BoardLocation source) {
		return player.getHomePositions().contains(source.getId());
	}

	public MoveStrategy checkValidMoveFromBase(Move move) {
		LudoPlayer player = state.getLudoPlayerFromPlayer(move.getPlayer());
		String start = player.getStartCoordinateOne();
		String startSix = player.getStartCoordinateSix();

		switch (getDiceResult()) {
		case 1:
			if (move.getDestination().getId().equals(start)) {
				if (destinationDoesNotAlreadyContainTwoPieces(move)) {
					return new MoveValidImplementation(LudoStaticValues.GOAL,
							ruler);
				} else {
					return new MoveNoMovesAvailableImplementation();
				}
			}

			else
				return new MoveIncorrectStepsImplementation();

		case 6:
			if (move.getDestination().getId().equals(startSix)) {
				if (destinationDoesNotAlreadyContainTwoPieces(move))
					return new MoveValidImplementation(LudoStaticValues.GOAL,
							ruler);
			} else if (move.getDestination().getId().equals(start)) {
				if (destinationDoesNotAlreadyContainTwoPieces(move)) {
					for (String basePositions : player.getHomePositions()) {
						BoardLocation home = HelpMethodsFinaMedKnuff
								.getBoardLocationFromCoordinate(basePositions,
										state.getBoard());
						if (home.getPiece() != null
								&& home.getPiece() != move.getSource()
										.getPiece()) {
							if (checkIfDestinationIsEmptyOrContainsOpponentsPieces(
									move.getPlayer(), move.getDestination()))
								return new MoveValidInbaseTwoPiecesImplementation(
										ruler);
							else
								return new MoveInvalidBoardLocationAlreadyOccupied();
						}
					}
				}

				return new MoveInvalidBaseCantMoveOut();
			}
		default:
			if (HelpMethodsFinaMedKnuff.doesPlayerHaveAnyPiecesOnTheBoard(
					move.getPlayer(), state.getBoard()))
				return new MoveIncorrectStepsImplementation();
			else
				return new MoveInvalidBaseCantMoveOut();

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

	public void putInBase(List<String> home, GamePiece pieceToPush) {
		for (String homeCoordinate : home) {
			BoardLocation homeLocation = HelpMethodsFinaMedKnuff
					.getBoardLocationFromCoordinate(homeCoordinate,
							state.getBoard());
			if (homeLocation.getPiece() == null) {
				homeLocation.setPiece(pieceToPush);
				break;
			}
		}
	}

	
	private int getDiceResult() {
		return state.getDieRollFactory().getLastRoll().getResult();
	}

}
