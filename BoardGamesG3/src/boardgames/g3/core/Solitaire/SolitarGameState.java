package boardgames.g3.core.Solitaire;

import game.api.GameState;
import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.Move;
import game.impl.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolitarGameState implements GameState {

	Board board;
	Player player;
	RuleControllerSolitar ruler;
	public final int ROWS;
	public final int COLS;

	public SolitarGameState() {
		this.ROWS = 7;
		this.COLS = 7;
		this.board = new Board(createBoardLocations());
		this.player = new Player(null, null);
		ruler = new RuleControllerSolitar();
	}

	private List<BoardLocation> createBoardLocations() {
		List<BoardLocation> boardLocations = new ArrayList<BoardLocation>();
		List<String> listOfRows = null;
		try {
			listOfRows = FileHandler.readCoordinate();
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
				s = null;

			boardLocations.add(new BoardLocation(s));
		}
		return boardLocations;
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public Player getLastPlayer() {
		return player;
	}

	@Override
	public String getMessage() {
		return "";
	}

	@Override
	public Player getPlayerInTurn() {
		return player;
	}

	@Override
	public List<Player> getPlayers() {
		return Arrays.asList(player);
	}

	@Override
	public Player getWinner() {
		return null;
	}

	@Override
	public Boolean hasEnded() {
		return false;
	}

	@Override
	public Boolean proposeMove(Move move) {
		return ruler.isValidMove(move);
	}

	@Override
	public void reset() {

	}

}
