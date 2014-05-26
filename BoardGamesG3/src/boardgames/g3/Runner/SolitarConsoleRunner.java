package boardgames.g3.Runner;

import game.init.Runner;
import boardgames.g3.Input_OutPutUnits.SolitarConsoleIOFactory;
import boardgames.g3.core.Solitaire.SolitarGameState;

public class SolitarConsoleRunner {
	public static void main(String[] args) {
		new Runner(new SolitarGameState(), new SolitarConsoleIOFactory()).run();
	}
}
