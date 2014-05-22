package boardgames.g3.core.Ludo;

import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.Player;

import java.util.ArrayList;
import java.util.List;

public class LudoPlayer {

	private Player player;
	private List<String> home;
	private List<String> finishLine;
	private List<BoardLocation> playersBoardlist;
	private String startCoordinateOne;
	private String startCoordinateSix;
	private int piecesLeft;

	public LudoPlayer(Player player, Board board) {
		this.player = player;
		getDataBasedOnPlayerColor();
		piecesLeft = player.getPieces().size();
		initiatePlayersBoardList(board);
	}

	private void initiatePlayersBoardList(Board board) {
		playersBoardlist = new ArrayList<BoardLocation>();
		List<BoardLocation> boardList = board.getLocations();
		
		BoardLocation start = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate(startCoordinateOne, board);
		int index = boardList.indexOf(start);
		boolean lapsed = false;
		
		while(!lapsed){
			playersBoardlist.add(boardList.get(index));

			if(boardList.get(index+1).equals(start))
				lapsed = true;
			
			index = (index+1) % LudoStaticValues.TOTALSTEPSAROUNDTHEBOARD;
		}
		
		for(String s : finishLine){
			BoardLocation finishLineLocation = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate(s, board);
			playersBoardlist.add(finishLineLocation);
		}
		
		BoardLocation goal = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate(LudoStaticValues.GOAL, board);
		playersBoardlist.add(goal);
	
	
	}

	public List<BoardLocation> getBoardList(){
		return playersBoardlist;
	}
	
	public Player getPlayerObject() {
		return player;
	}

	public List<String> getHomePositions() {
		return home;
	}

	public String getStartCoordinateOne() {
		return startCoordinateOne;
	}

	public String getStartCoordinateSix() {
		return startCoordinateSix;
	}

	private void getDataBasedOnPlayerColor() {
		switch (player.getName()) {
		case LudoStaticValues.GREENPLAYER:
			home = LudoStaticValues.GREENHOME;
			finishLine = LudoStaticValues.GREENFINISHLINE;
			startCoordinateOne = LudoStaticValues.GREENSTART;
			startCoordinateSix = LudoStaticValues.GREENSTARTSIXES;
			break;
		case LudoStaticValues.BLUEPLAYER:
			home = LudoStaticValues.BLUEHOME;
			finishLine = LudoStaticValues.BLUEFINISHLINE;
			startCoordinateOne = LudoStaticValues.BLUESTART;
			startCoordinateSix = LudoStaticValues.BLUESTARTSIXES;
			break;
		case LudoStaticValues.REDPLAYER:
			home = LudoStaticValues.REDHOME;
			finishLine = LudoStaticValues.REDFINISHLINE;
			startCoordinateOne = LudoStaticValues.REDSTART;
			startCoordinateSix = LudoStaticValues.REDSTARTSIXES;
			break;
		case LudoStaticValues.YELLOWPLAYER:
			home = LudoStaticValues.YELLOWHOME;
			finishLine = LudoStaticValues.YELLOWFINISHLINE;
			startCoordinateOne = LudoStaticValues.YELLOWSTART;
			startCoordinateSix = LudoStaticValues.YELLOWSTARTSIXES;

		}

	}
	
	public List<String> getFinishLine() {
		return finishLine;
	}
	
	public int getPiecesLeft(){
		return piecesLeft;
	}
	
	public int aPieceJustEnteredGoal(){
		return --piecesLeft;
	}

}
