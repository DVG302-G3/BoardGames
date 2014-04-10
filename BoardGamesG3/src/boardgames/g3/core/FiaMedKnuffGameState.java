package boardgames.g3.core;

import game.api.GameState;
import game.impl.Board;
import game.impl.Move;
import game.impl.Player;

import java.util.List;


public class FiaMedKnuffGameState implements GameState {

    private List<Player> player;
    private Board board;
    private RuleControllerFMK ruleController;
    private Integer turnCounter = 0;
	private String message;
	private int numberOfPlayers;
	
	
    public FiaMedKnuffGameState(List<Player> player, Board board, int numberOfPlayers ) {
        this.player = player;
        this.board = board;
        this.ruleController = new RuleControllerFMK();
        this.numberOfPlayers = numberOfPlayers;
    }    
	
	
	
	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public Player getLastPlayer() {
		return player.get(1 - turnCounter % numberOfPlayers);
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public Player getPlayerInTurn() {
		return player.get(turnCounter % numberOfPlayers);
	}

	@Override
	public List<Player> getPlayers() {
		return player;
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
		return null;
	}

	@Override
	public Boolean proposeMove(Move arg0) {
		return null;
	}

	@Override
	public void reset() {

	}

}
