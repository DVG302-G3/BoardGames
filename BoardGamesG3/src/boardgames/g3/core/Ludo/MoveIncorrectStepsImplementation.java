package boardgames.g3.core.Ludo;

import game.impl.Move;

public class MoveIncorrectStepsImplementation implements MoveStrategy {

	@Override
	public Boolean execute(Move move, LudoGameState state) {
		state.setMessage(LudoStaticValues.MESSAGE_INCORRECTNUMBEROFSTEPS);
		return false;
	}

}
