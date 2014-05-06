
package boardgames.g3.Input_OutPutUnits;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Move;
import game.impl.Player;
import game.io.InputUnit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import boardgames.g3.core.Ludo.HelpMethodsFinaMedKnuff;

public class LudoConsoleInputUnit extends InputUnit{
    BufferedReader br;

    @Override
    public void setup(GameState state) {
        br = new BufferedReader(new InputStreamReader(System.in));
        while(!state.hasEnded()){
            notifyListenersOfMove(getNextMove(state));
        }
    }
    
    private Move getNextMove(GameState state) {
    	
    	Player player = null;
    	List<String> input;
    	int dieRollNumber;
        BoardLocation source = null;
        BoardLocation destination = null;
        boolean inputOK = false;

        dieRollNumber = state.getDieRollFactory().getNewRoll(player).getResult();
        player = state.getPlayerInTurn(); 
       

        
        while(!inputOK){
            try{
            	System.out.println();
            	System.out.print(player.getName()+" "+"antal steg du f�r g� med pj�s �r:"+" "+dieRollNumber);
            	System.out.println();
            	System.out.print("Vilken pj�s vill du flytta:");
            	input = Arrays.asList(br.readLine().split(" "));
            	
            	
            	source = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate(input.get(0), state.getBoard());
            	destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate(input.get(1), state.getBoard());

            	
            }catch(ArrayIndexOutOfBoundsException e){
                
                System.out.println("Felaktiga parametrar. Mata in enligt FN");
            }catch(NullPointerException e){
            	
            	System.out.println("Denna spelare har inte denna f�rg");
                
            }catch(IOException e){
            }
            if(source != null && destination != null)
                inputOK = true;
            
            else
                System.out.println("Felaktig inmatning. Testa igen");
        }
        return new Move(player,source, destination);
    }
   }
