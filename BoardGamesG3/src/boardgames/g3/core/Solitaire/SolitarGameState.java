package boardgames.g3.core.Solitaire;

import game.api.GameState;
import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.GamePiece;
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
		putAllTheBeadsOnTheBoard();
		this.player = new Player("Playah!", null);
		ruler = new RuleControllerSolitar();
	}
	
	private void putAllTheBeadsOnTheBoard() {
		for (BoardLocation b : board.getLocations()) {
			b.setPiece(new GamePiece("O"));
		}

		board.getLocations().get(24).setPiece(null);
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
		boolean movePossible = false;
		BoardLocation[][] tmpBoard = SolitarHelpMethods.get2DBoard(ROWS, COLS, board.getLocations());
		for(int r = 0;r<ROWS;r++){
			for(int c = 0;c<COLS;c++){
				GamePiece currentPiece = tmpBoard[r][c].getPiece();
				if(currentPiece != null){
					if(c<(COLS-2)){
						GamePiece rightPiece = tmpBoard[r][c+1].getPiece();
						GamePiece secondRightPiece = tmpBoard[r][c+2].getPiece();
						if(rightPiece != null && secondRightPiece == null)
						{
							movePossible = true;
							break;
						}
					}
					
					else{
						if(r<(ROWS-2)){
							GamePiece pieceBelow = tmpBoard[r+1][c].getPiece();
							GamePiece twoBelow = tmpBoard[r+2][c].getPiece();
							if(pieceBelow != null && twoBelow == null){
								movePossible = true;
								break;
							}
						}
					}
					
				}
			}
		}
		
		
		return !movePossible;
	}

	@Override
	public Boolean proposeMove(Move move) {
		if (ruler.isValidMove(move)) {
			move.execute();
			removeBeadInBetween(move);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;

		}
	}

	@Override
	public void reset() {

	}

	private void removeBeadInBetween(Move move) {
		BoardLocation destination = move.getDestination();
		BoardLocation source = move.getSource();

		int sourceRow = Integer.parseInt(source.getId().substring(0, 1));
		int sourceCol = Integer.parseInt(source.getId().substring(1, 2));

		int destRow = Integer.parseInt(destination.getId().substring(0, 1));
		int destCol = Integer.parseInt(destination.getId().substring(1, 2));

		int deltaRow = (sourceRow - destRow);
		int deltaCol = (sourceCol - destCol);
		int middlePieceRow = destRow + deltaRow / 2;
		int middlePieceCol = destCol + deltaCol / 2;

		String middleLoc = Integer.toString(middlePieceRow)
				+ Integer.toString(middlePieceCol);

		BoardLocation middle = SolitarHelpMethods
				.getBoardLocationFromCoordinate(middleLoc, board);
		middle.setPiece(null);

	}
}
