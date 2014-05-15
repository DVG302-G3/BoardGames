package boardgames.g3.core.Ludo;

import game.api.GameState;
import game.impl.Board;
import game.impl.DieRollFactory;
import game.impl.Move;
import game.impl.Player;

import java.util.List;

public class LudoGameState implements GameState {

	Board board;
	List<Player> players;
	private Player winnerPlayer;

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
		
		testSetup();

	}

	private void testSetup(){

		HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EC", board).setPiece(players.get(1).getPieces().get(0));
		HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BI", board).clear();

		HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EC", board).addPiece(players.get(1).getPieces().get(1));
		HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BJ", board).clear();

		HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("EA", board).setPiece(players.get(0).getPieces().get(0));
		HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", board).clear();

		
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
			message = "No game piece located in source.";
			return false;
		case MOVE_INCORRECTNUMBEROFSTEPS:
			message = "You can't move to this position. Please try again.";
			return false;
		case MOVE_IN_BASE_DID_NOT_GET_THE_CORRECT_EYES_ON_THE_DICE_TO_MOVE_OUT:
			message = "You need to get 1 or 6 in order to move out of base.";
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
			message = "You are not allowed to pass your own piece, mate.";
			return false;
		case MOVE_INVALID_CANT_PASS_A_BLOCK:
			message = "You are not allowed to pass a block, mate.";
			return false;
		case MOVE_NO_MOVES_AVAILABLE:
			message = "No moves available.";
			nextTurn();
			return false;
		case MOVE_INVALIDA_BOARDLOCATION_ALREADY_OCCUPIED:
			message = "Boardlocation can't hold that many pieces!";
			return false;
		default: {
			message = "Default!!!!!!!!!!!!";
			return false;
		}
		}
	}

	private void nextTurn() {
		if(getDieRollFactory().getLastRoll().getResult() != 6)
			turnCounter++;
		getDieRollFactory().getNewRoll(getPlayerInTurn());
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
