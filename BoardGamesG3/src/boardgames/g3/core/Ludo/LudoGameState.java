package boardgames.g3.core.Ludo;

import game.api.GameState;
import game.impl.Board;
import game.impl.DieRollFactory;
import game.impl.Move;
import game.impl.Player;

import java.util.ArrayList;
import java.util.List;

public class LudoGameState implements GameState {

	Board board;
	private Player winnerPlayer;
	private List<LudoPlayer> players;

	LudoRuleController ruler;
	MoveValidExecutor moveValidExec;
	MoveValidInbaseTwoPiecesExecutor moveInBaseExec;

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
		BoardAndPlayerFactory factory = new BoardAndPlayerFactory();
		factory.execute();

		moveValidExec = new MoveValidExecutor();
		moveInBaseExec = new MoveValidInbaseTwoPiecesExecutor(this);

		this.players = factory.getPlayers();
		this.board = factory.getBoard();

		ruler = new LudoRuleController(this);
		dieRollFactory.getNewRoll(getLastPlayer());
	
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
		for(LudoPlayer lp : players){
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
		LudoMoveResult result = ruler.isValidMove(move);
		System.out.println(result);
		switch (result) {
		case MOVE_VALID:
			if (ruler.needToPush(move))
				ruler.pushOtherPiece(move.getDestination());
			message = "";
			moveValidExec.executeAndMakeSureThatNoPieceWillBeDeleted(move);
			nextTurn();
			return true;		
		
		case MOVE_LAPSED:
			message = "Lapsed!";
			move.execute();
			nextTurn();
			return true;
		case MOVE_INVALID_CANT_LAPSE_AGAIN:
			message = "You cant lapsed again";
			return true;
		case MOVE_NOGAMEPIECE:
			message = LudoStaticValues.MOVE_NOGAMEPIECE;
			return false;
		case MOVE_INCORRECTNUMBEROFSTEPS:
			message = LudoStaticValues.MOVE_INCORRECTNUMBEROFSTEPS;
			return false;
		case MOVE_IN_BASE_DID_NOT_GET_THE_CORRECT_EYES_ON_THE_DICE_TO_MOVE_OUT:
			message = LudoStaticValues.MOVE_IN_BASE_DID_NOT_GET_THE_CORRECT_EYES_ON_THE_DICE_TO_MOVE_OUT;
			nextTurn();
			return false;
		case MOVE_VALID_INBASE_TWO_PIECES:
			if (ruler.needToPush(move))
				ruler.pushOtherPiece(move.getDestination());
			move.execute();
			moveInBaseExec.moveSecondPieceToStartPosition(move);
			message = "";
			nextTurn();
			return true;
		case MOVE_PIECE_IN_TO_GOAL:
			move.execute();
			return false;
		case MOVE_INVALID_CANT_LAPSE_YOUR_OWN_PIECE:
			message = LudoStaticValues.MOVE_INVALID_CANT_LAPSE_YOUR_OWN_PIECE;
			return false;
		case MOVE_INVALID_CANT_PASS_A_BLOCK:
			message = "You are not allowed to pass a block, mate.";
			return false;
		case MOVE_NO_MOVES_AVAILABLE:
			message = LudoStaticValues.MOVE_NO_MOVES_AVAILABLE;
			nextTurn();
			return false;
		case MOVE_INVALIDA_BOARDLOCATION_ALREADY_OCCUPIED:
			message = LudoStaticValues.MOVE_INVALIDA_BOARDLOCATION_ALREADY_OCCUPIED;
			return false;
		default: {
			message = "Default!";
			return false;
		}
		}
	}

	private void nextTurn() {
		if(getDieRollFactory().getLastRoll().getResult() != 6)
			turnCounter++;
		getDieRollFactory().getNewRoll(getPlayerInTurn());
	}
	
	public LudoPlayer getLudoPlayerFromPlayer(Player player){
		for(LudoPlayer p : players){
			if(p.getPlayerObject().equals(player))
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
	
}
