package boardgames.g3.core.Ludo.StrategyMove;

import game.impl.BoardLocation;
import game.impl.Move;
import boardgames.g3.core.Ludo.LudoHelpMethods;
import boardgames.g3.core.Ludo.LudoGameState;
import boardgames.g3.core.Ludo.LudoPlayer;
import boardgames.g3.core.Ludo.LudoRuleController;

public class MoveValidInbaseTwoPiecesImplementation implements MoveStrategy {

	LudoRuleController ruler;

	public MoveValidInbaseTwoPiecesImplementation(LudoRuleController ruler) {
		this.ruler = ruler;
	}

	@Override
	public Boolean execute(Move move, LudoGameState state) {
		if (ruler.needToPush(move))
			ruler.pushOtherPiece(move.getDestination());
		move.execute();

		LudoPlayer player = state.getLudoPlayerFromPlayer(move.getPlayer());
		for (String basePositions : player.getHomePositions()) {
			BoardLocation home = LudoHelpMethods
					.getBoardLocationFromCoordinate(basePositions,
							state.getBoard());
			if (home.getPiece() != null && home.getPiece() != move.getPiece()) {
				BoardLocation start = LudoHelpMethods
						.getBoardLocationFromCoordinate(
								player.getStartCoordinateOne(),
								state.getBoard());
				start.addPiece(home.getPiece());
				home.clear();
				break;
			}
		}

		state.setMessage("");
		state.nextTurn();
		
		return true;
		
	}

}
