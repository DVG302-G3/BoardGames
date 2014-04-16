package boardgames.g3.core;

import game.api.GameState;
import game.impl.Move;
import game.io.InputUnit;

public class SolitarGUIInputUnit extends InputUnit {

	GameState state;
	boolean completeMove;

	@Override
	public void setup(GameState arg0) {
		state = arg0;
		completeMove = false;
	}

	public void onClick(String coordinate) {
		if (completeMove){
			notifyListenersOfMove(new Move(state.getPlayerInTurn(), state
					.getBoard().getLocations().get(10), state.getBoard()
					.getLocations().get(24)));
	
		}

	}
}
