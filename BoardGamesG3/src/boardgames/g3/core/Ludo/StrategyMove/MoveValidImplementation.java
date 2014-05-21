package boardgames.g3.core.Ludo.StrategyMove;

import boardgames.g3.core.Ludo.LudoGameState;
import boardgames.g3.core.Ludo.LudoPlayer;
import boardgames.g3.core.Ludo.LudoRuleController;
import game.impl.GamePiece;
import game.impl.Move;

public class MoveValidImplementation implements MoveStrategy{
	
	String goalCoordinate;
	LudoRuleController ruler;
	
	
	public MoveValidImplementation(String goalCoordinate, LudoRuleController ruler){
		this.goalCoordinate = goalCoordinate;
		this.ruler = ruler;
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

	@Override
	public Boolean execute(Move move, LudoGameState state) {
		GamePiece sourcePiece = checkSourceForDoublePieces(move);
		GamePiece destinationPiece = null;

		if (ruler.needToPush(move))
			ruler.pushOtherPiece(move.getDestination());
		else
			destinationPiece = checkDestinationForExistingPieces(move);

		
		move.execute();

		if (destinationPiece != null)
			move.getDestination().addPiece(destinationPiece);

		if (sourcePiece != null)
			move.getSource().addPiece(sourcePiece);

		if(move.getDestination().getId().equals(goalCoordinate)){
			updatePlayerInfoAndCheckIfWinner(move, state);
			move.getDestination().clear();
			
		}
		
		state.setMessage("");
		state.nextTurn();

		return true;
		
	}

	private void updatePlayerInfoAndCheckIfWinner(Move move, LudoGameState state) {
		LudoPlayer player = state.getLudoPlayerFromPlayer(move.getPlayer());
		player.aPieceJustEnteredGoal();
		if(player.getPiecesLeft() == 0){
			if(state.getWinner() == null)
				state.setWinner(player.getPlayerObject());
		}
	}

}
