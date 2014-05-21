package boardgames.g3.core.Ludo.StrategyMove;

import boardgames.g3.core.Ludo.LudoGameState;
import boardgames.g3.core.Ludo.LudoStaticValues;
import game.impl.Move;

public class MoveLapseOwnPieceImplementation implements MoveStrategy{

	@Override
	public Boolean execute(Move move, LudoGameState state) {
		state.setMessage(LudoStaticValues.MESSAGE_INVALID_CANT_LAPSE_YOUR_OWN_PIECE);
return false;
	}

}
