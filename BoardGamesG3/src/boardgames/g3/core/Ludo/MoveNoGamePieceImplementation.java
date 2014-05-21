package boardgames.g3.core.Ludo;

import game.impl.Move;

public class MoveNoGamePieceImplementation implements MoveStrategy {

	@Override
	public Boolean execute(Move move, LudoGameState state) {
		state.setMessage(LudoStaticValues.MESSAGE_NOGAMEPIECE);
		return false;
	}

}
