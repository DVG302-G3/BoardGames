package boardgames.g3.Input_OutPutUnits;

import game.api.GameState;
import game.io.OutputUnit;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import boardgames.g3.BGForLabelsButtons.BackGroundButtonID;
import boardgames.g3.BGForLabelsButtons.BackGroundLabelLudo;

public class LUDOGUIOutputUnit extends JPanel implements OutputUnit {

	BackGroundLabelLudo backgroundLabel;
	
	private LUDOGUIInputUnit inputUnit;

	private BackGroundButtonID button[][];

	int ROWS = 11;
	int COLS = 11;
	
	
	public LUDOGUIOutputUnit(LUDOGUIInputUnit inputUnit) {
   this.inputUnit = inputUnit; 
   backgroundLabel = new BackGroundLabelLudo(ROWS, COLS);
   setLayout(new BorderLayout());
   add(backgroundLabel);
	}

	@Override
	public void publish(GameState gameState) {

//
//		backgroundLabel.removeAll();
//
//		List<BoardLocation> locations = gameState.getBoard().getLocations();
//
//		button = new GUIGridButtonID[ROWS][COLS];
//		
		
		
		
	}

}
