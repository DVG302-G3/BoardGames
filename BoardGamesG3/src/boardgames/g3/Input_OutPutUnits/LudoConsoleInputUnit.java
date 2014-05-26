package boardgames.g3.Input_OutPutUnits;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.Move;
import game.impl.Player;
import game.io.InputUnit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import boardgames.g3.core.Ludo.LudoHelpMethods;

public class LudoConsoleInputUnit extends InputUnit {
	BufferedReader br;
	Player previousPlayer;

	@Override
	public void setup(GameState state) {
		br = new BufferedReader(new InputStreamReader(System.in));
		while (!state.hasEnded()) {
			notifyListenersOfMove(getNextMove(state));
			System.out.println(state.getMessage());
		}
	}

	private Move getNextMove(GameState state) {

		Player player = null;
		List<String> input;
		int dieRollNumber;
		BoardLocation source = null;
		BoardLocation destination = null;
		boolean inputOK = false;

		player = state.getPlayerInTurn();
		dieRollNumber = state.getDieRollFactory().getLastRoll().getResult();
		
		if(!state.getMessage().equals("You can't move to this position. Please try again.")){
			System.out.print(player.getName()
					+ " tryck på valfri knapp för att slå tärningen!");
			try {
				br.read();
				br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Tärningen visar: " + dieRollNumber);
		}


		if (playerCanMakeAMove(state, player, dieRollNumber)) {

			while (!inputOK) {

				System.out.print("Vilken pjäs vill du flytta:");
				try {
					input = Arrays.asList(br.readLine().split(" "));
					source = LudoHelpMethods
							.getBoardLocationFromCoordinate(input.get(0),
									state.getBoard());
					destination = LudoHelpMethods
							.getBoardLocationFromCoordinate(input.get(1),
									state.getBoard());

					if (source != null && destination != null)
						inputOK = true;

					else
						System.out.println("Felaktig inmatning. Testa igen");
				} catch (ArrayIndexOutOfBoundsException e) {

					System.out
							.println("Felaktiga parametrar. Mata in enligt FN");
					inputOK = false;
				} catch (NullPointerException e) {

					System.out.println("Denna spelare har inte denna f�rg");
					inputOK = false;
				} catch (IOException e) {
				}

			}
		}

		if (source == null || destination == null) {
			source = state.getBoard().getLocations().get(0);
			destination = state.getBoard().getLocations().get(1);
		}
		return new Move(player, source, destination);
	}

	private boolean playerCanMakeAMove(GameState state, Player player,
			int dieRollNumber) {
		return !(!isDieSixOrOne(dieRollNumber) && !LudoHelpMethods
				.doesPlayerHaveAnyPiecesOnTheBoard(player, state.getBoard()));
	}

	private boolean isDieSixOrOne(int dieRoll) {
		return dieRoll == 6 || dieRoll == 1;
	}

}
