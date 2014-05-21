package boardgames.g3.core.Ludo;

import game.impl.Move;

public class MoveInvalidBlockImplementation implements MoveStrategy {

	@Override
	public Boolean execute(Move move, LudoGameState state) {
		state
				.setMessage(LudoStaticValues.MESSAGE_INVALID_CANT_PASS_A_BLOCK);
		return false;
	}

}
