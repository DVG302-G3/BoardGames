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
	GameState state;

	public LudoRuleController(GameState state) {
		this.state = state;
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

	public LudoMoveResult isValidMove(Move move) {
		BoardLocation source = move.getSource();

		if (!HelpMethodsFinaMedKnuff.doesPlayerHaveAnyPiecesOnTheBoard(
				move.getPlayer(), state.getBoard())) {
			if (!checkIfDiceIsSIXorONE()) {
				return LudoMoveResult.MOVE_IN_BASE_DID_NOT_GET_THE_CORRECT_EYES_ON_THE_DICE_TO_MOVE_OUT;
			}
		}

		if (source.getPiece() == null)
			return LudoMoveResult.MOVE_NOGAMEPIECE;

		if (pieceInBase(move))
			return isValidMoveFromBase(move);

		if (shoulPiecedMoveIntoGoalLine(move))
			return LudoMoveResult.MOVE_LAPSED;

		if (checkIfPlayerStepsIsNotCorrect(move))
			return LudoMoveResult.MOVE_INCORRECTNUMBEROFSTEPS;

		if (checkIfPlayerIsTryingToLapseHisOwnPiece(move))
			return LudoMoveResult.MOVE_INVALID_CANT_LAPSE_YOUR_OWN_PIECE;

		else {
			addStepsToCounter(move);
			return LudoMoveResult.MOVE_VALID;
		}

	}

	private boolean checkIfPlayerIsTryingToLapseHisOwnPiece(Move move) {
		
		for(int i = 1 ; i<getNumberOfStepsFromDice();i++){
			int index = (state.getBoard().getLocations().indexOf(move.getSource()) + i) % LudoStaticValues.TOTALSTEPSAROUNDTHEBOARD;
			System.out.println("Modulerad: "+index);

			if(move.getPlayer().hasPiece(state.getBoard().getLocations().get(index).getPiece()))
					return true;
		}
		return false;
	}

	private boolean checkIfDiceIsSIXorONE() {
		int dice = state.getDieRollFactory().getLastRoll().getResult();
		if (dice == 6 || dice == 1)
			return true;
		else
			return false;
	}

	private LudoMoveResult isValidMoveFromBase(Move move) {

		return isDestinationPlayersStartPosition(move);
	}

	private void addStepsToCounter(Move move) {
		stepCounter.put(move.getSource().getPiece().getId(),
				getTotalStepsForPiece(move));
	}

	// private LudoMoveResult movePieceInToGoalLine(Move move){
	// if(shoulPiecedMoveIntoGoalLine(move))
	// return LudoMoveResult.MOVE_LAPSED;
	//
	// }

	private boolean shoulPiecedMoveIntoGoalLine(Move move) {
		if (getTotalStepsForPiece(move) > LudoStaticValues.TOTALSTEPSAROUNDTHEBOARD - 1) {
			return true;
		} else
			return false;

	}

	// private LudoStaticValues

	public Boolean isPieceInGoal(Move move, GamePiece gamePiece) {
		for (GamePiece gp : move.getPieces()) {
			if (gp.getId() == gamePiece.getId()) {
				return true;
			}
		}
		return false;

	}

	// private LudoMoveResult movePieceInGoal(Move move){
	// if(isPiecesInFinishline(move) == true && getNumberOfStepsFromDice() <=4){
	// return LudoMoveResult.MOVE_PIECE_IN_TO_GOAL;
	// }else
	// return LudoMoveResult.MOVE_PIECE_NOT_IN_TO_GOAL;
	// }

	private int getTotalStepsForPiece(Move move) {
		return stepCounter.get(move.getSource().getPiece().getId())
				+ stepsPlayerMoves(move);
	}

	private boolean checkIfPlayerStepsIsNotCorrect(Move move) {
		System.out.println("Moves: " + stepsPlayerMoves(move));
		System.out.println("Dice : " + getNumberOfStepsFromDice());
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

	private boolean pieceInBase(Move move) {
		if (move.getPlayer().getName().equals("Red"))
			return existInList(move.getSource().getId(),
					LudoStaticValues.REDHOME);
		else if (move.getPlayer().getName().equals("Blue"))
			return existInList(move.getSource().getId(),
					LudoStaticValues.BLUEHOME);

		else if (move.getPlayer().getName().equals("Yellow"))
			return existInList(move.getSource().getId(),
					LudoStaticValues.YELLOWHOME);
		else
			return existInList(move.getSource().getId(),
					LudoStaticValues.GREENHOME);

	}

	private boolean existInList(String id, List<String> list) {
		for (String coordinate : list) {
			if (coordinate.equals(id))
				return true;
		}

		return false;
	}

	public Boolean isPiecesInFinishline(Move move) {
		if (move.getPlayer().getName().equals("Red"))
			return existInList(move.getSource().getId(),
					LudoStaticValues.REDFINISHLINE);
		else if (move.getPlayer().getName().equals("Blue"))
			return existInList(move.getSource().getId(),
					LudoStaticValues.BLUEFINISHLINE);

		else if (move.getPlayer().getName().equals("Yellow"))
			return existInList(move.getSource().getId(),
					LudoStaticValues.YELLOWFINISHLINE);
		else
			return existInList(move.getSource().getId(),
					LudoStaticValues.GREENFINISHLINE);
	}

	public Boolean isGameFinished(GameState state) {
		if (state.hasEnded() == true) {
			return true;
		} else
			return false;
	}

	public void pushOtherPiece(BoardLocation destination) {
		GamePiece piece = destination.getPiece();
		String name = getPlayerName(piece);

		if (name.equals("Red")) {
			putInBase(LudoStaticValues.REDHOME, piece);
		}

		else if (name.equals("Blue")) {
			putInBase(LudoStaticValues.BLUEHOME, piece);
		} else if (name.equals("Yellow")) {
			putInBase(LudoStaticValues.YELLOWHOME, piece);
		}

		else {
			putInBase(LudoStaticValues.GREENHOME, piece);
		}

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

	private String getPlayerName(GamePiece pieceToPush) {
		for (Player p : state.getPlayers()) {
			if (p.getPieces().contains(pieceToPush)) {
				return p.getName();
			}
		}
		return "";

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
		if (getNumberOfStepsFromDice() == 1) {
			if (move.getDestination().getId().equals(start)
					&& destinationDoesNotAlreadyContainTwoPieces(move)) {
				return LudoMoveResult.MOVE_VALID;
			} else
				return LudoMoveResult.MOVE_INCORRECTNUMBEROFSTEPS;
		}

		else if (getNumberOfStepsFromDice() == 6
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

	private int getNumberOfStepsFromDice() {
		return state.getDieRollFactory().getLastRoll().getResult();
	}

}
