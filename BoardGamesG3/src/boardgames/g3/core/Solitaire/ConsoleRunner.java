package boardgames.g3.core.Solitaire;

import game.init.Runner;
import boardgames.g3.Input_OutPutUnits.SolitarConsoleIOFactory;

public class ConsoleRunner {
	public static void main(String[] args) {
		new Runner(new SolitarGameState(), new SolitarConsoleIOFactory()).run();
	}
}
