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

		if (hasPieceLapsedAndAreInRigtFinishline(move))
			return LudoMoveResult.MOVE_LAPSED;
		if (hasPiecePassedBefore(move))
			return LudoMoveResult.MOVE_INVALID_CANT_LAPSE_AGAIN;

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

	public LudoMoveResult isValidMove(Move move) {

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
		if (canAnyPieceMakeAMove(playersPieces, move.getPlayer())) {
			return false;
		}

		return true;
	}

	private boolean canAnyPieceMakeAMove(List<GamePiece> playersPieces,
			Player player) {
		for (GamePiece piece : playersPieces)
			if (canPieceMove(piece, player))
				return true;
		return false;
	}

	private boolean canPieceMove(GamePiece piece, Player player) {
		BoardLocation source = HelpMethodsFinaMedKnuff
				.getBoardLocationFromPiece(piece, state.getBoard());
		BoardLocation destination;
		int maxEyesOnTheDice = 6;
		for (int i = 1; i < maxEyesOnTheDice; i++) {
			int newIndex = modulateAndReturnTheIndex(source, i);
			destination = state.getBoard().getLocations().get(newIndex);
			Move move = new Move(player, source, destination);
			if (checkIfValidResult(move))
				return true;
		}
		return false;
	}

	private boolean checkIfValidResult(Move move) {
		return checkAndReturnValidMoves(move) == LudoMoveResult.MOVE_VALID || checkAndReturnValidMoves(move) == LudoMoveResult.MOVE_VALID_INBASE_ONE || checkAndReturnValidMoves(move) == LudoMoveResult.MOVE_VALID_INBASE_SIX;
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
	
	public void setStepsForPiece(GamePiece gamePiece, int steps){
		stepCounter.put(gamePiece.getId(), steps);
	
		System.out.println("Total: "+stepCounter.get(gamePiece.getId()));

	}

	public void addStepsToCounter(Move move) {
		stepCounter.put(move.getSource().getPiece().getId(),
				getTotalStepsForPiece(move));
		
		System.out.println("Total: "+stepCounter.get(move.getSource().getPiece().getId()));

	}

	private boolean hasPieceLapsed(Move move) {
		if (getTotalStepsForPiece(move) >= LudoStaticValues.TOTALSTEPSAROUNDTHEBOARD - 1) {
			return true;
		} else
			return false;

	}

	private boolean hasPieceLapsedAndAreInRigtFinishline(Move move) {
		if (hasPieceLapsed(move) == true
				&& isPiecesRightCollorForThisFinishline(move) == true) {
			return true;
		} else
			return false;
	}

	private boolean hasPiecePassedBefore(Move move) {
		if (hasPieceLapsedAndAreInRigtFinishline(move) == false
				&& getTotalStepsForPiece(move) > LudoStaticValues.TOTALSTEPSAROUNDTHEBOARD - 1) {
			return true;
		} else

			return false;
	}

	public boolean checkIfPieceInGoalline(Move move) {
		LudoPlayer player = state.getLudoPlayerFromPlayer(move.getPlayer());
		return player.getFinishLine().contains(move.getSource().getId());
	}

	public Boolean isPieceInGoal(Move move, GamePiece gamePiece) {
		for (GamePiece gp : move.getPieces()) {
			if (gp.getId() == gamePiece.getId()) {
				return true;
			}
		}
		return false;

	}

	private int getTotalStepsForPiece(Move move) {
		return stepCounter.get(move.getSource().getPiece().getId())
				+ stepsPlayerMoves(move);
	}

	private boolean checkIfPlayerStepsIsNotCorrect(Move move) {
		return stepsPlayerMoves(move) != getNumberOfStepsFromDice();
	}

	private int stepsPlayerMoves(Move move) {
		int destinationValue = HelpMethodsFinaMedKnuff
				.getFlatListIndexFromCoordinate(move.getDestination().getId(),
						state.getBoard());
		int sourceValue = HelpMethodsFinaMedKnuff
				.getFlatListIndexFromCoordinate(move.getSource().getId(),
						state.getBoard());

		int stepsPlayerMoves = (destinationValue - sourceValue)
				% LudoStaticValues.TOTALSTEPSAROUNDTHEBOARD;

		if (stepsPlayerMoves < 0)
			stepsPlayerMoves = stepsPlayerMoves
					+ LudoStaticValues.TOTALSTEPSAROUNDTHEBOARD;
		return stepsPlayerMoves;
	}

	public Boolean isPiecesInFinishline(Move move) {
		LudoPlayer player = state.getLudoPlayerFromPlayer(move.getPlayer());
		return player.getFinishLine().contains(move.getSource().getId());
	}


	public Boolean isPiecesRightCollorForThisFinishline(Move move) {
		LudoPlayer player = state.getLudoPlayerFromPlayer(move.getPlayer());
		return player.getFinishLine().contains(move.getSource().getId());
	}

	public Boolean isGameFinished(GameState state) {
		if (state.hasEnded() == true) {
			return true;
		} else
			return false;
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
