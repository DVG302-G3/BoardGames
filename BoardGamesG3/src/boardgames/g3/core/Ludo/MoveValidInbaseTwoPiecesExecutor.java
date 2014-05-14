package boardgames.g3.core.Ludo;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.Move;

import java.util.List;

public class MoveValidInbaseTwoPiecesExecutor {
	GameState state;
	public MoveValidInbaseTwoPiecesExecutor(GameState state){
		this.state = state;
	}
	
	public void moveSecondPieceToStartPosition(Move move) {
		if (move.getPlayer().getName().equals(LudoStaticValues.REDPLAYER))
			moveSecondPieceForPlayer(move, LudoStaticValues.REDHOME,
					LudoStaticValues.REDSTART);
		else if (move.getPlayer().getName().equals(LudoStaticValues.YELLOWPLAYER))
			moveSecondPieceForPlayer(move, LudoStaticValues.YELLOWHOME,
					LudoStaticValues.YELLOWSTART);
		else if (move.getPlayer().getName().equals(LudoStaticValues.GREENPLAYER))
			moveSecondPieceForPlayer(move, LudoStaticValues.GREENHOME,
					LudoStaticValues.GREENSTART);
		else
			moveSecondPieceForPlayer(move, LudoStaticValues.BLUEHOME,
					LudoStaticValues.BLUESTART);
	}
	
	
	private void moveSecondPieceForPlayer(Move move,
			List<String> homeLocations, String startLocation) {
		for (String basePositions : homeLocations) {
			BoardLocation home = HelpMethodsFinaMedKnuff
					.getBoardLocationFromCoordinate(basePositions, state.getBoard());
			if (home.getPiece() != null && home.getPiece() != move.getPiece()) {
				BoardLocation start = HelpMethodsFinaMedKnuff
						.getBoardLocationFromCoordinate(startLocation, state.getBoard());
				start.addPiece(home.getPiece());
				home.clear();
				break;
			}
		}
	}
}
