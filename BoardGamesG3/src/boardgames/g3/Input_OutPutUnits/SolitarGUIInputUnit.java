package boardgames.g3.Input_OutPutUnits;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.Move;
import game.io.InputUnit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boardgames.g3.core.FiaMedKnuff.GUIGridButtonID;
import boardgames.g3.core.Solitaire.SolitarHelpMethods;

public class SolitarGUIInputUnit extends InputUnit implements ActionListener {

	public GameState state;

	BoardLocation sourceClick = null;
	BoardLocation destinationClick = null;

	@Override
	public void setup(GameState gameState) {
		this.state = gameState;
	}

	public void onClick(String coordinate) {

		if (sourceClick == null) {
	    sourceClick = SolitarHelpMethods.getBoardLocationFromCoordinate(coordinate,
	      state.getBoard());
	
	   }else if (sourceClick.getId().equals(coordinate)){
		   sourceClick = null;
	   
	   }else if (sourceClick != null && destinationClick == null) {
	    destinationClick = SolitarHelpMethods.getBoardLocationFromCoordinate(
	      coordinate, state.getBoard());
	
	    
	    notifyListenersOfMove(new Move(state.getPlayerInTurn(), sourceClick,
	    	      destinationClick));
   
    sourceClick = null;
    destinationClick = null;
   }

 
 }

	@Override
	public void actionPerformed(ActionEvent event) {
		GUIGridButtonID buttonGUI = (GUIGridButtonID) event.getSource();
		onClick(buttonGUI.getStringId());		
	}
}
