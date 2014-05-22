package boardgames.g3.core.Solitaire;

import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.GamePiece;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolitarBoardFactory {
	
	Board board;
	
	
	public void execute() {
		this.board = new Board(createBoardLocations());
		putAllTheBeadsOnTheBoard();
	}
	
	

	public Board getBoard() {
		return board;
	}
	
	
	private void putAllTheBeadsOnTheBoard() {
		for (BoardLocation b : board.getLocations()) {
			if(b != null)
				b.setPiece(new GamePiece("O"));
		}

		board.getLocations().get(24).setPiece(null);
	}
	
	private List<BoardLocation> createBoardLocations() {
		List<BoardLocation> boardLocations = new ArrayList<BoardLocation>();
		List<String> listOfRows = null;
		try {
			listOfRows = SolitarFileHandler.readCoordinate();
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (!listOfRows.isEmpty()) {
			boardLocations.addAll(getBoardLocationFromRow(listOfRows.get(0)));
			listOfRows.remove(0);
		}
		return boardLocations;
	}

	private List<BoardLocation> getBoardLocationFromRow(String row) {
		String[] locations = row.split(";");
		List<BoardLocation> boardLocations = new ArrayList<BoardLocation>();
		for (String s : locations) {
			if (s.equals("null"))
				boardLocations.add(null);
			else
				boardLocations.add(new BoardLocation(s));
		}
		return boardLocations;
	}



}
