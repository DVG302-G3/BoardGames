package boardgames.g3.core.Solitaire;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.Move;

public class RuleControllerSolitar {

	public Boolean isValidMove(Move move){
		if(move.getSource().getPiece() == null){
			return false;
		}
			else
		{

			int sourceRow, sourceCol, destRow, destCol;
			BoardLocation destination = move.getDestination();
			BoardLocation source = move.getSource();
			
			if(destination.getPiece() != null){
				return false;
			}

			sourceRow = Integer.parseInt(source.getId().substring(0, 1));
			sourceCol = Integer.parseInt(source.getId().substring(1, 2));

			destRow = Integer.parseInt(destination.getId().substring(0, 1));
			destCol = Integer.parseInt(destination.getId().substring(1, 2));

			int deltaRow = Math.abs((sourceRow - destRow));
			int deltaCol = Math.abs((sourceCol - destCol));
			if(deltaRow == 2 && deltaCol == 0)
				return true;
			else if(deltaCol == 2 && deltaRow == 0)
				return true;
			
			else
				return false;
			}
		
	}
	
	public Boolean isGameFinished(GameState gamestate){
		return false;
	}
}
