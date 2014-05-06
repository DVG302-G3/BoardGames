package boardgames.g3.JUnitTests;
import static org.junit.Assert.*;
import game.impl.BoardLocation;
import game.impl.Move;

import org.junit.*;

import boardgames.g3.core.Ludo.LudoGameState;
import boardgames.g3.core.Ludo.HelpMethodsFinaMedKnuff;


public class LudoRuleControllerTest {
	
	LudoGameState state;
	
	@Before
	public void setup(){
		state = new LudoGameState();
	}
	@Test
	public void test() {
		BoardLocation source = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate("BB", state.getBoard());
		int index = HelpMethodsFinaMedKnuff.getFlatListIndexFromCoordinate("BB", state.getBoard());
		int dice = 5;
		int newIndex = index+dice;
		BoardLocation destination = state.getBoard().getLocations().get(newIndex);
		
		state.proposeMove(new Move(state.getLastPlayer(), source, destination));
		assertNotEquals(state.getBoard().getLocations().get(4).getPiece(), null);
	}

}
