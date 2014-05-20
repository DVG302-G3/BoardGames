package boardgames.g3.core.Ludo;

import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Move;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoveLapsedExecutor {

	LudoGameState gameState;

	public MoveLapsedExecutor(LudoGameState gameState) {
		this.gameState = gameState;
	}

	public boolean checkIfPieceCanMakeAMove(LudoPlayer player,
			BoardLocation source, int remainingSteps) {
		if (isPiecesInFinishline(player, source)) {
			int sourceIndex = player.getFinishLine().indexOf(source);
			int destinationIndex = sourceIndex + remainingSteps;
			if (withinRangeOfFinishLine(player, remainingSteps, sourceIndex)) {
				BoardLocation destination = getDestinationFromPlayersFinishLine(
						player, destinationIndex);
				if (isDestinationAlreadyOccupied(destination))
					if (isPieceLaping(player.getFinishLine(), sourceIndex,
							destinationIndex))
						return true;
			}
			if(destinationIndexIsGoal(player, destinationIndex))
				return true;

		} else {
			int dice = gameState.getDieRollFactory().getLastRoll().getResult();
			int stepsIntoFinishLine = dice - remainingSteps;
			BoardLocation destination;
			if (withinRangeOfFinishLine(player, stepsIntoFinishLine)) {
				destination = getDestinationFromPlayersFinishLine(player,
						stepsIntoFinishLine);
				if (checkIfDstinationIsFinishLine(player, destination,
						remainingSteps)) {
					return true;
				}
				
				if(destinationIndexIsGoal(player, stepsIntoFinishLine))
					return true;
			}
		}

		return false;

	}

	private boolean destinationIndexIsGoal(LudoPlayer player,
			int destinationIndex) {
		return destinationIndex == player.getFinishLine().size();
	}

	private BoardLocation getDestinationFromPlayersFinishLine(
			LudoPlayer player, int stepsIntoFinishLine) {
		return HelpMethodsFinaMedKnuff
				.getBoardLocationFromCoordinate(
						player.getFinishLine().get(stepsIntoFinishLine),
						gameState.getBoard());
	}

	private boolean withinRangeOfFinishLine(LudoPlayer player,
			int stepsIntoFinishLine) {
		return stepsIntoFinishLine > 0
				&& stepsIntoFinishLine < player.getFinishLine().size();
	}

	private boolean isPieceLaping(List<String> homeLocations, int source,
			int destination) {
		for (int i = source; i < destination; i++) {
			System.out.println(source);
			System.out.println(destination);
			BoardLocation location = HelpMethodsFinaMedKnuff
					.getBoardLocationFromCoordinate(homeLocations.get(i),
							gameState.getBoard());
			if (location.getPiece() != null)
				return true;

		}
		return false;
	}

	private boolean isDestinationAlreadyOccupied(BoardLocation destination) {
		return destination.getPieces().size() < 2;
	}

	private boolean withinRangeOfFinishLine(LudoPlayer player,
			int remainingSteps, int sourceIndex) {
		return ((sourceIndex + remainingSteps) < player.getFinishLine().size() && sourceIndex >= 0);
	}

	public boolean controlAndExecuteMove(Move move, int remainingSteps) {
		LudoPlayer player = gameState.getLudoPlayerFromPlayer(move.getPlayer());
		if (isPiecesInFinishline(player, move.getSource())) {
			if (checkIfValidFinishLineMove(move))
				if (!isDestinationAlreadyOccupied(move.getDestination()))
					executeFinishLineMove(move, player);
		} else {
			if (checkIfDstinationIsFinishLine(player, move.getDestination(),
					remainingSteps)) {
				move.execute();
				return true;
			}

			if (checkIfDestinationIsGoal(move.getDestination())) {
				executeMove(move);
			}
		}

		return false;
	}

	private void executeMove(Move move) {
		move.execute();
		LudoPlayer player = gameState.getLudoPlayerFromPlayer(move.getPlayer());
		player.aPieceJustEnteredGoal();
		move.getDestination().clear();
	}

	private boolean checkIfDestinationIsGoal(BoardLocation destination) {
		if (destination.getId().equals(LudoStaticValues.GOAL))
			return true;
		else
			return false;
	}

	private boolean checkIfDstinationIsFinishLine(LudoPlayer player,
			BoardLocation destination, int stepsRemaining) {
		if (stepsRemaining == 0)
			return true;

		if (withinRangeOfFinishLine(player, stepsRemaining)) {
			if (player.getFinishLine().get(stepsRemaining - 1)
					.equals(destination.getId()))
				return true;
		}

		return false;

	}

	private boolean checkIfValidFinishLineMove(Move move) {
		LudoPlayer player = gameState.getLudoPlayerFromPlayer(move.getPlayer());
		ArrayList<String> finishLine = new ArrayList<String>(
				player.getFinishLine());

		finishLine.add(LudoStaticValues.GOAL);

		BoardLocation destination = move.getDestination();
		BoardLocation source = move.getSource();

		int sourceIndex = finishLine.indexOf(source.getId());
		int destinationIndex = finishLine.indexOf(destination.getId());
		int dice = gameState.getDieRollFactory().getLastRoll().getResult();

		if (destinationIndex - sourceIndex == dice)
			return true;
		return false;

	}

	private void executeFinishLineMove(Move move, LudoPlayer player) {
		move.execute();

		if (move.getDestination().getId().equals(LudoStaticValues.GOAL)) {
			move.getDestination().clear();
			if (player.aPieceJustEnteredGoal() == 0)
				gameState.removePlayer(player);
		}
	}

	private boolean isPiecesInFinishline(LudoPlayer player, BoardLocation source) {
		return player.getFinishLine().contains(source.getId());
	}

	public void executeAndMakeSureThatNoPieceWillBeDeleted(Move move) {
		GamePiece sourcePiece = checkSourceForDoublePieces(move);
		GamePiece destinationPiece = checkDestinationForExistingPieces(move);

		move.execute();
		if (destinationPiece != null)
			move.getDestination().addPiece(destinationPiece);

		if (sourcePiece != null)
			move.getSource().addPiece(sourcePiece);

	}

	private GamePiece checkSourceForDoublePieces(Move move) {
		move.getSource().getPieces().size();
		if (move.getSource().getPieces().size() > 1) {
			return move.getSource().getPieces().get(1);
		}
		return null;
	}

	private GamePiece checkDestinationForExistingPieces(Move move) {
		return move.getDestination().getPiece();
	}

}
