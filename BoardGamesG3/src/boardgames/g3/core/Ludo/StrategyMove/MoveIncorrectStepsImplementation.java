package boardgames.g3.core.Ludo.StrategyMove;

import game.impl.Move;
import boardgames.g3.core.Ludo.LudoGameState;
import boardgames.g3.core.Ludo.LudoStaticValues;

public class MoveIncorrectStepsImplementation implements MoveStrategy {

	@Override
	public Boolean execute(Move move, LudoGameState state) {
		state.setMessage(LudoStaticValues.MESSAGE_INCORRECTNUMBEROFSTEPS);
		return false;
	}

}
