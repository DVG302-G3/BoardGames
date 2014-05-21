package boardgames.g3.core.Ludo;

import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Move;
import game.impl.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import boardgames.g3.core.Ludo.StrategyMove.MoveIncorrectStepsImplementation;
import boardgames.g3.core.Ludo.StrategyMove.MoveInvalidBaseCantMoveOut;
import boardgames.g3.core.Ludo.StrategyMove.MoveInvalidBlockImplementation;
import boardgames.g3.core.Ludo.StrategyMove.MoveInvalidBoardLocationAlreadyOccupied;
import boardgames.g3.core.Ludo.StrategyMove.MoveLapseOwnPieceImplementation;
import boardgames.g3.core.Ludo.StrategyMove.MoveNoGamePieceImplementation;
import boardgames.g3.core.Ludo.StrategyMove.MoveNoMovesAvailableImplementation;
import boardgames.g3.core.Ludo.StrategyMove.MoveStrategy;
import boardgames.g3.core.Ludo.StrategyMove.MoveValidImplementation;

public class LudoRuleController {

	LudoGameState state;
	BaseController baseController;

	public LudoRuleController(LudoGameState state) {
		this.state = state;
		baseController = new BaseController(state, this);
	}

	public MoveStrategy checkAndReturnValidMoves(Move move) {
		BoardLocation source = move.getSource();

		if (source.getPiece() == null)
			return new MoveNoGamePieceImplementation();

		if (baseController.checkIfPieceInbase(move))
			return baseController.checkValidMoveFromBase(move);

		if (checkIfPlayerStepsIsNotCorrect(move))
			return new MoveIncorrectStepsImplementation();

		if (playerIsTryingToLapseHisOwnPiece(move, getNumberOfStepsFromDice()))
			return new MoveLapseOwnPieceImplementation();

		if (playerIsTryingToLapseAblock(move, getNumberOfStepsFromDice()))
			return new MoveInvalidBlockImplementation();

		if (destinationIsAlreadyOccupado(move)) {
			return new MoveInvalidBoardLocationAlreadyOccupied();
		}

		else {
			return new MoveValidImplementation(LudoStaticValues.GOAL, this);
		}

	}

	public MoveStrategy evaluateMove(Move move) {

		if (!HelpMethodsFinaMedKnuff.doesPlayerHaveAnyPiecesOnTheBoard(
				move.getPlayer(), state.getBoard())) {
			if (!checkIfDiceIsSIXorONE()) {
				return new MoveInvalidBaseCantMoveOut();
			}
		}

		if (playerCantMakeAMove(move)) 
					return new MoveNoMovesAvailableImplementation();
		

		return checkAndReturnValidMoves(move);

	}

	private boolean playerCantMakeAMove(Move move) {
		List<GamePiece> playersPieces = move.getPlayer().getPieces();
		LudoPlayer player = state.getLudoPlayerFromPlayer(move.getPlayer());

		if (baseController.canPlayerMakeAMoveFromBase(player)) {
			return false;
		}

		if(!HelpMethodsFinaMedKnuff.doesPlayerHaveAnyPiecesOnTheBoard(player.getPlayerObject(), state.getBoard()))
			if (canAnyPieceMakeAMoveOnouterBoardArea(playersPieces, player))
				return false;
		

		return true;
	}

	private boolean canAnyPieceMakeAMoveOnouterBoardArea(
			List<GamePiece> playersPieces, LudoPlayer player) {
		for (GamePiece piece : playersPieces)
			if (canPieceMove(piece, player))
				return true;
		return false;
	}

	private boolean canPieceMove(GamePiece piece, LudoPlayer player) {
		BoardLocation source = HelpMethodsFinaMedKnuff
				.getBoardLocationFromPiece(piece, state.getBoard());
		BoardLocation destination;

		List<BoardLocation> playerListOfBoardLocations = player.getBoardList();
		int sourceIndex = playerListOfBoardLocations.indexOf(source);
		int destinationIndex = sourceIndex + getNumberOfStepsFromDice();
		if (destinationIndex > playerListOfBoardLocations.size())
			return false;

		destination = playerListOfBoardLocations.get(destinationIndex);
		if(source == null || destination == null)
			return false;

		Move move = new Move(player.getPlayerObject(), source, destination);
		if (checkIfValidResult(move))
			return true;

		return false;
	}

	private boolean checkIfValidResult(Move move) {
		return checkLudoMoveResultsForValidMove(move);
	}

	private boolean checkLudoMoveResultsForValidMove(Move move) {
		return checkAndReturnValidMoves(move) instanceof MoveValidImplementation;}

	private boolean destinationIsAlreadyOccupado(Move move) {
		if (move.getDestination().getPieces().size() > 1)
			return true;
		else
			return false;
	}

	private boolean playerIsTryingToLapseAblock(Move move, int dice) {
		for (int i = 1; i < dice; i++) {
			int index = (state.getBoard().getLocations()
					.indexOf(move.getSource()) + i)
					% LudoStaticValues.TOTALSTEPSAROUNDTHEBOARD;

			if (state.getBoard().getLocations().get(index).getPieces().size() > 1)
				return true;
		}
		return false;
	}

	private boolean playerIsTryingToLapseHisOwnPiece(Move move, int dice) {

		for (int i = 1; i < dice; i++) {
			int index = modulateAndReturnTheIndex(move.getSource(), i);

			if (move.getPlayer().hasPiece(
					state.getBoard().getLocations().get(index).getPiece()))
				return true;
		}
		return false;
	}

	private int modulateAndReturnTheIndex(BoardLocation source, int index) {
		int newIndex = (state.getBoard().getLocations().indexOf(source) + index)
				% LudoStaticValues.TOTALSTEPSAROUNDTHEBOARD;
		return newIndex;
	}

	private boolean checkIfDiceIsSIXorONE() {
		int dice = state.getDieRollFactory().getLastRoll().getResult();
		if (dice == 6 || dice == 1)
			return true;
		else
			return false;
	}

	private boolean checkIfPlayerStepsIsNotCorrect(Move move) {
		return stepsPlayerMoves(move) != getNumberOfStepsFromDice();
	}

	private int stepsPlayerMoves(Move move) {
		LudoPlayer player = state.getLudoPlayerFromPlayer(move.getPlayer());
		List<BoardLocation> playersList = player.getBoardList();

		int destinationValue = playersList.indexOf(move.getDestination());
		int sourceValue = playersList.indexOf(move.getSource());
		int stepsPlayerMoves = destinationValue - sourceValue;

		return stepsPlayerMoves;
	}

	public void pushOtherPiece(BoardLocation destination) {
		GamePiece piece = destination.getPiece();
		LudoPlayer player = getPlayerFromPiece(piece);

		putInBase(player.getHomePositions(), piece);
		destination.clear();
	}

	private void putInBase(List<String> home, GamePiece pieceToPush) {
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

	private LudoPlayer getPlayerFromPiece(GamePiece pieceToPush) {
		for (Player p : state.getPlayers()) {
			if (p.getPieces().contains(pieceToPush)) {
				return state.getLudoPlayerFromPlayer(p);
			}
		}
		return null;
	}

	public boolean needToPush(Move move) {
		GamePiece destinationPiece = move.getDestination().getPiece();

		if (move.getPlayer().hasPiece(destinationPiece))
			return false;
		else
			return move.getDestination().getPiece() != null;
	}

	private int getNumberOfStepsFromDice() {
		return state.getDieRollFactory().getLastRoll().getResult();
	}

}
