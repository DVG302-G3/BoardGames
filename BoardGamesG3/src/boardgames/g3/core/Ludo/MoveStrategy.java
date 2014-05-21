package boardgames.g3.core.Ludo;

import game.api.GameState;
import game.impl.Move;

public interface MoveStrategy {
	public Boolean execute(Move move, LudoGameState state);
}
