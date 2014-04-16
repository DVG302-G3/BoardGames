package boardgames.g3.core;

import boardgames.g3.core.Solitaire.SolitarGameState;
import game.api.GameState;
import game.impl.Move;
import game.io.InputUnit;

public class SolitarGUIInputUnit extends InputUnit {

	public GameState state = new SolitarGameState();
	boolean completeMove;

	@Override
	public void setup(GameState arg0) {
		state = arg0;
		completeMove = false;
	}

	public void onClick(String coordinate) {
		completeMove = true;
		if (completeMove){
			notifyListenersOfMove(new Move(state.getPlayerInTurn(), state
					.getBoard().getLocations().get(10), state.getBoard()
					.getLocations().get(24)));
	
		}
		
		

	}
}
