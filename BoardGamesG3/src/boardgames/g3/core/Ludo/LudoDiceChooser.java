package boardgames.g3.core.Ludo;

import game.api.GameState;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class LudoDiceChooser extends JCheckBox {

	private int diceNumber;
	GameState gameState;

	public LudoDiceChooser(GameState state) {
		this.gameState = state;
		returnDice();
	}

	public void returnDice() {
		diceNumber = gameState.getDieRollFactory().getLastRoll().getResult();

		switch (diceNumber) {
		case 1:
			this.setIcon(new ImageIcon("src\\boardgames\\img\\dice1.png"));
			System.out.println("1");
			break;

		case 2:
			this.setIcon(new ImageIcon("src\\boardgames\\img\\dice2.png"));
			System.out.println("2");
			break;

		case 3:
			this.setIcon(new ImageIcon("src\\boardgames\\img\\dice3.png"));
			System.out.println("3");
			break;

		case 4:
			this.setIcon(new ImageIcon("src\\boardgames\\img\\dice4.png"));
			System.out.println("4");
			break;

		case 5:
			this.setIcon(new ImageIcon("src\\boardgames\\img\\dice5.png"));
			System.out.println("5");
			break;

		case 6:
			this.setIcon(new ImageIcon("src\\boardgames\\img\\dice6.png"));
			System.out.println("6");
			break;

		}

	}

}
