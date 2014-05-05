package boardgames.g3.core;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Move;
import game.impl.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleControllerFiaMedKnuff {

	Map<String, Integer> stepCounter;
	GameState state;

	public RuleControllerFiaMedKnuff(GameState state) {
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
		else {
			if (pieceInBase(move)){
				return LudoMoveResult.MOVE_PIECEINBASE;
			}
				

			int destinationValue = HelpMethodsFinaMedKnuff
					.getFlatListIndexFromCoordinate(move.getDestination()
							.getId(), state.getBoard());
			int sourceValue = HelpMethodsFinaMedKnuff
					.getFlatListIndexFromCoordinate(move.getSource().getId(),
							state.getBoard());
			int steps = (destinationValue - sourceValue)
					% LudoStaticValues.TOTALSTEPSAROUNDTHEBOARD;
			int totalStepsForPiece = stepCounter.get(move.getSource()
					.getPiece().getId())
					+ steps;
			if (totalStepsForPiece > LudoStaticValues.TOTALSTEPSAROUNDTHEBOARD)
				return LudoMoveResult.MOVE_LAPSED;
			else {
				stepCounter.put(move.getSource().getPiece().getId(),
						totalStepsForPiece);
				return LudoMoveResult.MOVE_VALID;
			}

		}
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
		for(String coordinate : list){
			if(coordinate.equals(id))
				return true;
		}
		
		return false;
	}
	
	public Boolean isPiecesInFinishline(Move move){
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

	public Boolean isGameFinished(GameState state) {
		if (state.hasEnded() == true){
			return true;
			}else 
				return false;
	}

	public boolean checkIfSourceIsBase(Move move) {
		return true;
	}

	public boolean movePlayerToStartPosition(Move move) {
		int dice = getNumberOfSteps(move);
		GamePiece piece = move.getSource().getPiece();
		System.out.println(move.getPlayer().getName());

		if (move.getPlayer().getName().equals("Red")) {
			
			return movePlayerFromHomeToNextLocation(move, dice, piece, LudoStaticValues.REDSTART);
		}

		else if (move.getPlayer().getName().equals("Blue")) {
			return movePlayerFromHomeToNextLocation(move, dice, piece, LudoStaticValues.BLUESTART);
		}

		else if (move.getPlayer().getName().equals("Yellow")) {
			return movePlayerFromHomeToNextLocation(move, dice, piece, LudoStaticValues.YELLOWSTART);
		}

		else {
			return movePlayerFromHomeToNextLocation(move, dice, piece, LudoStaticValues.GREENSTART);
		}
}

	private boolean movePlayerFromHomeToNextLocation(Move move, int dice,
			GamePiece piece, String startCoordinate) {
		System.out.println(dice);
		if(dice == 1){
			move.getSource().setPiece(null);
			BoardLocation start = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate(startCoordinate, state.getBoard());
			start.setPiece(piece);
			return true;
		}
		if(dice == 6){
			move.getSource().setPiece(null);
			int deltaLocation = HelpMethodsFinaMedKnuff.getFlatListIndexFromCoordinate(startCoordinate, state.getBoard());
			deltaLocation = deltaLocation + (dice-1) % LudoStaticValues.TOTALSTEPSAROUNDTHEBOARD;
			state.getBoard().getLocations().get(deltaLocation).setPiece(piece);
			return true;
		}
		
		return false;
	}

	private int getNumberOfSteps(Move move) {
		int destinationValue = HelpMethodsFinaMedKnuff
				.getFlatListIndexFromCoordinate(move.getDestination().getId(),
						state.getBoard());
		int sourceValue = HelpMethodsFinaMedKnuff
				.getFlatListIndexFromCoordinate(move.getSource().getId(),
						state.getBoard());
		return LudoStaticValues.TOTALSTEPSAROUNDTHEBOARD+((destinationValue - sourceValue)
				% LudoStaticValues.TOTALSTEPSAROUNDTHEBOARD);
	}
}
