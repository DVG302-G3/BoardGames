package boardgames.g3.core.Solitaire;

import game.api.GameState;
import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.Move;
import game.io.InputUnit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class SolitarInputUnit extends InputUnit {
	BufferedReader br;

	@Override
	public void setup(GameState state) {
		br = new BufferedReader(new InputStreamReader(System.in));
		while (!state.hasEnded()) {
			notifyListenersOfMove(getNextMove(state));
		}
	}

	private Move getNextMove(GameState state) {
		List<String> input;
		BoardLocation source = null;
		BoardLocation destination = null;

		try {
			System.out.println();
			System.out.print("Mata in nästa drag:");
			input = Arrays.asList(br.readLine().split(" "));

			source = SolitarHelpMethods.getBoardLocationFromCoordinate(input.get(0), state.getBoard());
			destination = SolitarHelpMethods.getBoardLocationFromCoordinate(input.get(1), state.getBoard());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return new Move(state.getPlayerInTurn(), source, destination);
	}

	

}
