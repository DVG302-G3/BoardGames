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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import boardgames.g3.BGForLabelsButtons.BackGroundButtonIDLudo;
import boardgames.g3.BGForLabelsButtons.BackGroundLabelLudo;
import boardgames.g3.JUnitTests.SolitarJOptionFinishTest;
import boardgames.g3.core.Ludo.HelpMethodsFinaMedKnuff;
import boardgames.g3.core.Ludo.LudoDiceChooser;
import boardgames.g3.core.Ludo.LudoStaticValues;
import boardgames.g3.core.Solitaire.SolitarJOptionFinish;
import boardgames.g3.core.Solitaire.SolitarTimer;

public class LudoGUIOutputUnit extends JPanel implements OutputUnit {

	BackGroundLabelLudo backgroundLabel;

	LudoDiceChooser diceB;

	private String redURL = "src\\boardgames\\img\\red.jpg";
	private String blueURL = "src\\boardgames\\img\\blue.jpg";
	private String yellowURL = "src\\boardgames\\img\\yellow.jpg";
	private String greenURL = "src\\boardgames\\img\\green.jpg";

	private String winnerRedIconURL = "src\\boardgames\\img\\redWINNER.png";
	private String winnerBlueIconURL = "src\\boardgames\\img\\blueWINNER.png";
	private String winnerYellowIconURL = "src\\boardgames\\img\\yellowWINNER.png";
	private String winnerGreenIconURL = "src\\boardgames\\img\\greenWINNER.png";
	
	private ActionListener inputUnit;
	
	private ImageIcon winnerIcon;
	
	private BackGroundButtonIDLudo button[][];

	private ButtonGroup buttonGroup;
	private JCheckBox redC, blueC, yellowC, greenC;

	private JPanel midPanel, topPanel, topPanelPlayers, eastPanelFinished,
			topPanelWhoPlay, topPanelMessage, eastPanel, eastPanelDice, easttopFinishPanel, eastbottomFinishPanel;

	private JLabel eastbottomWinnerLabel;
	private ImageIcon winnerImage;
	
	private JTextField textFieldWhosTurn, winnerTextField;
	private JTextArea textAreaMessage;

	private String player1, player2, player3, player4;
	private int players;

	public LudoGUIOutputUnit(LudoGUIInputUnit inputUnit) {
		this.inputUnit = inputUnit;
		createComponent();
//		howManyPlayerAndSetName();
		settingUpComponents();
	}

	private void createComponent() {
		backgroundLabel = new BackGroundLabelLudo(LudoStaticValues.ROWS,
				LudoStaticValues.COLS);

		winnerIcon = new ImageIcon();
		
		textFieldWhosTurn = new JTextField();
		textAreaMessage = new JTextArea();
		winnerTextField = new JTextField();

		topPanel = new JPanel(new GridLayout(1, 4));
		topPanelPlayers = new JPanel(new GridLayout(2, 2));
		topPanelWhoPlay = new JPanel(new GridLayout(1, 1));
		topPanelMessage = new JPanel(new GridLayout(1, 1));
		
		eastPanel = new JPanel(new GridLayout(2, 1));
		eastPanelDice = new JPanel(new GridLayout(1, 1));
		eastPanelFinished = new JPanel(new GridLayout(4, 4));
		easttopFinishPanel = new JPanel();
		eastbottomFinishPanel = new JPanel();
		
		eastbottomWinnerLabel = new JLabel();

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
		if (players >= 1 && players <= 4) {
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
		redC.setIcon(new ImageIcon(redURL));
		blueC.setIcon(new ImageIcon(blueURL));
		greenC.setIcon(new ImageIcon(greenURL));
		yellowC.setIcon(new ImageIcon(yellowURL));
		
		redC.setOpaque(false);
		blueC.setOpaque(false);
		greenC.setOpaque(false);
		yellowC.setOpaque(false);

		redC.setText(player1);
		blueC.setText(player2);
		greenC.setText(player3);
		yellowC.setText(player4);

		buttonGroup.add(redC);
		buttonGroup.add(blueC);
		buttonGroup.add(greenC);
		buttonGroup.add(yellowC);

		textFieldWhosTurn.setFocusable(false);
		textFieldWhosTurn.setEditable(false);
		textFieldWhosTurn.setOpaque(false);
		textFieldWhosTurn.setBorder(BorderFactory.createEmptyBorder());
		textFieldWhosTurn.setHorizontalAlignment(JLabel.CENTER);
		textFieldWhosTurn.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));

		winnerTextField.setFocusable(false);
		winnerTextField.setEditable(false);
		winnerTextField.setOpaque(false);
		winnerTextField.setBorder(BorderFactory.createEmptyBorder());
		winnerTextField.setHorizontalAlignment(JLabel.CENTER);
		winnerTextField.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
		
		textAreaMessage.setFocusable(false);
		textAreaMessage.setEditable(false);
		textAreaMessage.setOpaque(false);
		textAreaMessage.setLineWrap(true);
		textAreaMessage.setWrapStyleWord(true);
		textAreaMessage.setFont(new Font("Arial", Font.CENTER_BASELINE, 10));
		
		topPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Ludo", TitledBorder.LEFT,
				TitledBorder.TOP));
		topPanel.setBackground(Color.WHITE);

		topPanelPlayers.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Players",
				TitledBorder.LEFT, TitledBorder.TOP));
		topPanelPlayers.setBackground(Color.WHITE);

		topPanelPlayers.add(redC);
		topPanelPlayers.add(blueC);
		topPanelPlayers.add(greenC);
		topPanelPlayers.add(yellowC);

		topPanelWhoPlay.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Whos Turn",
				TitledBorder.LEFT, TitledBorder.TOP));
		topPanelWhoPlay.add(textFieldWhosTurn);
		topPanelWhoPlay.setBackground(Color.WHITE);

		topPanelMessage.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Message",
				TitledBorder.LEFT, TitledBorder.TOP));
		topPanelMessage.add(textAreaMessage);
		topPanelMessage.setBackground(Color.WHITE);

		eastPanelDice.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Roll The Dice",
				TitledBorder.LEFT, TitledBorder.TOP));
		eastPanelDice.setBackground(Color.WHITE);

		eastPanelFinished.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Finished Pieces",
				TitledBorder.LEFT, TitledBorder.TOP));
		eastPanelFinished.setBackground(Color.WHITE);
		
		easttopFinishPanel.setOpaque(false);
		eastbottomFinishPanel.setOpaque(false);
		easttopFinishPanel.add(winnerTextField);
		eastbottomFinishPanel.add(eastbottomWinnerLabel);
		
		eastPanelFinished.setLayout(new BorderLayout());
		eastPanelFinished.add(easttopFinishPanel, BorderLayout.NORTH);
		eastPanelFinished.add(eastbottomFinishPanel, BorderLayout.SOUTH);

		topPanel.add(topPanelPlayers);
		topPanel.add(topPanelWhoPlay);
		topPanel.add(topPanelMessage);

		eastPanel.add(eastPanelDice);
		eastPanel.add(eastPanelFinished);

		midPanel.add(backgroundLabel);

		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);
		this.add(eastPanel, BorderLayout.EAST);
	}

	@Override
	public void publish(GameState gameState) {
		char cordRow = 'A';
		char cordCol = 'A';

		backgroundLabel.removeAll();
		eastPanelDice.removeAll();
		topPanelMessage.removeAll();
		

		textFieldWhosTurn.setText(gameState.getLastPlayer().getName());

		textAreaMessage.setText(gameState.getMessage());
		
		button = new BackGroundButtonIDLudo[LudoStaticValues.ROWS][LudoStaticValues.COLS];
		diceB = new LudoDiceChooser(gameState);

		for (int rows = 0; rows < LudoStaticValues.ROWS; rows++) {
			for (int cols = 0; cols < LudoStaticValues.COLS; cols++) {

				String coordinate = Character.toString(cordRow)
						+ Character.toString(cordCol++);

				BoardLocation location = HelpMethodsFinaMedKnuff
						.getBoardLocationFromCoordinate(coordinate,
								gameState.getBoard());

				button[rows][cols] = new BackGroundButtonIDLudo(location,
						coordinate);
				button[rows][cols].addActionListener(inputUnit);

				diceB.addActionListener(diceB);

				
				eastPanelDice.add(diceB);
			    diceB.addActionListener(diceB);			
			    eastPanelDice.add(diceB);
			    
				topPanelMessage.add(textAreaMessage);
				easttopFinishPanel.add(winnerTextField);
				
				
				textAreaMessage.repaint();
				
				backgroundLabel.add(button[rows][cols]);
				
				getWinner(gameState);
				
			}

			cordCol = 'A';
			cordRow++;
		}
		if (gameState.hasEnded()) {
			
			SolitarJOptionFinish optionLabel = new SolitarJOptionFinish();

			optionLabel.dataTaker(0,0,"0","0");

			optionLabel.displayGUI();

			if (optionLabel.getReturnValue() == 1) {
				gameState.reset();
				publish(gameState);
			}
		}
		backgroundLabel.revalidate();
		eastPanelDice.revalidate();
		eastPanelFinished.revalidate();
		topPanelMessage.revalidate();
	}

	private void getWinner(GameState gameState) {
		if(gameState.getWinner() != null){
			String winner = gameState.getWinner().getName();

			switch(winner){
			
			case LudoStaticValues.REDPLAYER :
				winnerIcon = new ImageIcon(winnerRedIconURL);
			break;	
			case LudoStaticValues.BLUEPLAYER :
				winnerIcon = new ImageIcon(winnerBlueIconURL);
			break;	
			case LudoStaticValues.YELLOWPLAYER :
				winnerIcon = new ImageIcon(winnerYellowIconURL);
			break;	
			case LudoStaticValues.GREENPLAYER :
				winnerIcon = new ImageIcon(winnerGreenIconURL);
			break;
			}
			winnerTextField.setText(winner);
			winnerTextField.repaint();
			eastbottomWinnerLabel.setIcon(winnerIcon);
		}
	}
}
