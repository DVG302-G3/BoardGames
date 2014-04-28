package boardgames.g3.core;

import game.api.GameState;
import game.impl.Move;

public class RuleControllerFiaMedKnuff {
	
	static final String RedStart = "EA";
	static final String BlueStart = "AG";
	static final String GreenStart = "KE";
	static final String YellowStart = "GK";
	
	static final String[] RedFinishLine = {"FB", "FC", "FD", "FE"};
	static final String[] BlueFinishLine = {"BF", "CF", "DF", "EF"};
	static final String[] GreenFinishLine = {"JF", "IF", "GF", "HF"};
	static final String[] YellowFinishLine = {"FJ", "FI", "FH", "FG"};

	
	
    public Boolean isValidMove(GameState gamestate, Move move){
    	if(move.getSource().getPiece() == null)
    		return false;
    	else{
    		return true;
    	}
    }
    public Boolean isGameFinished(GameState gamestate){
        return false;
    }
}
