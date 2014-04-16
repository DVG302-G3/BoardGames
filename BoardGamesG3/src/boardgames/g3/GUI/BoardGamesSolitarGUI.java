package boardgames.g3.GUI;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.io.OutputUnit;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import boardgames.g3.core.SolitarGUIInputUnit;

public class BoardGamesSolitarGUI extends JPanel implements OutputUnit {

	SolitarGUIInputUnit inputUnit;

	private ImageIcon mainBackground;
	private JLabel backgroundLabel;
	private JButton button[][];

	private JPanel topPanel, topPanelBeadsLeft, topPanelBeadsTaken, midPanel;

	int ROWS = 7;
	int COLS = 7;

	public BoardGamesSolitarGUI() {
		createComponents();
		setUpPanels();
		inputUnit = new SolitarGUIInputUnit();
		publish(inputUnit.state);
	}

	private void createComponents() {
		mainBackground = new ImageIcon(
				"src\\boardgames\\img\\menubackgroundFMK.png");
		backgroundLabel = new JLabel(mainBackground);

		topPanel = new JPanel(new GridLayout(0, 2));
		topPanelBeadsLeft = new JPanel();
		topPanelBeadsTaken = new JPanel();

		midPanel = new JPanel();

	}

	private void setUpPanels() {

		topPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Solitär",
				TitledBorder.LEFT, TitledBorder.TOP));

		topPanelBeadsLeft.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Beads Left",
				TitledBorder.LEFT, TitledBorder.TOP));

		topPanelBeadsTaken.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Beads Taken",
				TitledBorder.LEFT, TitledBorder.TOP));

		topPanel.add(topPanelBeadsLeft);
		topPanel.add(topPanelBeadsTaken);

		midPanel.setLayout(new GridLayout(ROWS, COLS));

		setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);

	}

	@Override
	public void publish(GameState gameState) {

		System.out.println("Hej?");
		midPanel.removeAll();
		button = new JButton[ROWS][COLS];

		List<BoardLocation> locations = gameState.getBoard().getLocations();

		int index = 0;
		for (int rows = 0; rows < ROWS; rows++) {
			for (int cols = 0; cols < COLS; cols++) {
				button[rows][cols] = new JButton();
				button[rows][cols]
						.addActionListener(new ButtonBeadslisterners());
				midPanel.add(button[rows][cols]);

				String col = locations.get(index).getId();
				GamePiece piece = locations.get(index++).getPiece();

				if (col == null) {
					button[rows][cols].setVisible(false);
				} else {
					if (piece == null)
						button[rows][cols].setText(" ");
					else
						button[rows][cols].setText(" O ");
				}

			}

		}
		
		
		List<BoardLocation> locations2 = gameState.getBoard().getLocations();

		System.out.print("  1 2 3 4 5 6 7");
		int rowCounter = 1;
		for (int i = 0; i < locations2.size(); i++) {
			String col = locations2.get(i).getId();
			GamePiece piece = locations2.get(i).getPiece();

			if (i % COLS == 0) {
				System.out.println();
				System.out.print(rowCounter++ + " ");
			}

			if (col == null) {
				System.out.print("  ");
			} else {
				if (piece == null)
					System.out.print(". ");
				else
					System.out.print("O ");
			}
		}

	
		midPanel.revalidate();
	}

	class ButtonBeadslisterners implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			inputUnit.onClick("");
			publish(inputUnit.state);
			
		}

	}
}
