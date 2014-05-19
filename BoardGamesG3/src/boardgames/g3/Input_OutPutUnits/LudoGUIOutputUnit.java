package boardgames.g3.Input_OutPutUnits;

import game.api.GameState;
import game.impl.BoardLocation;
import game.io.OutputUnit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

import boardgames.g3.BGForLabelsButtons.BackGroundButtonIDLudo;
import boardgames.g3.BGForLabelsButtons.BackGroundLabelLudo;
import boardgames.g3.core.Ludo.HelpMethodsFinaMedKnuff;
import boardgames.g3.core.Ludo.LudoDiceChooser;
import boardgames.g3.core.Ludo.LudoStaticValues;

public class LudoGUIOutputUnit extends JPanel implements OutputUnit {

	BackGroundLabelLudo backgroundLabel;

	LudoDiceChooser diceB;

	private ActionMap actionMap;
	private InputMap inputMap;

	private ActionListener inputUnit;

	private BackGroundButtonIDLudo button[][];

	private ButtonGroup buttonGroup;
	private JCheckBox redC, blueC, yellowC, greenC;

	private JPanel midPanel, topPanel, topPanelPlayers, eastPanelFinished,
			topPanelWhoPlay, topPanelMessage, eastPanel, eastPanelDice;

	private JTextField textFieldWhosTurn;
	private JTextArea textAreaMessage;

	private String player1, player2, player3, player4;
	private int players;

	public LudoGUIOutputUnit(LudoGUIInputUnit inputUnit) {
		this.inputUnit = inputUnit;
		createComponent();
//		 howManyPlayerAndSetName();
		settingUpComponents();
	}

	private void createComponent() {
		backgroundLabel = new BackGroundLabelLudo(LudoStaticValues.ROWS,
				LudoStaticValues.COLS);


		
		textFieldWhosTurn = new JTextField();
		textAreaMessage = new JTextArea();

		topPanel = new JPanel(new GridLayout(1, 4));
		topPanelPlayers = new JPanel(new GridLayout(2, 2));
		eastPanelFinished = new JPanel(new GridLayout(4, 4));
		topPanelWhoPlay = new JPanel(new GridLayout(1, 1));

		eastPanel = new JPanel(new GridLayout(2, 1));
		topPanelMessage = new JPanel(new GridLayout(1, 1));
		eastPanelDice = new JPanel(new GridLayout(1, 1));

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
		redC.setIcon(new ImageIcon("src\\boardgames\\img\\red.jpg"));
		blueC.setIcon(new ImageIcon("src\\boardgames\\img\\blue.jpg"));
		greenC.setIcon(new ImageIcon("src\\boardgames\\img\\green.jpg"));
		yellowC.setIcon(new ImageIcon("src\\boardgames\\img\\yellow.jpg"));
		
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
		textFieldWhosTurn.setHorizontalAlignment(JLabel.CENTER);
		textFieldWhosTurn.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));

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
		// eastPanelFinished.add(labelRedFinished);
		// eastPanelFinished.add(labelBlueFinished);
		// eastPanelFinished.add(labelYellowFinished);
		// eastPanelFinished.add(labelGreenFinished);

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
		
		actionMap = this.getActionMap();  
	    inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

	    
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
			    
			    
				inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "Space");
			    actionMap.put("Space", diceB);
				
			    eastPanelDice.add(diceB);
			    
				topPanelMessage.add(textAreaMessage);
				textAreaMessage.repaint();
				backgroundLabel.add(button[rows][cols]);
			}

			cordCol = 'A';
			cordRow++;
		}
		backgroundLabel.revalidate();
		eastPanelDice.revalidate();
		topPanelMessage.revalidate();
	}

}
