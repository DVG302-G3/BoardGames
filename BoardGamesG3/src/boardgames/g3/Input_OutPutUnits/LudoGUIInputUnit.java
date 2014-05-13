package boardgames.g3.Input_OutPutUnits;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boardgames.g3.BGForLabelsButtons.BackGroundButtonIDLudo;
import boardgames.g3.core.Ludo.LudoStaticValues;
import boardgames.g3.core.Solitaire.SolitarHelpMethods;
import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.Move;
import game.io.InputUnit;

public class LudoGUIInputUnit extends InputUnit implements ActionListener {

	GameState gameState;

	BackGroundButtonIDLudo buttonGUI;

	BoardLocation sourceClick = null;
	BoardLocation destinationClick = null;

	@Override
	public void setup(GameState gameState) {
		this.gameState = gameState;

	}

	public void onClick(String coordinate) {
		if (sourceClick == null) {
			sourceClick = SolitarHelpMethods.getBoardLocationFromCoordinate(
					coordinate, gameState.getBoard());
			if (sourceClick.getPiece() != null) {

				if (gameState.getLastPlayer().getName().equals(LudoStaticValues.REDPLAYER)) {
					if(gameState.getLastPlayer().hasPiece(sourceClick.getPiece()))
						buttonGUI.setButtonWithRedMarked();
					else 
						sourceClick = null;
					
				} else if (gameState.getLastPlayer().getName().equals(LudoStaticValues.BLUEPLAYER)) {
					if(gameState.getLastPlayer().hasPiece(sourceClick.getPiece()))
						buttonGUI.setButtonWithBlueMarked();
					else 
						sourceClick = null;

				} else if (gameState.getLastPlayer().getName().equals(LudoStaticValues.YELLOWPLAYER)) {
					if(gameState.getLastPlayer().hasPiece(sourceClick.getPiece()))
						buttonGUI.setButtonWithYellowMarked();
					else 
						sourceClick = null;

				} else if (gameState.getLastPlayer().getName().equals(LudoStaticValues.GREENPLAYER)) {
					if(gameState.getLastPlayer().hasPiece(sourceClick.getPiece()))
						buttonGUI.setButtonWithGreenMarked();
					else 
						sourceClick = null;

				}
			}

		} else if (sourceClick.getId().equals(coordinate)) {
			if (sourceClick.getPiece() != null) {
				if (gameState.getLastPlayer().getName().equals(LudoStaticValues.REDPLAYER)) {
					buttonGUI.setButtonWithRed();

				} else if (gameState.getLastPlayer().getName().equals(LudoStaticValues.BLUEPLAYER)) {
					buttonGUI.setButtonWithBlue();

				} else if (gameState.getLastPlayer().getName().equals(LudoStaticValues.YELLOWPLAYER)) {
					buttonGUI.setButtonWithYellow();

				} else if (gameState.getLastPlayer().getName().equals(LudoStaticValues.GREENPLAYER)) {
					buttonGUI.setButtonWithGreen();
				}
			}

			sourceClick = null;

		} else if (sourceClick != null && destinationClick == null) {
			destinationClick = SolitarHelpMethods
					.getBoardLocationFromCoordinate(coordinate,
							gameState.getBoard());

			notifyListenersOfMove(new Move(gameState.getPlayerInTurn(),
					sourceClick, destinationClick));

			sourceClick = null;
			destinationClick = null;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buttonGUI = (BackGroundButtonIDLudo) e.getSource();

		onClick(buttonGUI.getStringId());
	}

}
