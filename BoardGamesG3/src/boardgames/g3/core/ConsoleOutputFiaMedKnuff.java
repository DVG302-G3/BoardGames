package boardgames.g3.core;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.io.OutputUnit;
import java.util.List;

public class ConsoleOutputFiaMedKnuff implements OutputUnit {
	final int ROWS = 11;
	final int COLS = 11;

	@Override
	public void publish(GameState state) {
		System.out.print("  A B C D E F G H I J K");

		char cordRow = 'A';
		char cordCol = 'A';

		for (int i = 0; i < ROWS * COLS; i++) {

			if (i % COLS == 0) {
				System.out.println();
				System.out.print(cordRow + " ");
				cordCol = 'A';
				cordRow++;
			}


			String coordinate = Character.toString(cordRow)
					+ Character.toString(cordCol);
			String col;
			BoardLocation location = HelpMethodsFinaMedKnuff
					.getBoardLocationFromCoordinate(coordinate,
							state.getBoard());
			if (location == null)
				col = null;
			else
				col = location.getId();

			if (col == null) {
				System.out.print("  ");
			} else {
				System.out.print("X ");
			}

			cordCol++;

		}
	}
}
