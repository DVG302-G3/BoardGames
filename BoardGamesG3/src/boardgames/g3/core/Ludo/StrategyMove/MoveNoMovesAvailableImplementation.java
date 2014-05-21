package boardgames.g3.core.Ludo.StrategyMove;

import boardgames.g3.core.Ludo.LudoGameState;
import boardgames.g3.core.Ludo.LudoStaticValues;
import game.impl.Move;

public class MoveNoMovesAvailableImplementation implements MoveStrategy{

	@Override
	public Boolean execute(Move move, LudoGameState state) {
		state.setMessage(LudoStaticValues.MESSAGE_NO_MOVE_AVAILABLE);
		state.nextTurn();
		return false;
	}

}
