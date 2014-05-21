package boardgames.g3.core.Ludo.StrategyMove;

import boardgames.g3.core.Ludo.LudoGameState;
import game.api.GameState;
import game.impl.Move;

public interface MoveStrategy {
	public Boolean execute(Move move, LudoGameState state);
}
