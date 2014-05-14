package boardgames.g3.core.Ludo;

import game.api.GameState;
import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.DieRollFactory;
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
	private String message = "";
	DieRollFactory dieRollFactory;

	public LudoGameState() {
		this.dieRollFactory = new DieRollFactory();
		startToPlayNewGame();

	}

	public LudoGameState(int noPlayers) {
		this.numberOfPlayers = noPlayers;
		this.dieRollFactory = new DieRollFactory();
		startToPlayNewGame();
	}

	public void startToPlayNewGame() {
		this.players = createAndReturnPlayers();
		this.board = new Board(createBoardLocations());
		ruler = new LudoRuleController(this);
		addPlayersPiecesToTheBoard();
		dieRollFactory.getNewRoll(getLastPlayer());

	}

	private List<Player> createAndReturnPlayers() {
		List<Player> players = new ArrayList<>();
		players.add(createAndReturnPlayer(LudoStaticValues.REDPLAYER,
				LudoStaticValues.REDPIECES));
		players.add(createAndReturnPlayer(LudoStaticValues.BLUEPLAYER,
				LudoStaticValues.BLUEPIECES));
		players.add(createAndReturnPlayer(LudoStaticValues.YELLOWPLAYER,
				LudoStaticValues.YELLOWPIECES));
		players.add(createAndReturnPlayer(LudoStaticValues.GREENPLAYER,
				LudoStaticValues.GREENPIECES));
		return players;
	}

	private void addPlayersPiecesToTheBoard() {
		for (Player p : players) {
			List<GamePiece> pieces = p.getPieces();
			List<String> homePositions;
			if (p.getName().equals(LudoStaticValues.GREENPLAYER))
				homePositions = LudoStaticValues.GREENHOME;
			else if (p.getName().equals(LudoStaticValues.BLUEPLAYER))
				homePositions = LudoStaticValues.BLUEHOME;
			else if (p.getName().equals(LudoStaticValues.REDPLAYER))
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
			return players.get(turnCounter % numberOfPlayers);
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
		System.out.println(result);
		switch (result) {
		case MOVE_VALID:
			if (needToPush(move))
				ruler.pushOtherPiece(move.getDestination());
			message = "";
			executeAndMakeSureThatNoPieceWillBeDeleted(move);
			System.out.println("Pj�ser p� platsen: "
					+ move.getDestination().getPieces().size());
			nextPlayer();
			return true;
		case MOVE_LAPSED:
			message = "Lapsed!";
			move.execute();
			nextPlayer();
			return true;
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
			System.out.println("Valid 2 pieces!!!!");
			if (needToPush(move))
				ruler.pushOtherPiece(move.getDestination());
			message = "";
			move.execute();
			moveSecondPieceToStartPosition(move);
			nextPlayer();
			return true;
		case MOVE_PIECE_IN_TO_GOAL:
			move.execute();
			return false;
		case MOVE_INVALID_CANT_LAPSE_YOUR_OWN_PIECE:
			message = "You are not allowed to pass your own piece, mate.";
			return false;
		default: {
			message = "Default!!!!!!!!!!!!";
			return false;
		}
		}
	}

	private void nextPlayer() {
		turnCounter++;
		getDieRollFactory().getNewRoll(getPlayerInTurn());
	}

	private void executeAndMakeSureThatNoPieceWillBeDeleted(Move move) {
		GamePiece sourcePiece = checkSourceForDoublePieces(move);
		GamePiece destinationPiece = checkDestinationForExistingPieces(move);

		move.execute();
		if (destinationPiece != null)
			move.getDestination().addPiece(destinationPiece);

		if (sourcePiece != null)
			move.getSource().addPiece(sourcePiece);
	}

	private GamePiece checkSourceForDoublePieces(Move move) {
		move.getSource().getPieces().size();
		if (move.getSource().getPieces().size() > 1) {
			return move.getSource().getPieces().get(1);
		}
		return null;
	}

	private GamePiece checkDestinationForExistingPieces(Move move) {
		return move.getDestination().getPiece();
	}

	private void moveSecondPieceToStartPosition(Move move) {
		if (move.getPlayer().getName().equals(LudoStaticValues.REDPLAYER))
			moveSecondPieceForPlayer(move, LudoStaticValues.REDHOME,
					LudoStaticValues.REDSTART);
		else if (move.getPlayer().getName().equals(LudoStaticValues.YELLOWPLAYER))
			moveSecondPieceForPlayer(move, LudoStaticValues.YELLOWHOME,
					LudoStaticValues.YELLOWSTART);
		else if (move.getPlayer().getName().equals(LudoStaticValues.GREENPLAYER))
			moveSecondPieceForPlayer(move, LudoStaticValues.GREENHOME,
					LudoStaticValues.GREENSTART);
		else
			moveSecondPieceForPlayer(move, LudoStaticValues.BLUEHOME,
					LudoStaticValues.BLUESTART);
	}

	private void moveSecondPieceForPlayer(Move move,
			List<String> homeLocations, String startLocation) {
		for (String basePositions : homeLocations) {
			BoardLocation home = HelpMethodsFinaMedKnuff
					.getBoardLocationFromCoordinate(basePositions, getBoard());
			if (home.getPiece() != null && home.getPiece() != move.getPiece()) {
				BoardLocation start = HelpMethodsFinaMedKnuff
						.getBoardLocationFromCoordinate(startLocation, board);
				start.addPiece(home.getPiece());
				home.clear();
				break;
			}
		}
	}

	private boolean needToPush(Move move) {
		GamePiece destinationPiece = move.getDestination().getPiece();
		System.out.println("Has Piece: "
				+ move.getPlayer().hasPiece(destinationPiece));
		if (move.getPlayer().hasPiece(destinationPiece))
			return false;
		else
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
