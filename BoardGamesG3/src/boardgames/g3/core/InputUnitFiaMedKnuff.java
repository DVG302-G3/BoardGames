
package boardgames.g3.core;

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
    	
    	Player player = null;
    	String input;
    	int dieRollNumber;
        BoardLocation source = null;
        BoardLocation destination = null;
        boolean inputOK = false;

        dieRollNumber = state.getDieRollFactory().getNewRoll(player).getResult();
        player = state.getPlayerInTurn(); 
       

        
        while(!inputOK){
            try{
            	System.out.println();
            	System.out.print(player.getName()+" "+"antal steg du får gå med pjäs är:"+" "+dieRollNumber);
            	System.out.println();
            	System.out.print("Vilken pjäs vill du flytta:");
            	input = br.readLine();
            	
            	for(GamePiece gp : player.getPieces()){
            		if(gp.getId().equals(input)){
            			source = HelpMethodsFinaMedKnuff.getBoardLocationFromPiece(gp, state.getBoard());
            			break;
          			
            		}          			
            	}
            	
            	
            	int numberInList = HelpMethodsFinaMedKnuff.getFlatListIndexFromCoordinate(source.getId(),state.getBoard());
            	int destinationForPieceAfterDieRoll = (numberInList+dieRollNumber)%LudoStaticValues.TOTALSTEPSAROUNDTHEBOARD;
            	
            	String destinationForPiece = state.getBoard().getLocations().get(destinationForPieceAfterDieRoll).getId();
            	destination = HelpMethodsFinaMedKnuff.getBoardLocationFromCoordinate(destinationForPiece, state.getBoard());
            
            }catch(ArrayIndexOutOfBoundsException e){
                
                System.out.println("Felaktiga parametrar. Mata in enligt FN");
                e.printStackTrace();
            }catch(NullPointerException e){
            	
            	System.out.println("Denna spelare har inte denna färg");
                e.printStackTrace();
                
            }catch(IOException e){
                e.printStackTrace();
            }
            if(source != null && destination != null)
                inputOK = true;
            
            else
                System.out.println("Felaktig inmatning. Testa igen");
        }
        return new Move(player,source, destination);
    }
   }
