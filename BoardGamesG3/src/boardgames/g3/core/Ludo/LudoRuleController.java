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

		if (source.getPiece() == null)
			return LudoMoveResult.MOVE_NOGAMEPIECE;

		if (pieceInBase(move))
			return isValidMoveFromBase(move);

		if (checkIfPlayerStepsIsNotCorrect(move))
			return LudoMoveResult.MOVE_INCORRECTNUMBEROFSTEPS;

		if (pieceShouldMoveIntoGoalLine(move))
			return LudoMoveResult.MOVE_LAPSED;

		else {
			addStepsToCounter(move);
			return LudoMoveResult.MOVE_VALID;
		}

	}

	private LudoMoveResult isValidMoveFromBase(Move move) {

		return isDestinationPlayersStartPosition(move);
	}

	private void addStepsToCounter(Move move) {
		stepCounter.put(move.getSource().getPiece().getId(),
				getTotalStepsForPiece(move));
	}

	private boolean pieceShouldMoveIntoGoalLine(Move move) {
		return getTotalStepsForPiece(move) > LudoStaticValues.TOTALSTEPSAROUNDTHEBOARD;
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
		System.out.println(move.getSource().getId());
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
	
	public Boolean isPieceInGoal(Move move, GamePiece gamePiece){			
		for(GamePiece gp : move.getPieces()){
			if(gp.getId() == gamePiece.getId()){
				return true;
			}
		}
		return false;
		
	}

	public Boolean isGameFinished(GameState state) {
		if (state.hasEnded() == true) {
			return true;
		} else
			return false;
	}

	public void pushOtherPiece(GamePiece piece) {
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
	
	
	private LudoMoveResult movePieceInGoal(Move move){		
		if(isPiecesInFinishline(move) == true && getNumberOfStepsFromDice() <=4){
			return LudoMoveResult.MOVE_PIECE_IN_TO_GOAL;
		}else
			return LudoMoveResult.MOVE_PIECE_NOT_IN_TO_GOAL;
	}

	private LudoMoveResult checkValidMoveFromStartForPlayer(Move move,
			String start, String startSix, List<String> homeValues) {
		if (getNumberOfStepsFromDice() == 1) {
			if (move.getDestination().getId().equals(start)) {
				return LudoMoveResult.MOVE_VALID;
			} else
				return LudoMoveResult.MOVE_INCORRECTNUMBEROFSTEPS;
		}

		else if (getNumberOfStepsFromDice() == 6) {
			if (move.getDestination().getId().equals(startSix)) {
				return LudoMoveResult.MOVE_VALID;
			} else if (move.getDestination().getId().equals(start)) {

				for (String basePositions : homeValues) {
					BoardLocation home = HelpMethodsFinaMedKnuff
							.getBoardLocationFromCoordinate(basePositions,
									state.getBoard());
					if (home.getPiece() != null
							&& home.getPiece() != move.getPiece()) {
						return LudoMoveResult.MOVE_VALID_INBASE_TWO_PIECES;
					}
				}

				return LudoMoveResult.MOVE_INVALID_INBASE_TWO_PIECES_NOT_AVAILABLE;
			}

		}

		return LudoMoveResult.MOVE_IN_BASE_DID_NOT_GET_THE_CORRECT_EYES_ON_THE_DICE_TO_MOVE_OUT;
	}

	public boolean movePlayerToStartPosition(Move move) {
		int stepsLeft = getNumberOfStepsFromDice();
		GamePiece piece = move.getSource().getPiece();
		System.out.println(move.getPlayer().getName());

		if (move.getPlayer().getName().equals("Red")) {

			return movePlayerFromHomeToNextLocation(move, stepsLeft, piece,
					LudoStaticValues.REDSTART);
		}

		else if (move.getPlayer().getName().equals("Blue")) {
			return movePlayerFromHomeToNextLocation(move, stepsLeft, piece,
					LudoStaticValues.BLUESTART);
		}

		else if (move.getPlayer().getName().equals("Yellow")) {
			return movePlayerFromHomeToNextLocation(move, stepsLeft, piece,
					LudoStaticValues.YELLOWSTART);
		}

		else {
			return movePlayerFromHomeToNextLocation(move, stepsLeft, piece,
					LudoStaticValues.GREENSTART);
		}
	}

	private boolean movePlayerFromHomeToNextLocation(Move move, int dice,
			GamePiece piece, String startCoordinate) {
		System.out.println(dice);
		if (dice == 1) {
			move.getSource().setPiece(null);
			BoardLocation start = HelpMethodsFinaMedKnuff
					.getBoardLocationFromCoordinate(startCoordinate,
							state.getBoard());
			start.setPiece(piece);
			return true;
		}
		if (dice == 6) {
			move.getSource().setPiece(null);
			int deltaLocation = HelpMethodsFinaMedKnuff
					.getFlatListIndexFromCoordinate(startCoordinate,
							state.getBoard());
			deltaLocation = deltaLocation + (dice - 1)
					% LudoStaticValues.TOTALSTEPSAROUNDTHEBOARD;
			state.getBoard().getLocations().get(deltaLocation).setPiece(piece);
			return true;
		}

		return false;
	}

	private int getNumberOfStepsFromDice() {
		return state.getDieRollFactory().getLastRoll().getResult();
	}
}
