package boardgames.g3.Input_OutPutUnits;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.io.OutputUnit;

import java.util.List;

public class SolitarConsoleOutput implements OutputUnit {
	final int ROWS = 7;
	final int COLS = 7;

	@Override
	public void publish(GameState state) {

		List<BoardLocation> locations = state.getBoard().getLocations();

		
		System.out.print("  1 2 3 4 5 6 7");
		int rowCounter = 1;
		for (int i = 0; i < locations.size(); i++) {
			GamePiece piece = null;
			if (i % COLS == 0) {
				System.out.println();
				System.out.print(rowCounter++ + " ");
			}

			if (locations.get(i) != null){ 
				piece = locations.get(i).getPiece();
			if (piece == null)
				System.out.print(". ");
			else
				System.out.print("O ");
			
			}else
				System.out.print("  ");
			

		}
	}

}
