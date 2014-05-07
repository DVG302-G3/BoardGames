package boardgames.g3.Input_OutPutUnits;

import game.api.GameState;
import game.impl.BoardLocation;
import game.io.OutputUnit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import boardgames.g3.BGForLabelsButtons.BackGroundButtonID;
import boardgames.g3.BGForLabelsButtons.BackGroundLabelLudo;
import boardgames.g3.core.Ludo.HelpMethodsFinaMedKnuff;
import boardgames.g3.core.Ludo.LudoDiceChooser;
import boardgames.g3.core.Ludo.LudoStaticValues;

public class LudoGUIOutputUnit extends JPanel implements OutputUnit {

	BackGroundLabelLudo backgroundLabel;
	LudoDiceChooser dice;

	private ActionListener inputUnit;

	private BackGroundButtonID button[][];
	private JButton diceButton;

	private JCheckBox redC, blueC, yellowC, greenC;
	private JCheckBox diceCheckBox;

	private JPanel midPanel, topPanel, topPanelPlayers, topPanelFinished,
			topPanelWhoPlay, topPanelDice;

	private ButtonGroup buttonGroup;
	
	private JTextField textFieldWhosTurn;
	
	

	public LudoGUIOutputUnit(LudoGUIInputUnit inputUnit) {
		this.inputUnit = inputUnit;
		createComponent();
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
		diceButton = new JButton("Roll Dice");

		redC = new JCheckBox();
		blueC = new JCheckBox();
		yellowC = new JCheckBox();
		greenC = new JCheckBox();
		diceCheckBox = new JCheckBox();
	}

	private void settingUpComponents() {
		greenC.setIcon(new ImageIcon("src\\boardgames\\img\\green.jpg"));
		redC.setIcon(new ImageIcon("src\\boardgames\\img\\red.jpg"));
		yellowC.setIcon(new ImageIcon("src\\boardgames\\img\\yellow.jpg"));
		blueC.setIcon(new ImageIcon("src\\boardgames\\img\\blue.jpg"));

		greenC.setText("Mattias");
		redC.setText("Anders");
		yellowC.setText("Maya");
		blueC.setText("Cissan");

		buttonGroup.add(greenC);
		buttonGroup.add(redC);
		buttonGroup.add(yellowC);
		buttonGroup.add(blueC);

		diceCheckBox
				.setIcon(new ImageIcon("src\\boardgames\\img\\no_dice.png"));
		
		textFieldWhosTurn.setEditable(false);
		textFieldWhosTurn.setHorizontalAlignment(JLabel.CENTER);
		textFieldWhosTurn.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
		

		topPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Fia Med Knuff",
				TitledBorder.LEFT, TitledBorder.TOP));

		topPanelPlayers.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Players",
				TitledBorder.LEFT, TitledBorder.TOP));
		topPanelPlayers.add(greenC);
		topPanelPlayers.add(redC);
		topPanelPlayers.add(yellowC);
		topPanelPlayers.add(blueC);

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
		topPanelDice.add(diceButton);
		topPanelDice.add(diceCheckBox);

		topPanel.add(topPanelPlayers);
		topPanel.add(topPanelFinished);
		topPanel.add(topPanelWhoPlay);
		topPanel.add(topPanelDice);

		midPanel.add(backgroundLabel);

		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);

	}

	private void setDiceCheckBox(JCheckBox diceCheckBoxNew) {
		topPanelDice.removeAll();
		topPanelDice.add(diceButton);
		topPanelDice.add(diceCheckBoxNew);
		this.revalidate();
	}

	@Override
	public void publish(GameState gameState) {
		char cordRow = 'A';
		char cordCol = 'A';
		
		backgroundLabel.removeAll();

		setDiceCheckBox(new LudoDiceChooser(gameState));
		
		textFieldWhosTurn.setText(gameState.getPlayerInTurn().getName());
		
		button = new BackGroundButtonID[LudoStaticValues.ROWS][LudoStaticValues.COLS];

		
		for (int rows = 0; rows < LudoStaticValues.ROWS; rows++) {
			for (int cols = 0; cols < LudoStaticValues.COLS; cols++) {
				
				String coordinate = Character.toString(cordRow)
						+ Character.toString(cordCol++);
				String col;
				BoardLocation location = HelpMethodsFinaMedKnuff
						.getBoardLocationFromCoordinate(coordinate,
								gameState.getBoard());
				
				System.out.println(coordinate);
				button[rows][cols] = new BackGroundButtonID(coordinate);

				button[rows][cols].addActionListener(inputUnit);

				backgroundLabel.add(button[rows][cols]);

				
				
				if (location == null)
					col = null;
				else
					col = location.getId();

				if (col == null) {
					button[rows][cols].setVisible(false);
				
				} else if (location.getPiece() != null) {
					button[rows][cols].setButtonWithBead();
					System.out.print(location.getPiece().getId() + " ");
				
				} else {
					button[rows][cols].setButtonEmptyBead();
				}

				
				
				
//				if (location == null) {
//					piece = locations.get(index).getPiece();
//
//					if (piece == null) {
//						button[rows][cols].setButtonEmptyBead();
//
//					} else
//						button[rows][cols].setButtonWithBead();
//
//				} else {
//					button[rows][cols].setVisible(false);
//
//				}
				

			}
		
			cordCol = 'A';
			cordRow++;
		}

		backgroundLabel.revalidate();
	}

}
