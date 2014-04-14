package boardgames.g3.core.Solitaire;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.Move;
import game.io.InputUnit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SolitarInputUnit extends InputUnit{
	BufferedReader br;
	@Override
	public void setup(GameState state) {
		br = new BufferedReader(new InputStreamReader(System.in));
		while(!state.hasEnded()){
			notifyListenersOfMove(getNextMove(state));
		}
	}

	private Move getNextMove(GameState state) {
		try {
			br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Move move = new Move(state.getPlayerInTurn(), new BoardLocation("11"), new BoardLocation("22"));
		return move;
	}

}
