package boardgames.g3.core.Ludo;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Move;
import game.impl.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LudoRuleController {

	Map<String, Integer> stepCounter;
	LudoGameState state;
	BaseController baseController;

	public LudoRuleController(LudoGameState state) {
		this.state = state;
		baseController = new BaseController(state);
		initiateStepCounterMap();

	}

	private void initiateStepCounterMap() {
		stepCounter = new HashMap<String, Integer>();
		for (Player p : state.getPlayers()) {
			for (GamePiece gp : p.getPieces()) {
				stepCounter.put(gp.getId(), 0);
			}
		}
	}

	public LudoMoveResult checkAndReturnValidMoves(Move move) {
		BoardLocation source = move.getSource();

		if (source.getPiece() == null)
			return LudoMoveResult.MOVE_NOGAMEPIECE;

		if (baseController.checkIfPieceInbase(move))
			return baseController.checkValidMoveFromBase(move);

		if (checkIfPlayerStepsIsNotCorrect(move))
			return LudoMoveResult.MOVE_INCORRECTNUMBEROFSTEPS;

		if (playerIsTryingToLapseHisOwnPiece(move, getNumberOfStepsFromDice()))
			return LudoMoveResult.MOVE_INVALID_CANT_LAPSE_YOUR_OWN_PIECE;

		if (playerIsTryingToLapseAblock(move, getNumberOfStepsFromDice()))
			return LudoMoveResult.MOVE_INVALID_CANT_PASS_A_BLOCK;

		if (destinationIsAlreadyOccupado(move)) {
			return LudoMoveResult.MOVE_INVALIDA_BOARDLOCATION_ALREADY_OCCUPIED;
		}

		else {
			return LudoMoveResult.MOVE_VALID;
		}

	}

	public LudoMoveResult evaluateMove(Move move) {

		if (!HelpMethodsFinaMedKnuff.doesPlayerHaveAnyPiecesOnTheBoard(
				move.getPlayer(), state.getBoard())) {
			if (!checkIfDiceIsSIXorONE()) {
				return LudoMoveResult.MOVE_IN_BASE_DID_NOT_GET_THE_CORRECT_EYES_ON_THE_DICE_TO_MOVE_OUT;
			}
		}

		if (playerCantMakeAMove(move)) {
			return LudoMoveResult.MOVE_NO_MOVES_AVAILABLE;
		}

		return checkAndReturnValidMoves(move);

	}

	private boolean playerCantMakeAMove(Move move) {
		List<GamePiece> playersPieces = move.getPlayer().getPieces();
		LudoPlayer player = state.getLudoPlayerFromPlayer(move.getPlayer());

		if (baseController.canPlayerMakeAMoveFromBase(player)) {
			return false;
		}

		if (canAnyPieceMakeAMoveOnouterBoardArea(playersPieces, player)) {
			return false;
		}

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

		Move move = new Move(player.getPlayerObject(), source, destination);
		if (checkIfValidResult(move))
			return true;

		return false;
	}

	private boolean checkIfValidResult(Move move) {
		return checkLudoMoveResultsForValidMove(move);
	}

	private boolean checkLudoMoveResultsForValidMove(Move move) {
		return checkAndReturnValidMoves(move) == LudoMoveResult.MOVE_VALID
				|| checkAndReturnValidMoves(move) == LudoMoveResult.MOVE_VALID_INBASE_ONE
				|| checkAndReturnValidMoves(move) == LudoMoveResult.MOVE_VALID_INBASE_SIX;
	}

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

	public void setStepsForPiece(GamePiece gamePiece, int steps) {
		stepCounter.put(gamePiece.getId(), steps);
	}

	public void addStepsToCounter(Move move) {
		stepCounter.put(move.getSource().getPiece().getId(),
				getTotalStepsForPiece(move));
	}

	private int getTotalStepsForPiece(Move move) {
		return stepCounter.get(move.getSource().getPiece().getId())
				+ stepsPlayerMoves(move);
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
