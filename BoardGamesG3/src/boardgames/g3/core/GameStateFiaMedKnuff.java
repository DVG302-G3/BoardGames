package boardgames.g3.core;

import game.api.GameState;
import game.impl.Board;
import game.impl.BoardLocation;
//import game.impl.DieRollFactory;
import game.impl.GamePiece;
import game.impl.Move;
import game.impl.Player;
import java.io.IOException;
import static java.nio.file.Files.move;
import java.util.ArrayList;
import java.util.List;


public class GameStateFiaMedKnuff implements GameState {
	
    Board board;
    List<Player> players;
    private List<GamePiece> playerOnesPieces;
    private List<GamePiece> playerTwosPieces;
    private List<GamePiece> playerThreesPieces;
    private List<GamePiece> playerFoursPieces;
    RuleControllerFiaMedKnuff ruler;
    public final int ROWS;
    public final int COLS;
    private int numberOfPlayers;
    private Integer turnCounter = 0;
    private String message;
    
    public GameStateFiaMedKnuff(){
        this.ROWS = 11;
        this.COLS = 11;
        this.players = new ArrayList<Player>();
        this.playerOnesPieces = new ArrayList<GamePiece>();
        this.playerTwosPieces = new ArrayList<GamePiece>();
        this.playerThreesPieces = new ArrayList<GamePiece>();
        this.playerFoursPieces = new ArrayList<GamePiece>();
        this.board = new Board(createBoardLocations());
        putAllTheBeadsOnTheBoard();
        ruler = new RuleControllerFiaMedKnuff();

	}
    
    private void putAllTheBeadsOnTheBoard() {
    	for (BoardLocation b : board.getLocations()) {
    		
    		b.setPiece(new GamePiece("O"));
    		}
    	
    	GamePiece gp = new GamePiece("R");
    	
    	board.getLocations().get(60).setPiece(null);
    	
    	board.getLocations().get(00).setPiece(gp);
    	board.getLocations().get(01).setPiece(null);
    	board.getLocations().get(11).setPiece(null);
    	board.getLocations().get(12).setPiece(null);
    	
    	board.getLocations().get(9).setPiece(null);
    	board.getLocations().get(10).setPiece(null);
    	board.getLocations().get(20).setPiece(null);
    	board.getLocations().get(21).setPiece(null);
    	
    	board.getLocations().get(111).setPiece(null);
    	board.getLocations().get(110).setPiece(null);
    	board.getLocations().get(100).setPiece(null);
    	board.getLocations().get(99).setPiece(null);
    	
    	board.getLocations().get(108).setPiece(null);
    	board.getLocations().get(109).setPiece(null);
    	board.getLocations().get(119).setPiece(null);
    	board.getLocations().get(120).setPiece(null);
    	
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
    
    
    public void createPlayer(String nameForPlayer){
    	
    	if(numberOfPlayers ==0){
    		
    		Player player = new Player(nameForPlayer, playerOnesPieces);
    		players.add(player);
    		numberOfPlayers ++;
    		System.out.println(numberOfPlayers);
    		System.out.println(player.getName());
    		
    	}else if(numberOfPlayers == 1){
    		
    		Player player = new Player(nameForPlayer, playerTwosPieces);
    		players.add(player);
    		numberOfPlayers ++;
    		System.out.println(numberOfPlayers);
    		System.out.println(player.getName());
    		
    	}else if (numberOfPlayers == 2){
    		
    		Player player = new Player(nameForPlayer, playerThreesPieces);
    		players.add(player);
    		numberOfPlayers ++;
    		System.out.println(numberOfPlayers);
    		System.out.println(player.getName());
    		
    	} else {
    		
    		Player player = new Player(nameForPlayer, playerFoursPieces);
    		players.add(player);
    		numberOfPlayers ++;
    		System.out.println(numberOfPlayers);
    		System.out.println(player.getName());
    	}
    	}
    
    public void createPiecesToPlayerOne(String colorForPieces){
    	int i = 0;
    	while(!(i==4)){
    		i++;
    		GamePiece gamePiece = new GamePiece(colorForPieces+i);
    		System.out.println(gamePiece.getId());
    		playerOnesPieces.add(gamePiece);
    	}
    	
    }    
    
    public void createPiecesToPlayerTwo(String colorForPieces){
    	int i = 0;
    	while(!(i==4)){
    		i++;
    		GamePiece gamePiece = new GamePiece(colorForPieces+i);
    		System.out.println(gamePiece.getId());
    		playerTwosPieces.add(gamePiece);
    	}
    	
    }
    
    public void createPiecesToPlayerThree(String colorForPieces){
    	int i = 0;
    	while(!(i==4)){
    		i++;
    		GamePiece gamePiece = new GamePiece(colorForPieces+i);
    		System.out.println(gamePiece.getId());
    		playerThreesPieces.add(gamePiece);
    	}
    	
    }
    
    public void createPiecesToPlayerFour(String colorForPieces){
    	int i = 0;
    	while(!(i==4)){
    		i++;
    		GamePiece gamePiece = new GamePiece(colorForPieces+i);
    		System.out.println(gamePiece.getId());
    		playerFoursPieces.add(gamePiece);
    	}
    	
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
