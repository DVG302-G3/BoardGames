
package boardgames.g3.core.Ludo;

import game.init.Runner;
import boardgames.g3.Input_OutPutUnits.LudoConsoleIOFactory;

public class ConsoleRunnerFiaMedKnuff {
    public static void main(String[] args) {
        new Runner(new LudoGameState(), new LudoConsoleIOFactory()).run();	
    }
}
