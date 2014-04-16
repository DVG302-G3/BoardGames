package boardgames.g3.GUI;

import game.api.GameState;
import game.io.OutputUnit;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import boardgames.g3.core.SolitarGUIIOFactory;

public class BoardGamesSolitarGUI extends JPanel implements OutputUnit {

	private ImageIcon mainBackground;
	private JLabel backgroundLabel;
	private JButton button[][];

	private JPanel topPanel, topPanelBeadsLeft, topPanelBeadsTaken, midPanel;

	int rows = 7;
	int cols = 7;

	public BoardGamesSolitarGUI() {
		createComponents();
		setUpPanels();
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

		midPanel.setLayout(new GridLayout(rows, cols));

		setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);

	}

	private void updateButtonsView(){

		
		  button = new JButton[rows][cols];
		  for(int r = 0; r < rows; r++){
			for(int c = 0; c < cols; c++){
				button[r][c] = new JButton(""+ r + c);
				button[r][c].setSize(70,50);
			
				button[r][c].addActionListener(new ButtonBeadslisterners());
				
				midPanel.add(button[r][c]);	
			}
		  }	
	}

	@Override
	public void publish(GameState gameState) {
		buttons()
		
		
		
	}

	class ButtonBeadslisterners implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new SolitarGUIIOFactory()
			
			;
	}

	}


}
