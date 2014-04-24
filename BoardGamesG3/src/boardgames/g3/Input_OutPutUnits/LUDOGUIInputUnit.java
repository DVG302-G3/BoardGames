package boardgames.g3.Input_OutPutUnits;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.api.GameState;
import game.io.InputUnit;

public class LUDOGUIInputUnit extends InputUnit implements ActionListener {

	GameState gameState;
	
	@Override
	public void setup(GameState gameState) {
		this.gameState = gameState;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
