package boardgames.g3.core.Ludo.StrategyMove;

import game.impl.Move;
import boardgames.g3.core.Ludo.LudoGameState;
import boardgames.g3.core.Ludo.LudoStaticValues;

public class MoveInvalidBaseCantMoveOut implements MoveStrategy{

	@Override
	public Boolean execute(Move move, LudoGameState state) {
		state.setMessage(LudoStaticValues.MESSAGE_IN_BASE_CANTMOVEOUT);
		state.nextTurn();
		return false;
	}

}
