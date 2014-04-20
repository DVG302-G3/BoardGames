package boardgames.g3.core.Solitaire;

import game.impl.Board;
import game.impl.BoardLocation;

import java.util.List;

public class SolitarHelpMethods {

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
	
	public static BoardLocation[][] get2DBoard(int rows, int cols, List<BoardLocation> board){
		int index = 0;
		BoardLocation[][] newBoard = new BoardLocation[rows][cols];
		for(int currentROW = 0;currentROW < rows;currentROW++){
			for(int currentCOL = 0; currentCOL < rows;currentCOL++){
				newBoard[currentROW][currentCOL] = board.get(index++);
			}
		}
		
		return newBoard;
	}

}
