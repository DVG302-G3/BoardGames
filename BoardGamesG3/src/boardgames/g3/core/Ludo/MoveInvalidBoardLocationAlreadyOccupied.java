package boardgames.g3.core.Ludo;

import game.impl.Move;

public class MoveInvalidBoardLocationAlreadyOccupied implements MoveStrategy {

	@Override
	public Boolean execute(Move move, LudoGameState state) {
		state.setMessage(LudoStaticValues.MESSAGE_INVALIDA_BOARDLOCATION_ALREADY_OCCUPIED);
		return false;
	}

}
