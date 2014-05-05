
package boardgames.g3.core;

import game.init.Runner;

public class ConsoleRunnerFiaMedKnuff {
    public static void main(String[] args) {
        new Runner(new GameStateFiaMedKnuff(), new IOFactoryFiaMedKnuff()).run();
    	
    	
    	
    }
}
