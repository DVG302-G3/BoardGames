package boardgames.g3.core.Ludo;

import game.api.GameState;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class LudoDiceChooser extends JButton implements Action  {

	private int diceNumber;
	GameState gameState;
	ImageIcon img;

	public LudoDiceChooser(GameState state) {
		this.gameState = state;
		this.repaint();
		setBorderPainted(false);
		setContentAreaFilled(false);
	    setFocusPainted(false);
	
		setUpDefaultImage();
		
	}
		
	
	private void setUpDefaultImage(){
		setIcon(new ImageIcon("src\\boardgames\\img\\no_dice.png"));
	}
	
	
	public void returnDice() {
		diceNumber = gameState.getDieRollFactory().getLastRoll().getResult();

		switch (diceNumber) {
		case 1:
			setDiceIMG(new ImageIcon("src\\boardgames\\img\\dice1.png"));
			break;

		case 2:
			setDiceIMG(new ImageIcon("src\\boardgames\\img\\dice2.png"));
			break;

		case 3:
			setDiceIMG(new ImageIcon("src\\boardgames\\img\\dice3.png"));
			break;

		case 4:
			setDiceIMG(new ImageIcon("src\\boardgames\\img\\dice4.png"));
			break;

		case 5:
			setDiceIMG(new ImageIcon("src\\boardgames\\img\\dice5.png"));
			break;
			
		case 6:
			setDiceIMG(new ImageIcon("src\\boardgames\\img\\dice6.png"));
			break;
		}	
	}
	
	
	private void setDiceIMG(ImageIcon img){
		this.img = img; 
	}
	
	
	private ImageIcon getDiceIMG(){
		return img;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		returnDice();		
		this.setIcon(getDiceIMG());
	}

	
	
	@Override
	public Object getValue(String key) {
		return null;
	}
	@Override
	public void putValue(String key, Object value) {
	}
}
