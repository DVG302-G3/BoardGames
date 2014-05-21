package boardgames.g3.core.Ludo;

import game.impl.GamePiece;
import game.impl.Move;

public class MoveValidExecutor {
	
	String goalCoordinate;
	public MoveValidExecutor(String goalCoordinate){
		this.goalCoordinate = goalCoordinate;
	}

	public void executeAndMakeSureThatNoPieceWillBeDeleted(Move move) {
		GamePiece sourcePiece = checkSourceForDoublePieces(move);
		GamePiece destinationPiece = checkDestinationForExistingPieces(move);

		move.execute();
		if (destinationPiece != null)
			move.getDestination().addPiece(destinationPiece);

		if (sourcePiece != null)
			move.getSource().addPiece(sourcePiece);

		if(move.getDestination().getId().equals(goalCoordinate))
			move.getDestination().clear();
		
	}
	
	
	private GamePiece checkSourceForDoublePieces(Move move) {
		move.getSource().getPieces().size();
		if (move.getSource().getPieces().size() > 1) {
			return move.getSource().getPieces().get(1);
		}
		return null;
	}

	private GamePiece checkDestinationForExistingPieces(Move move) {
		return move.getDestination().getPiece();
	}

}
