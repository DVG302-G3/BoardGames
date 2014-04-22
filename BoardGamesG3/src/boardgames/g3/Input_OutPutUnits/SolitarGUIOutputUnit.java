package boardgames.g3.Input_OutPutUnits;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.io.OutputUnit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import boardgames.g3.GUI.ImagePanel;
import boardgames.g3.core.FiaMedKnuff.GUIGridButtonID;

public class SolitarGUIOutputUnit extends JPanel implements OutputUnit {

	SolitarGUIInputUnit inputUnit;

	ImagePanel backgroundPanel;
	
	private JPanel mainPanel;

	private JButton button[][];

	int ROWS = 7;
	int COLS = 7;

	public SolitarGUIOutputUnit() {
		this.setLayout(new GridLayout(ROWS, COLS));

		backgroundPanel = new ImagePanel(new ImageIcon(
				"src\\boardgames\\img\\backgroundSolitaire.png").getImage());

		
		inputUnit = new SolitarGUIInputUnit();
		
		setLayout(new BorderLayout());
		add(backgroundPanel);
		backgroundPanel.setLayout(new GridLayout(ROWS, COLS));
		
		publish(inputUnit.state);
		
	}

	@Override
	public void publish(GameState gameState) {

		
		backgroundPanel.removeAll();
		button = new JButton[ROWS][COLS];


		
		List<BoardLocation> locations = gameState.getBoard().getLocations();

		int index = 0;
		for (int rows = 0; rows < ROWS; rows++) {
			for (int cols = 0; cols < COLS; cols++) {
				button[rows][cols] = new GUIGridButtonID(
						Integer.toString((rows + 1))
								+ Integer.toString((cols + 1)));
				button[rows][cols].setBorderPainted(false);
				button[rows][cols].setContentAreaFilled(false);

				button[rows][cols]
						.addActionListener(new ButtonBeadslisterners());

				backgroundPanel.add(button[rows][cols]);

				String col = locations.get(index).getId();
				GamePiece piece = locations.get(index++).getPiece();

				if (col == null) {
					button[rows][cols].setVisible(false);
				} else {
					if (piece == null)
						button[rows][cols]
								.setIcon(new ImageIcon(
										"src\\boardgames\\img\\emptybeadsolitaire.png"));
					else
						button[rows][cols].setIcon(new ImageIcon(
								"src\\boardgames\\img\\beadsolitaire.png"));
				}
			}
		}

		backgroundPanel.revalidate();
	}

	class ButtonBeadslisterners implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			GUIGridButtonID buttonGUI = (GUIGridButtonID) e.getSource();

			inputUnit.onClick(buttonGUI.getStringId());

			System.out.println(buttonGUI.getStringId());
			publish(inputUnit.state);
		}
	}
}
