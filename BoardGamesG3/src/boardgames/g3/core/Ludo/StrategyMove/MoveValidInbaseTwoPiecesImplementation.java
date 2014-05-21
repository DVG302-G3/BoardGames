package boardgames.g3.core.Ludo.StrategyMove;

import boardgames.g3.core.Ludo.HelpMethodsFinaMedKnuff;
import boardgames.g3.core.Ludo.LudoGameState;
import boardgames.g3.core.Ludo.LudoPlayer;
import boardgames.g3.core.Ludo.LudoRuleController;
import game.impl.BoardLocation;
import game.impl.Move;

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
			BoardLocation home = HelpMethodsFinaMedKnuff
					.getBoardLocationFromCoordinate(basePositions,
							state.getBoard());
			if (home.getPiece() != null && home.getPiece() != move.getPiece()) {
				BoardLocation start = HelpMethodsFinaMedKnuff
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
