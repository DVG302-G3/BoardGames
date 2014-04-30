package boardgames.g3.core;

import game.api.GameState;
import game.impl.Move;
import game.impl.Player;

public class RuleControllerFiaMedKnuff {
	
	Map<String, int> stepCounter;

	public Boolean isValidMove(GameState gamestate, Move move) {
		if (move.getSource().getPiece() == null)
			return false;
		else {
			int destinationValue = HelpMethodsFinaMedKnuff.getFlatListIndexFromCoordinate(move.getDestination().getId(), gamestate.getBoard());
			int playerStartValue = getPlayersStartValue(move, gamestate);
			if(destinationValue % LudoStaticValues.TOTALSTEPSAROUNDTHEBOARD > playerStartValue)
				return false;
			else
				return true;
		}
	}

	private int getPlayersStartValue(Move move, GameState state) {
		if(move.getPlayer().getName().equals("Green"))
			return HelpMethodsFinaMedKnuff.getFlatListIndexFromCoordinate(LudoStaticValues.GREENSTART, state.getBoard());
		else if(move.getPlayer().getName().equals("Blue"))
			return HelpMethodsFinaMedKnuff.getFlatListIndexFromCoordinate(LudoStaticValues.BLUESTART, state.getBoard());
		else if(move.getPlayer().getName().equals("Yellow"))
			return HelpMethodsFinaMedKnuff.getFlatListIndexFromCoordinate(LudoStaticValues.YELLOWSTART, state.getBoard());
		else
			return HelpMethodsFinaMedKnuff.getFlatListIndexFromCoordinate(LudoStaticValues.REDSTART, state.getBoard());

	}

	public Boolean isGameFinished(GameState gamestate) {
		return false;
	}
	
	public boolean checkIfSourceIsBase(Move move) {
		return true;
	}
	

}
