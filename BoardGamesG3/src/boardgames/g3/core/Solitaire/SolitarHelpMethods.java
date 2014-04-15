package boardgames.g3.core.Solitaire;

import game.impl.Board;
import game.impl.BoardLocation;

public class SolitarHelpMethods {
	
	public static BoardLocation getBoardLocationFromCoordinate(String location, Board board) {
		for (BoardLocation b : board.getLocations()) {
			if (b.getId() != null) {
				if (b.getId().equals(location)){
					System.out.println(b.getId());
					return b;
				}

			}
		}

		return null;
	}

}
