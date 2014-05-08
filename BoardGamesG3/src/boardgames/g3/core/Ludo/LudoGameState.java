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

public class LudoGameState implements GameState {

	Board board;
	List<Player> players;
	private Player winnerPlayer;

	LudoRuleController ruler;

	private int numberOfPlayers = 4;

	private Integer turnCounter = 0;
	private String message;
	DieRollFactory dieRollFactory;

	public LudoGameState() {
		this.dieRollFactory = new DieRollFactory();
		startToPlayNewGame();

	}
	
	public LudoGameState(int noPlayers){
		this.numberOfPlayers = noPlayers;
	}
	
	public void startToPlayNewGame() {
		this.players = createAndReturnPlayers();
		this.board = new Board(createBoardLocations());
		ruler = new LudoRuleController(this);
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
			message = "";
			move.execute();
			nextPlayer();
			return true;
		case MOVE_LAPSED:
			return false;
		case MOVE_NOGAMEPIECE:
			message = "No game piece located in source.";
			return false;
		case MOVE_INCORRECTNUMBEROFSTEPS:
			message = "You can't move to this position. Please try again.";
			return false;			
		case MOVE_IN_BASE_DID_NOT_GET_THE_CORRECT_EYES_ON_THE_DICE_TO_MOVE_OUT:
			message = "You need to get 1 or 6 in order to move out of base.";
			nextPlayer();
			return false;
		case MOVE_VALID_INBASE_TWO_PIECES:
			System.out.println("Two pieces!!!!!");
			if (needToPush(move))
				ruler.pushOtherPiece(move.getDestination().getPiece());
			message = "";
			move.execute();
			moveSecondPieceToStartPosition(move);
			nextPlayer();
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
		if(move.getPlayer().getName().equals("Red"))
			moveSecondPieceForPlayer(move, LudoStaticValues.REDHOME, LudoStaticValues.REDSTART);
		else 	if(move.getPlayer().getName().equals("Yellow"))
			moveSecondPieceForPlayer(move, LudoStaticValues.YELLOWHOME, LudoStaticValues.YELLOWSTART);
		else 	if(move.getPlayer().getName().equals("Green"))
			moveSecondPieceForPlayer(move, LudoStaticValues.GREENHOME, LudoStaticValues.GREENSTART);
		else			
			moveSecondPieceForPlayer(move, LudoStaticValues.BLUEHOME, LudoStaticValues.BLUESTART);
	}

	private void moveSecondPieceForPlayer(Move move, List<String> homeLocations, String startLocation) {
		for (String basePositions : homeLocations) {
			BoardLocation home = HelpMethodsFinaMedKnuff
					.getBoardLocationFromCoordinate(basePositions,
							getBoard());
			if (home.getPiece() != null
					&& home.getPiece() != move.getPiece()) {
				BoardLocation start = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate(startLocation, board);
				start.addPiece(home.getPiece());
				home.clear();
				break;
			}
		}
	}

	private boolean needToPush(Move move) {
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
