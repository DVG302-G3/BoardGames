package boardgames.g3.core;

import game.impl.Board;
import game.impl.BoardLocation;

import java.util.List;

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
	
	public static int getFlatListIndexFromCoordinate(String location, Board board){
		List<BoardLocation> b = board.getLocations();
		for(int i = 0;i<b.size();i++){
			if(b.get(i).getId().equals(location)){
				return i;
			};
		}
		
		return -1;
	}

}
