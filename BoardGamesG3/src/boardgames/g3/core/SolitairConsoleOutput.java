package boardgames.g3.core;

import game.api.GameState;
import game.impl.BoardLocation;
import game.io.OutputUnit;

import java.util.List;

public class SolitairConsoleOutput implements OutputUnit {
	final int ROWS = 7;
	final int COLS = 7;

	@Override
	public void publish(GameState state) {

		List<BoardLocation> locations = state.getBoard().getLocations();
		
		
		for (int i = 0; i < locations.size(); i++) {
			String col = locations.get(i).getId();
			if(col == null){
				System.out.print("   ");
			}
			else{
				System.out.print(col+" ");
			}
			
			if ((i + 1) % COLS == 0)
				System.out.println();
		}

	}

}
