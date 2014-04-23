
package boardgames.g3.core;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.Move;
import game.io.InputUnit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class InputUnitFiaMedKnuff extends InputUnit{
    BufferedReader br;

    @Override
    public void setup(GameState state) {
        br = new BufferedReader(new InputStreamReader(System.in));
        while(!state.hasEnded()){
            notifyListenersOfMove(getNextMove(state));
        }
    }
    
    private Move getNextMove(GameState state) {
        List<String> input;
        BoardLocation source = null;
        BoardLocation destination = null;
        boolean inputOK = false;
        while(!inputOK){
            try{
                
            System.out.println();
            System.out.print("Mata in nasta drag:");
            input = Arrays.asList(br.readLine().split(" "));
            
            source = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate(input.get(0), state.getBoard());
            destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate(input.get(1),state.getBoard());
            
            }catch(ArrayIndexOutOfBoundsException e){
                
                System.out.println("Felaktigt antal parametrar. Mata in enligt S DD");
                e.printStackTrace();
                
            }catch(IOException e){
                e.printStackTrace();
            }
            if(source != null && destination != null)
                inputOK = true;
            else
                System.out.println("Incorrect inmatning. Testa igen");
        }
        return new Move(state.getPlayerInTurn(),source, destination);
    }
   }
