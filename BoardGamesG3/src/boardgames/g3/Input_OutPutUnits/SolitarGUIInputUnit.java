package boardgames.g3.Input_OutPutUnits;

import javax.naming.ldap.SortControl;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.Move;
import game.io.InputUnit;
import boardgames.g3.core.Solitaire.SolitarGameState;
import boardgames.g3.core.Solitaire.SolitarHelpMethods;

public class SolitarGUIInputUnit extends InputUnit {

	public GameState state = new SolitarGameState();

	BoardLocation sourceClick = null;
	BoardLocation destinationClick = null;

	@Override
	public void setup(GameState arg0) {
		state = arg0;

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
	    
	    state.proposeMove(new Move(state.getPlayerInTurn(), sourceClick,
	    	      destinationClick));
   
    sourceClick = null;
    destinationClick = null;
   }

 
 }
}
