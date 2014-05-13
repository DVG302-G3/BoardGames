package boardgames.g3.core.Ludo;

import game.api.GameState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class LudoDiceChooser extends JButton implements ActionListener {

	private int diceNumber;
	GameState gameState;

	public LudoDiceChooser(GameState state) {
		this.gameState = state;
		setIcon(new ImageIcon("src\\boardgames\\img\\no_dice.png"));
		
		setBorderPainted(false);
		setContentAreaFilled(false);
	    setFocusPainted(false);
		
	}

	public void returnDice() {
		diceNumber = gameState.getDieRollFactory().getLastRoll().getResult();

		switch (diceNumber) {
		case 1:
			this.setIcon(new ImageIcon("src\\boardgames\\img\\dice1.png"));
			break;

		case 2:
			this.setIcon(new ImageIcon("src\\boardgames\\img\\dice2.png"));
			break;

		case 3:
			this.setIcon(new ImageIcon("src\\boardgames\\img\\dice3.png"));
			break;

		case 4:
			this.setIcon(new ImageIcon("src\\boardgames\\img\\dice4.png"));
			break;

		case 5:
			this.setIcon(new ImageIcon("src\\boardgames\\img\\dice5.png"));
			break;

		case 6:
			this.setIcon(new ImageIcon("src\\boardgames\\img\\dice6.png"));
			break;
		
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		returnDice();		
	}

}
