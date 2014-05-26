package boardgames.g3.Input_OutPutUnits;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.Move;
import game.io.InputUnit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boardgames.g3.BGForLabelsButtons.BackGroundButtonIDLudo;
import boardgames.g3.core.Ludo.LudoStaticValues;
import boardgames.g3.core.Solitaire.SolitarHelpMethods;

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
		if (checkIfSourceMarked()) {
			sourceClick = SolitarHelpMethods.getBoardLocationFromCoordinate(
					coordinate, gameState.getBoard());

			if (checkIfBoardlocationEmpty()) {
				if (checkPlayer(LudoStaticValues.REDPLAYER)) {
					if (checkSourceForPlayerPiece())
						setButtonIconMarked(LudoStaticValues.REDPLAYER);
					else
						sourceClick = null;

				} else if (checkPlayer(LudoStaticValues.BLUEPLAYER)) {
					if (checkSourceForPlayerPiece())
						setButtonIconMarked(LudoStaticValues.BLUEPLAYER);
					else
						sourceClick = null;

				} else if (checkPlayer(LudoStaticValues.YELLOWPLAYER)) {
					if (checkSourceForPlayerPiece())
						setButtonIconMarked(LudoStaticValues.YELLOWPLAYER);
					else
						sourceClick = null;

				} else if (checkPlayer(LudoStaticValues.GREENPLAYER)) {
					if (checkSourceForPlayerPiece())
						setButtonIconMarked(LudoStaticValues.GREENPLAYER);
					else
						sourceClick = null;
				}
			}

		} else if (checkSourceEqualsDestination(coordinate)) {
			if (checkIfBoardlocationEmpty()) {
				if (checkPlayer(LudoStaticValues.REDPLAYER)) {
					buttonGUI.setButtonIconOneOrTwo(LudoStaticValues.REDPLAYER, LudoStaticValues.REDFINISHLINE);

				} else if (checkPlayer(LudoStaticValues.BLUEPLAYER)) {
					buttonGUI.setButtonIconOneOrTwo(LudoStaticValues.BLUEPLAYER, LudoStaticValues.BLUEFINISHLINE);

				} else if (checkPlayer(LudoStaticValues.YELLOWPLAYER)) {
					buttonGUI.setButtonIconOneOrTwo(LudoStaticValues.YELLOWPLAYER, LudoStaticValues.YELLOWFINISHLINE);
					
				} else if (checkPlayer(LudoStaticValues.GREENPLAYER)) {
					buttonGUI.setButtonIconOneOrTwo(LudoStaticValues.GREENPLAYER, LudoStaticValues.GREENFINISHLINE);
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

	private boolean checkSourceEqualsDestination(String coordinate) {
		return sourceClick.getId().equals(coordinate);
	}

	private boolean checkIfBoardlocationEmpty() {
		return sourceClick.getPiece() != null;
	}

	private boolean checkIfSourceMarked() {
		return sourceClick == null;
	}

	private void setButtonIconMarked(String pathURL) {

		if (sourceClick.getPieces().size() == 1)
			buttonGUI.setButtonIcon(pathURL.toLowerCase() + "PieceMarked.png");
		else
			buttonGUI.setButtonIcon(pathURL.toLowerCase()
					+ "PieceTwoMarked.png");
	}

	private Boolean checkSourceForPlayerPiece() {
		return gameState.getPlayerInTurn().hasPiece(sourceClick.getPiece());
	}

	private boolean checkPlayer(String player) {
		return gameState.getPlayerInTurn().getName().equals(player);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buttonGUI = (BackGroundButtonIDLudo) e.getSource();

		onClick(buttonGUI.getStringId());
	}

}
