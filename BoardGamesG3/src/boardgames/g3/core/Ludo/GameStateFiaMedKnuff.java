package boardgames.g3.core.Ludo;

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
	private Player winnerPlayer;

	RuleControllerFiaMedKnuff ruler;
	public final int ROWS;
	public final int COLS;
	private int numberOfPlayers = 4;

	private Integer turnCounter = 0;
	private String message;
	DieRollFactory dieRollFactory;

	public GameStateFiaMedKnuff() {
		this.ROWS = 11;
		this.COLS = 11;
		this.dieRollFactory = new DieRollFactory();
		startToPlayNewGame();

	}

	public void startToPlayNewGame() {
		this.players = createAndReturnPlayers();
		this.board = new Board(createBoardLocations());
		ruler = new RuleControllerFiaMedKnuff(this);
		addPlayersPiecesToTheBoard();
		dieRollFactory.getNewRoll(getPlayerInTurn());

	}

	private List<Player> createAndReturnPlayers() {
		List<Player> players = new ArrayList<>();
		players.add(createAndReturnPlayer("Red",
				Arrays.asList("R1", "R2", "R3", "R4")));
		players.add(createAndReturnPlayer("Blue",
				Arrays.asList("B1", "B2", "B3", "B4")));
		players.add(createAndReturnPlayer("Yellow",
				Arrays.asList("Y1", "Y2", "Y3", "Y4")));
		players.add(createAndReturnPlayer("Green",
				Arrays.asList("G1", "G2", "G3", "G4")));
		return players;
	}

	private void addPlayersPiecesToTheBoard() {
		for (Player p : players) {
			List<GamePiece> pieces = p.getPieces();
			List<String> homePositions;
			if (p.getName().equals("Green"))
				homePositions = LudoStaticValues.GREENHOME;
			else if (p.getName().equals("Blue"))
				homePositions = LudoStaticValues.BLUEHOME;
			else if (p.getName().equals("Red"))
				homePositions = LudoStaticValues.REDHOME;
			else
				homePositions = LudoStaticValues.YELLOWHOME;

			int listIndex = 0;
			for (GamePiece gp : pieces) {
				int index = HelpMethodsFinaMedKnuff
						.getFlatListIndexFromCoordinate(
								homePositions.get(listIndex++), board);
				board.getLocations().get(index).setPiece(gp);

			}

		}
	}

	private Player createAndReturnPlayer(String color,
			List<String> gamePieceNames) {

		List<GamePiece> gamePieces = new ArrayList<GamePiece>();
		gamePieces.add(new GamePiece(gamePieceNames.get(0)));
		gamePieces.add(new GamePiece(gamePieceNames.get(1)));
		gamePieces.add(new GamePiece(gamePieceNames.get(2)));
		gamePieces.add(new GamePiece(gamePieceNames.get(3)));

		return new Player(color, gamePieces);
	}

	private List<BoardLocation> createBoardLocations() {
		List<BoardLocation> boardLocations = new ArrayList<BoardLocation>();
		try {
			List<String> listOfPositions = FileHandlerFiaMedKnuff
					.readCoordinate();
			for (String s : listOfPositions) {
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
		if (turnCounter == 0) {
			return players.get(0);
		} else
			return players.get(turnCounter-- % numberOfPlayers);
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
	public Boolean hasEnded() {
		if (players.size() == 1) {
			return true;
		} else
			return false;
	}

	@Override
	public Boolean proposeMove(Move move) {
		LudoMoveResult result = ruler.isValidMove(move);
		switch (result) {
		case MOVE_VALID:
			if (needToPush(move))
				ruler.pushOtherPiece(move.getDestination().getPiece());
			move.execute();
			nextPlayer();
			return true;
		case MOVE_LAPSED:
			System.out.println("Lapsed");
			return false;
		case MOVE_NOGAMEPIECE:
			System.out.println("No game piece");
			return false;
		case MOVE_INCORRECTNUMBEROFSTEPS:
			System.out.println("Incorrect number of steps!");
			return false;
		case MOVE_VALID_INBASE_TWO_PIECES:
			System.out.println("Two pieces!!!!! To do!");
			if (needToPush(move))
				ruler.pushOtherPiece(move.getDestination().getPiece());
			move.execute();
			moveSecondPieceToStartPosition(move);
			return true;
		default:
			return false;
		}
	}

	private void nextPlayer() {
		turnCounter++;
		getDieRollFactory().getNewRoll(getPlayerInTurn());
	}

	private void moveSecondPieceToStartPosition(Move move) {
		if(move.getPlayer().getName().equals("Red")){
			for (String basePositions : LudoStaticValues.REDHOME) {
				BoardLocation home = HelpMethodsFinaMedKnuff
						.getBoardLocationFromCoordinate(basePositions,
								getBoard());
				if (home.getPiece() != null
						&& home.getPiece() != move.getPiece()) {
					BoardLocation start = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate(LudoStaticValues.REDSTART, board);
					start.addPiece(home.getPiece());
				}
			}

		}
		else 	if(move.getPlayer().getName().equals("Yellow")){
			
		}
		else 	if(move.getPlayer().getName().equals("Green")){
			
		}
		else{
			
		}
	}

	private boolean needToPush(Move move) {
		System.out.println("need to push!");
		return move.getDestination().getPiece() != null;
	}

	@Override
	public void reset() {
		startToPlayNewGame();
	}

	@Override
	public DieRollFactory getDieRollFactory() {
		return dieRollFactory;
	}

	@Override
	public Player getWinner() {
		return winnerPlayer;
	}
}
