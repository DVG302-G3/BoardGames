package boardgames.g3.GUI;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Move;
import game.io.InputUnit;
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

import boardgames.g3.core.SolitarGUIIOFactory;
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
		inputUnit  = new SolitarGUIInputUnit();
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
		

		  button = new JButton[ROWS][COLS];
		  for(int r = 0; r < ROWS; r++){
			for(int c = 0; c < COLS; c++){
				button[r][c] = new JButton(""+ r + c);
				button[r][c].setSize(70,50);
			
				button[r][c].addActionListener(new ButtonBeadslisterners());
				
				midPanel.add(button[r][c]);	
			}
		  }
		
		List<BoardLocation> locations = gameState.getBoard().getLocations();

		int rowCounter = 0;
		for (int i = 0; i < locations.size(); i++) {
			String col = locations.get(i).getId();
			GamePiece piece = locations.get(i).getPiece();
			button[rowCounter][i % COLS] = new JButton(); 
			if (i % COLS == 0) {
					rowCounter++;
			}

			if (col == null) {
				button[rowCounter][i % COLS].setVisible(false);
			} else {
				if(piece == null)
					button[rowCounter][i % COLS].setText(" ");
				else
					button[rowCounter][i % COLS].setText(" O ");
			}
		}
	}
	
	
	class ButtonBeadslisterners implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			inputUnit.onClick("");
		}

	}


}
