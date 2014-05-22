package boardgames.g3.core.Ludo.StrategyMove;

import game.impl.Move;
import boardgames.g3.core.Ludo.LudoGameState;
import boardgames.g3.core.Ludo.LudoStaticValues;

public class MoveNoMovesAvailableImplementation implements MoveStrategy{

	@Override
	public Boolean execute(Move move, LudoGameState state) {
		state.setMessage(LudoStaticValues.MESSAGE_NO_MOVE_AVAILABLE);
		state.nextTurn();
		return false;
	}

}
