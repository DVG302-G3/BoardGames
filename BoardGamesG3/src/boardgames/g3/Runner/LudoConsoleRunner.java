
package boardgames.g3.Runner;

import game.init.Runner;
import boardgames.g3.Input_OutPutUnits.LudoConsoleIOFactory;
import boardgames.g3.core.Ludo.LudoGameState;

public class LudoConsoleRunner {
    public static void main(String[] args) {
        new Runner(new LudoGameState(), new LudoConsoleIOFactory()).run();	
    }
}
