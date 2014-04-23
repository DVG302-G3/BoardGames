
package boardgames.g3.core;

import game.init.Runner;

public class ConsoleRunnerFiaMedKnuff {
    public static void main(String[] args) {
        new Runner(new GameStateFiaMedKnuff(), new IOFactoryFiaMedKnuff()).run();
    	
    	/*
    	GameStateFiaMedKnuff gameStateFiaMedKnuff = new GameStateFiaMedKnuff();
    	gameStateFiaMedKnuff.createPlayer("Johan");
    	gameStateFiaMedKnuff.createPiecesToPlayerOne("Röd");
    	gameStateFiaMedKnuff.createPlayer("Hanna");
    	gameStateFiaMedKnuff.createPiecesToPlayerTwo("Blå");
    	gameStateFiaMedKnuff.createPlayer("Jessica");
    	gameStateFiaMedKnuff.createPiecesToPlayerThree("Grön");
    	gameStateFiaMedKnuff.createPlayer("Alexander");
    	gameStateFiaMedKnuff.createPiecesToPlayerFour("Gul");
    	*/
    	
    	//System.out.println(gameStateFiaMedKnuff.getLastPlayer());
    	//System.out.println(gameStateFiaMedKnuff.getPlayerInTurn());
    }
}
