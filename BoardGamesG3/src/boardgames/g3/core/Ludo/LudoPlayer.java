package boardgames.g3.core.Ludo;

import game.impl.Player;

import java.util.List;

public class LudoPlayer {

	Player player;
	List<String> home;
	String startCoordinateOne;
	String startCoordinateSix;

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
		if (player.getName().equals(LudoStaticValues.GREENPLAYER)) {
			home = LudoStaticValues.GREENHOME;
			startCoordinateOne = LudoStaticValues.GREENSTART;
			startCoordinateSix = LudoStaticValues.GREENSTARTSIXES;
		} else if (player.getName().equals(LudoStaticValues.BLUEPLAYER)) {
			home = LudoStaticValues.BLUEHOME;
			startCoordinateOne = LudoStaticValues.BLUESTART;
			startCoordinateSix = LudoStaticValues.BLUESTARTSIXES;
		} else if (player.getName().equals(LudoStaticValues.REDPLAYER)) {
			home = LudoStaticValues.REDHOME;
			startCoordinateOne = LudoStaticValues.REDSTART;
			startCoordinateSix = LudoStaticValues.REDSTARTSIXES;
		} else {
			home = LudoStaticValues.YELLOWHOME;
			startCoordinateOne = LudoStaticValues.YELLOWSTART;
			startCoordinateSix = LudoStaticValues.YELLOWSTARTSIXES;
		}

	}


}
