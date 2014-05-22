package boardgames.g3.core.Ludo;

import game.impl.Move;
import boardgames.g3.core.Ludo.StrategyMove.MoveStrategy;
import boardgames.g3.core.Ludo.StrategyMove.MoveValidImplementation;

public class LudoMoveController {

	LudoGameState gameState;
	LudoRuleController ruler;
	MoveValidImplementation moveValidExec;
	
	
	public LudoMoveController(LudoGameState gameState) {
		this.gameState = gameState;


		ruler = new LudoRuleController(gameState);
		moveValidExec = new MoveValidImplementation(LudoStaticValues.GOAL, ruler);

	}

	public Boolean proposeMove(Move move) {
		MoveStrategy result = ruler.evaluateMove(move);
		return result.execute(move, gameState);
	}

}
