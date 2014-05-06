
package boardgames.g3.core.Ludo;

import boardgames.g3.Input_OutPutUnits.LudoConsoleIOFactory;
import game.init.Runner;

public class ConsoleRunnerFiaMedKnuff {
    public static void main(String[] args) {
        new Runner(new GameStateFiaMedKnuff(), new LudoConsoleIOFactory()).run();	
    }
}
