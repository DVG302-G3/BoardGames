
package boardgames.g3.core;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.io.OutputUnit;
import java.util.List;

public class ConsoleOutputFiaMedKnuff implements OutputUnit{
    final int ROWS = 11;
    final int COLS = 11;

    @Override
    public void publish(GameState state) {
        List<BoardLocation> locations = state.getBoard().getLocations();
        System.out.print("  1 2 3 4 5 6 7 8 9 10 11");
        int rowCounter = 1;
        
        for (int i = 0; i < locations.size(); i++) {
			String col = locations.get(i).getId();
			GamePiece piece = locations.get(i).getPiece();
			
			if (i % COLS == 0) {
				System.out.println("");
				System.out.print(rowCounter++ + " ");
			}
			

			if (col == null) {
				System.out.print("  ");
			} else { 
				
				if(piece == null)
					System.out.print("o ");


				else if (piece.getId() == "R"){
					System.out.println("R");
				}else
					System.out.print("O ");
					
			}
		}
    }
    
    /*
    public String getRow(GameState state){
        return "";//getLocationAsString(state.getBoard());
    } 
    */
    
}
