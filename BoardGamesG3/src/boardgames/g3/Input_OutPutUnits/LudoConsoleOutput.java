package boardgames.g3.Input_OutPutUnits;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.io.OutputUnit;

import java.util.List;

import boardgames.g3.core.Ludo.LudoHelpMethods;
import boardgames.g3.core.Ludo.LudoStaticValues;

public class LudoConsoleOutput implements OutputUnit {

	@Override
	public void publish(GameState state) {
		System.out.println("      A     B     C     D     E     F     G     H     I     J     K");

		char cordRow = 'A';
		char cordCol = 'A';

		for (int r = 0; r < LudoStaticValues.ROWS; r++) {
			System.out.print(cordRow + "     ");
			for (int c = 0; c < LudoStaticValues.COLS; c++) {

				String coordinate = Character.toString(cordRow)
						+ Character.toString(cordCol++);
				String col;
				BoardLocation location = LudoHelpMethods
						.getBoardLocationFromCoordinate(coordinate,
								state.getBoard());

				if (location == null)
					col = null;
				else
					col = location.getId();

				if (col == null) {
					System.out.print("      ");
				} 
				else if(location.getPiece() != null){
					List<GamePiece> pieces = location.getPieces();
					for(GamePiece p : pieces){
						System.out.print(p.getId());
					}

					if(pieces.size() == 1){
						System.out.print("    ");
					}
					else
						System.out.print("  ");

				}
					else {
					System.out.print("X     ");
				}
			}
			System.out.println();
			System.out.println();

			cordCol = 'A';
			cordRow++;
		}

	}
}