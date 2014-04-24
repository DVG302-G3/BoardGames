package boardgames.g3.Input_OutPutUnits;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import boardgames.g3.GUI.ImageLabel;
import boardgames.g3.core.FiaMedKnuff.GUIGridButtonID;
import game.api.GameState;
import game.impl.BoardLocation;
import game.io.OutputUnit;

public class LUDOGUIOutputUnit extends JPanel implements OutputUnit {

	ImageLabel backgroundLabel;
	
	private SolitarGUIInputUnit inputUnit;

	private GUIGridButtonID button[][];

	int ROWS = 11;
	int COLS = 11;
	
	
	public LUDOGUIOutputUnit(LUDOGUIInputUnit inputUnit) {
		
		backgroundLabel = new ImageLabel(new ImageIcon(
				"src\\boardgames\\img\\menubackgroundFMK.png").getImage());		
		
		setLayout(new BorderLayout());
		add(backgroundLabel);
		backgroundLabel.setLayout(new GridLayout(ROWS, COLS));
	}

	@Override
	public void publish(GameState gameState) {


		backgroundLabel.removeAll();

		List<BoardLocation> locations = gameState.getBoard().getLocations();

		button = new GUIGridButtonID[ROWS][COLS];
		
		
		
		
	}

}
