package boardgames.g3.core;

import game.api.GameState;
import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.DieRollFactory;
import game.impl.GamePiece;
import game.impl.Move;
import game.impl.Player;
import java.io.IOException;
import static java.nio.file.Files.move;
import java.util.ArrayList;
import java.util.List;


public class GameStateFiaMedKnuff implements GameState {
    Board board;
    Player player;
    RuleControllerFiaMedKnuff ruler;
    public final int ROWS;
    public final int COLS;
    private int numberOfPlayers;
    private Integer turnCounter = 0;
    private String message;
    
    public GameStateFiaMedKnuff(){
        this.ROWS = 11;
        this.COLS = 11;
        this.board = new Board(createBoardLocations());
        putAllTheBeadsOnTheBoard();
        this.player = new Player("Playah!", null);
        ruler = new RuleControllerFiaMedKnuff();

	}
	
	/*
    public GameStateFiaMedKnuff(List<Player> player, Board board, int numberOfPlayers ) {
        this.player = player;
        this.board = board;
        this.ruleController = new RuleControllerFiaMedKnuff();
        this.numberOfPlayers = numberOfPlayers;
        this.COLS = 11;
        this.ROWS = 11;
    }*/
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
			listOfRows = FileHandlerFiaMedKnuff.readCoordinate();
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
    
    /*
    public int createPlayers(){
    	
    	return 0;
    }
    */
    
	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public Player getLastPlayer() {
		return null;//player.get(1 - turnCounter % numberOfPlayers);
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public Player getPlayerInTurn() {
		return null;//player.get(turnCounter % numberOfPlayers);
	}

	@Override
	public List<Player> getPlayers() {
		return null;//player;
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

		BoardLocation middle = HelpMethodsFinaMedKnuff
				.getBoardLocationFromCoordinate(middleLoc, board);
		middle.setPiece(null);

	}
        /*

    @Override
    public DieRollFactory getDieRollFactory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        */

}
