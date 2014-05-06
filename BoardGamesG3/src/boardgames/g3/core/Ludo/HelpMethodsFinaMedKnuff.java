package boardgames.g3.core.Ludo;

import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.GamePiece;

import java.util.List;

public class HelpMethodsFinaMedKnuff {

	public static BoardLocation getBoardLocationFromCoordinate(String location,
			Board board) {
		for (BoardLocation b : board.getLocations()) {
			if (b.getId() != null) {
				if (b.getId().equals(location)) {
					return b;
				}
			}
		}
		return null;
	}

	public static int getFlatListIndexFromCoordinate(String location,
			Board board) {
		List<BoardLocation> b = board.getLocations();
		for (int i = 0; i < b.size(); i++) {
			if (b.get(i).getId().equals(location)) {
				return i;
			}
		}

		return -1;
	}

	public static BoardLocation getBoardLocationFromPiece(GamePiece piece,
			Board board) {
		List<BoardLocation> b = board.getLocations();
		for (int i = 0; i < b.size(); i++) {
			if (b.get(i).getPiece()!=null && b.get(i).getPiece().equals(piece)) {
				return b.get(i);
			}
		}

		return null;
	}

}
