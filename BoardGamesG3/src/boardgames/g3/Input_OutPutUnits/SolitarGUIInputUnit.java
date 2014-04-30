package boardgames.g3.Input_OutPutUnits;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.Move;
import game.io.InputUnit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boardgames.g3.BGForLabelsButtons.BackGroundButtonID;
import boardgames.g3.core.Solitaire.CounterBeadsLeft;
import boardgames.g3.core.Solitaire.SolitarHelpMethods;

public class SolitarGUIInputUnit extends InputUnit implements ActionListener {

	private GameState state;

	BackGroundButtonID buttonGUI;
	
 	BoardLocation sourceClick = null;
	BoardLocation destinationClick = null;

	@Override
	public void setup(GameState gameState) {
		this.state = gameState;
	}

	public void onClick(String coordinate) {
	
		
		if (sourceClick == null) {
			sourceClick = SolitarHelpMethods.getBoardLocationFromCoordinate(
					coordinate, state.getBoard());
			if (sourceClick.getPiece() != null)
				buttonGUI.setButtonMarked();

		} else if (sourceClick.getId().equals(coordinate)) {
			if (sourceClick.getPiece() != null)
				buttonGUI.setButtonWithBead();

			sourceClick = null;

		} else if (sourceClick != null && destinationClick == null) {
			destinationClick = SolitarHelpMethods
					.getBoardLocationFromCoordinate(coordinate,
							state.getBoard());

			notifyListenersOfMove(new Move(state.getPlayerInTurn(),
					sourceClick, destinationClick));
	
			sourceClick = null;
			destinationClick = null;
		}	
	}

	
	@Override
	public void actionPerformed(ActionEvent event) {
		buttonGUI = (BackGroundButtonID) event.getSource();

		onClick(buttonGUI.getStringId());

	}
}
