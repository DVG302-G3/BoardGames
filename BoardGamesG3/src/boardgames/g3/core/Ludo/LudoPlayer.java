package boardgames.g3.core.Ludo;

import game.impl.Player;

import java.util.List;

public class LudoPlayer {

	private Player player;
	private List<String> home;
	private List<String> finishLine;
	private String startCoordinateOne;
	private String startCoordinateSix;

	public LudoPlayer(Player player) {
		this.player = player;
		getDataBasedOnPlayerColor();

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

}
