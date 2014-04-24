package boardgames.g3.core;

import game.impl.Board;
import game.impl.BoardLocation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelpMethodsFinaMedKnuff {


	public static BoardLocation getBoardLocationFromCoordinate(String location, Board board) {
		for (BoardLocation b : board.getLocations()) {
			if (b.getId() != null) {
				if (b.getId().equals(location)) {
					return b;
				}
			}
		}
		return null;
	}

}
