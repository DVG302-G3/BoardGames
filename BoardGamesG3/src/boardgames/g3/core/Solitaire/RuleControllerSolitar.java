package boardgames.g3.core.Solitaire;

import game.api.GameState;
import game.impl.Move;

public class RuleControllerSolitar {

	public Boolean isValidMove(Move move){
		if(move.getPiece() == null)
			return false;
		else
		{
			return true;
		}
		
	}
	
	public Boolean isGameFinished(GameState gamestate){
		return false;
	}
}
