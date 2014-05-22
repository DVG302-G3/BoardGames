package boardgames.g3.core.Ludo.StrategyMove;

import game.impl.Move;
import boardgames.g3.core.Ludo.LudoGameState;

public interface MoveStrategy {
	public Boolean execute(Move move, LudoGameState state);
}
