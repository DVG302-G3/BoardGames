package boardgames.g3.Input_OutPutUnits;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.io.OutputUnit;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import boardgames.g3.GUI.ImageLabel;
import boardgames.g3.core.FiaMedKnuff.GUIGridButtonID;

public class SolitarGUIOutputUnit extends JPanel implements OutputUnit {

	ImageLabel backgroundLabel;

	private SolitarGUIInputUnit inputUnit;

	private GUIGridButtonID button[][];

	int ROWS = 7;
	int COLS = 7;

	public SolitarGUIOutputUnit(SolitarGUIInputUnit inputUnit) {
		
		this.inputUnit = inputUnit;
		backgroundLabel = new ImageLabel(new ImageIcon(
				"src\\boardgames\\img\\backgroundSolitaire.png").getImage());
		
		setLayout(new BorderLayout());
		add(backgroundLabel);
		backgroundLabel.setLayout(new GridLayout(ROWS, COLS));

	}

	@Override
	public void publish(GameState gameState) {

		backgroundLabel.removeAll();

		List<BoardLocation> locations = gameState.getBoard().getLocations();

		button = new GUIGridButtonID[ROWS][COLS];

		int index = 0;
		for (int rows = 0; rows < ROWS; rows++) {
			for (int cols = 0; cols < COLS; cols++) {
				button[rows][cols] = new GUIGridButtonID(
						Integer.toString((rows + 1))
								+ Integer.toString((cols + 1)));

				button[rows][cols].addActionListener(inputUnit);

				backgroundLabel.add(button[rows][cols]);

				String col = locations.get(index).getId();
				GamePiece piece = locations.get(index++).getPiece();

				if (col == null) {
					button[rows][cols].setVisible(false);
				} else {
					if (piece == null)
						button[rows][cols].setButtonEmptyBead();
					else
						button[rows][cols].setButtonWithBead();
				}
			}
		}

		backgroundLabel.revalidate();
	}

}
