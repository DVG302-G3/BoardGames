package boardgames.g3.core;

import game.api.GameState;
import game.impl.Board;
import game.impl.Move;

public class RuleControllerFiaMedKnuff {

	public Boolean isValidMove(GameState gamestate, Move move) {
		if (move.getSource().getPiece() == null)
			return false;
		else {
			return true;
		}
	}

	public Boolean isGameFinished(GameState gamestate) {
		return false;
	}

}
