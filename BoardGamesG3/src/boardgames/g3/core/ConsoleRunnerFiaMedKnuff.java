
package boardgames.g3.core;

import game.init.Runner;

public class ConsoleRunnerFiaMedKnuff {
    public static void main(String[] args) {
        new Runner(new GameStateFiaMedKnuff(), new IOFactoryFiaMedKnuff()).run();
    	
    	/*
    	GameStateFiaMedKnuff gameStateFiaMedKnuff = new GameStateFiaMedKnuff();
    	gameStateFiaMedKnuff.createPlayer("Johan");
    	gameStateFiaMedKnuff.createPiecesToPlayerOne("R�d");
    	gameStateFiaMedKnuff.createPlayer("Hanna");
    	gameStateFiaMedKnuff.createPiecesToPlayerTwo("Bl�");
    	gameStateFiaMedKnuff.createPlayer("Jessica");
    	gameStateFiaMedKnuff.createPiecesToPlayerThree("Gr�n");
    	gameStateFiaMedKnuff.createPlayer("Alexander");
    	gameStateFiaMedKnuff.createPiecesToPlayerFour("Gul");
    	*/
    	
    	//System.out.println(gameStateFiaMedKnuff.getLastPlayer());
    	//System.out.println(gameStateFiaMedKnuff.getPlayerInTurn());
    }
}
