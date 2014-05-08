package boardgames.g3.Input_OutPutUnits;

import game.api.GameState;
import game.impl.BoardLocation;
import game.io.OutputUnit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import boardgames.g3.BGForLabelsButtons.BackGroundButtonIDLudo;
import boardgames.g3.BGForLabelsButtons.BackGroundLabelLudo;
import boardgames.g3.core.Ludo.HelpMethodsFinaMedKnuff;
import boardgames.g3.core.Ludo.LudoDiceChooser;
import boardgames.g3.core.Ludo.LudoStaticValues;

public class LudoGUIOutputUnit extends JPanel implements OutputUnit {

	BackGroundLabelLudo backgroundLabel;
	
	LudoDiceChooser diceB;
	

	private ActionListener inputUnit;

	private BackGroundButtonIDLudo button[][];

	private ButtonGroup buttonGroup;
	private JCheckBox redC, blueC, yellowC, greenC;
	

	private JPanel midPanel, topPanel, topPanelPlayers, topPanelFinished,
			topPanelWhoPlay, topPanelDice;

	

	private JTextField textFieldWhosTurn;
	private String player1, player2, player3, player4;
	private int players;

	public LudoGUIOutputUnit(LudoGUIInputUnit inputUnit) {
		this.inputUnit = inputUnit;
		createComponent();
		// howManyPlayerAndSetName();
		settingUpComponents();
	}

	private void createComponent() {
		backgroundLabel = new BackGroundLabelLudo(LudoStaticValues.ROWS,
				LudoStaticValues.COLS);

		textFieldWhosTurn = new JTextField();

		topPanel = new JPanel(new GridLayout(0, 4));
		topPanelPlayers = new JPanel(new GridLayout(0, 2));
		topPanelFinished = new JPanel(new GridLayout(0, 2));
		topPanelWhoPlay = new JPanel(new GridLayout(1, 0));
		topPanelDice = new JPanel(new GridLayout(0, 2));
		midPanel = new JPanel();

		buttonGroup = new ButtonGroup();

		redC = new JCheckBox();
		blueC = new JCheckBox();
		yellowC = new JCheckBox();
		greenC = new JCheckBox();
		
	}

	private void howManyPlayerAndSetName() {

		players = Integer.parseInt(JOptionPane.showInputDialog(null,
				"How many players? (2-4)"));
		if (players >= 2 && players <= 4) {
			if (players == 2) {
				player1 = JOptionPane.showInputDialog("Player 1 (Red) Name:");
				player2 = JOptionPane.showInputDialog("Player 2 (Blue) Name:");
			} else if (players == 3) {
				player1 = JOptionPane.showInputDialog("Player 1 (Red) Name:");
				player2 = JOptionPane.showInputDialog("Player 2 (Blue) Name:");
				player3 = JOptionPane.showInputDialog("Player 3 (Green) Name:");
			} else if (players == 4) {
				player1 = JOptionPane.showInputDialog("Player 1 (Red) Name:");
				player2 = JOptionPane.showInputDialog("Player 2 (Blue) Name:");
				player3 = JOptionPane.showInputDialog("Player 3 (Green) Name:");
				player4 = JOptionPane
						.showInputDialog("Player 4 (Yellow) Name:");
			}
		} else
			JOptionPane.showMessageDialog(null, "Wrong amount of players!!!");
	}

	private void settingUpComponents() {
		redC.setIcon(new ImageIcon("src\\boardgames\\img\\red.jpg"));
		blueC.setIcon(new ImageIcon("src\\boardgames\\img\\blue.jpg"));
		greenC.setIcon(new ImageIcon("src\\boardgames\\img\\green.jpg"));
		yellowC.setIcon(new ImageIcon("src\\boardgames\\img\\yellow.jpg"));

		redC.setText(player1);
		blueC.setText(player2);
		greenC.setText(player3);
		yellowC.setText(player4);

		buttonGroup.add(redC);
		buttonGroup.add(blueC);
		buttonGroup.add(greenC);
		buttonGroup.add(yellowC);

		textFieldWhosTurn.setEditable(false);
		textFieldWhosTurn.setHorizontalAlignment(JLabel.CENTER);
		textFieldWhosTurn.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));

		topPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Fia Med Knuff",
				TitledBorder.LEFT, TitledBorder.TOP));

		topPanelPlayers.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Players",
				TitledBorder.LEFT, TitledBorder.TOP));
		topPanelPlayers.add(redC);
		topPanelPlayers.add(blueC);
		topPanelPlayers.add(greenC);
		topPanelPlayers.add(yellowC);

		topPanelFinished.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Finished Pieces",
				TitledBorder.LEFT, TitledBorder.TOP));
		// topPanelFinished.add(piecesGreen);
		// topPanelFinished.add(piecesRed);
		// topPanelFinished.add(piecesYellow);
		// topPanelFinished.add(piecesBlue);

		topPanelWhoPlay.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Whos Turn",
				TitledBorder.LEFT, TitledBorder.TOP));
		topPanelWhoPlay.add(textFieldWhosTurn);

		topPanelDice.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Roll The Dice",
				TitledBorder.LEFT, TitledBorder.TOP));
	

		topPanel.add(topPanelPlayers);
		topPanel.add(topPanelFinished);
		topPanel.add(topPanelWhoPlay);
		topPanel.add(topPanelDice);

		midPanel.add(backgroundLabel);

		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);

	}

	private void setDiceButton(JButton diceButtonNew) {
		topPanelDice.removeAll();
		topPanelDice.add(diceButtonNew);
		this.revalidate();
	}

	@Override
	public void publish(GameState gameState) {
		char cordRow = 'A';
		char cordCol = 'A';

		backgroundLabel.removeAll();
		topPanelDice.removeAll();

		textFieldWhosTurn.setText(gameState.getPlayerInTurn().getName());

		button = new BackGroundButtonIDLudo[LudoStaticValues.ROWS][LudoStaticValues.COLS];
		diceB = new LudoDiceChooser(gameState);
		
		for (int rows = 0; rows < LudoStaticValues.ROWS; rows++) {
			for (int cols = 0; cols < LudoStaticValues.COLS; cols++) {

				String coordinate = Character.toString(cordRow)
						+ Character.toString(cordCol++);

				BoardLocation location = HelpMethodsFinaMedKnuff
						.getBoardLocationFromCoordinate(coordinate,
								gameState.getBoard());
				
				button[rows][cols] = new BackGroundButtonIDLudo(location, coordinate);
				button[rows][cols].addActionListener(inputUnit);

				diceB.addActionListener(diceB);

				backgroundLabel.add(button[rows][cols]);

				topPanelDice.add(diceB);
				
			}

			cordCol = 'A';
			cordRow++;
		}

		backgroundLabel.revalidate();
		topPanelDice.revalidate();
	}

}
