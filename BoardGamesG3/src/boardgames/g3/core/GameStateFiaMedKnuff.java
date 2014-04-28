package boardgames.g3.core;

import game.api.GameState;
import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.DieRollFactory;
//import game.impl.DieRollFactory;
import game.impl.GamePiece;
import game.impl.Move;
import game.impl.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameStateFiaMedKnuff implements GameState {

	Board board;
	List<Player> players;

	RuleControllerFiaMedKnuff ruler;
	public final int ROWS;
	public final int COLS;
	private int numberOfPlayers = 4;
	private Integer turnCounter = 0;
	private String message;

	public GameStateFiaMedKnuff() {
		this.ROWS = 11;
		this.COLS = 11;
		this.players = createAndReturnPlayers(Arrays.asList("Player 1", "Player 2", "Player 3", "Player 4"));
		this.board = new Board(createBoardLocations());
		ruler = new RuleControllerFiaMedKnuff();

	}

	private List<Player> createAndReturnPlayers(List<String> names) {
		List<GamePiece> gamePieces = new ArrayList<GamePiece>();
		List<Player> players = new ArrayList<Player>();
		for(int i = 0 ; i<names.size();i++){
			gamePieces.add(new GamePiece("P"+(i+1)+"1"));
			gamePieces.add(new GamePiece("P"+(i+1)+"2"));
			gamePieces.add(new GamePiece("P"+(i+1)+"3"));
			gamePieces.add(new GamePiece("P"+(i+1)+"4"));
			
			players.add(new Player("Player"+(i+1), gamePieces));
		}
		return players;
	}


	private List<BoardLocation> createBoardLocations() {
		List<BoardLocation> boardLocations = new ArrayList<BoardLocation>();
		try {
			List<String> listOfPositions = FileHandlerFiaMedKnuff.readCoordinate();
			for(String s : listOfPositions){
				boardLocations.add(new BoardLocation(s));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return boardLocations;

	}


	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public Player getLastPlayer() {
		return players.get(3 - turnCounter % numberOfPlayers);
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public Player getPlayerInTurn() {
		return players.get(turnCounter % numberOfPlayers);
	}

	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public Player getWinner() {
		if (hasEnded()) {
			return getLastPlayer();
		}
		return null;
	}

	@Override
	public Boolean hasEnded() {
		return false;
	}

	@Override
	public Boolean proposeMove(Move move) {
		if (ruler.isValidMove(this, move)) {
			move.execute();
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public void reset() {

	}

	@Override
	public DieRollFactory getDieRollFactory() {
		return null;
	}
}
