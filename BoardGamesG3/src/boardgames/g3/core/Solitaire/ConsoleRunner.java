package boardgames.g3.core.Solitaire;

import boardgames.g3.Input_OutPutUnits.SolitarConsoleIOFactory;
import game.init.Runner;

public class ConsoleRunner{
	public static void main(String[] args) {
        new Runner(new SolitarGameState(), new SolitarConsoleIOFactory()).run();
    }

}