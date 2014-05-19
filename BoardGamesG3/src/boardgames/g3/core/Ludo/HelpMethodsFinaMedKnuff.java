package boardgames.g3.core.Ludo;

import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Player;

import java.util.ArrayList;
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
		for(BoardLocation boardLocation : board.getLocations()){
			for(GamePiece gp : boardLocation.getPieces()){
				if(gp != null && gp.equals(piece))
					return boardLocation;
			}
		}
		return null;
	}
		
	public static boolean doesPlayerHaveAnyPiecesOnTheBoard(Player player, Board board){
		List<String> homeLocations = new ArrayList<String>();
		homeLocations.addAll(LudoStaticValues.BLUEHOME);
		homeLocations.addAll(LudoStaticValues.GREENHOME);
		homeLocations.addAll(LudoStaticValues.REDHOME);
		homeLocations.addAll(LudoStaticValues.YELLOWHOME);

		for(BoardLocation bl : board.getLocations()){
			GamePiece piece = bl.getPiece(); 
			if(piece != null)
			{
				if(player.getPieces().contains(piece) && !homeLocations.contains(bl.getId()))
					return true;
			}
		}
		return false;
	}

}