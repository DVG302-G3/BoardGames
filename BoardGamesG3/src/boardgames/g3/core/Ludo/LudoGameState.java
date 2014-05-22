package boardgames.g3.core.Ludo;

import game.api.GameState;
import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.DieRollFactory;
import game.impl.Move;
import game.impl.Player;

import java.util.ArrayList;
import java.util.List;

public class LudoGameState implements GameState {

	Board board;
	private Player winnerPlayer;
	private List<LudoPlayer> players;
	private LudoMoveController ludoMoveController;

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

		BoardAndPlayerFactory factory = new BoardAndPlayerFactory(numberOfPlayers);
		factory.execute();
		
		this.players = factory.getPlayers();
		this.board = factory.getBoard();

		dieRollFactory.getNewRoll(getLastPlayer());
		ludoMoveController = new LudoMoveController(this);
		
		
		HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("FE", board).addPiece(getPlayerInTurn().getPieces().get(0));
		HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("FD", board).addPiece(getPlayerInTurn().getPieces().get(1));
		HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("FC", board).addPiece(getPlayerInTurn().getPieces().get(2));
		HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("FB", board).addPiece(getPlayerInTurn().getPieces().get(3));

		HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", board).clear();
		HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BC", board).clear();
		HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("CB", board).clear();
		HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("CC", board).clear();
		
		
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public Player getLastPlayer() {
		if (turnCounter == 0) {
			return players.get(0).getPlayerObject();
		} else
			return players.get(turnCounter % numberOfPlayers).getPlayerObject();
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public Player getPlayerInTurn() {
		return players.get(turnCounter % numberOfPlayers).getPlayerObject();
	}

	@Override
	public List<Player> getPlayers() {
		List<Player> playerObjects = new ArrayList<Player>();
		for (LudoPlayer lp : players) {
			playerObjects.add(lp.getPlayerObject());
		}
		return playerObjects;
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
		return ludoMoveController.proposeMove(move);
	}

	public void nextTurn() {
		if (getDieRollFactory().getLastRoll().getResult() != 6)
			turnCounter++;
		getDieRollFactory().getNewRoll(getPlayerInTurn());
	}

	public LudoPlayer getLudoPlayerFromPlayer(Player player) {
		for (LudoPlayer p : players) {
			if (p.getPlayerObject().equals(player))
				return p;
		}
		return null;
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
	
	public void setWinner(Player player){
		this.winnerPlayer = player;
	}

	public void setMessage(String newMessage) {
		message = newMessage;
	}

	public void removePlayer(LudoPlayer player) {
		players.remove(player);
	}

}
