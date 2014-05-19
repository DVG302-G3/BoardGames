package boardgames.g3.core.Ludo;

import game.impl.BoardLocation;
import game.impl.Move;

public class MoveValidInbaseTwoPiecesExecutor {
	LudoGameState state;

	public MoveValidInbaseTwoPiecesExecutor(LudoGameState state) {
		this.state = state;
	}

	public void moveSecondPieceToStartPosition(Move move) {
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

	}
}
