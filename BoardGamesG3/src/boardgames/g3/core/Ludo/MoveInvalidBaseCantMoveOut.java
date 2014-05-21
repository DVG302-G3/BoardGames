package boardgames.g3.core.Ludo;

import game.impl.Move;

public class MoveInvalidBaseCantMoveOut implements MoveStrategy{

	@Override
	public Boolean execute(Move move, LudoGameState state) {
		state.setMessage(LudoStaticValues.MESSAGE_IN_BASE_CANTMOVEOUT);
		state.nextTurn();
		return false;
	}

}
