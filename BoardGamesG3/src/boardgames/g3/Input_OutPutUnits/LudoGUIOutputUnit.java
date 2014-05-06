package boardgames.g3.Input_OutPutUnits;

import game.api.GameState;
import game.io.OutputUnit;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import boardgames.g3.BGForLabelsButtons.BackGroundButtonID;
import boardgames.g3.BGForLabelsButtons.BackGroundLabelLudo;

public class LudoGUIOutputUnit extends JPanel implements OutputUnit {

	BackGroundLabelLudo backgroundLabel;

	private ActionListener inputUnit;

	private BackGroundButtonID button[][];
	private JButton diceButton;

	
	private JPanel midPanel, topPanel, topPanelPlayers, topPanelFinished,
	topPanelWhoPlay, topPanelDice;
	
	private JCheckBox redC, blueC, yellowC, greenC;

	private JCheckBox diceCheckBox;

	private ButtonGroup buttonGroup;
	

	int ROWS = 11;
	int COLS = 11;

	public LudoGUIOutputUnit(LudoGUIInputUnit inputUnit) {
		this.inputUnit = inputUnit;
		createComponent();
		settingUpComponents();
	}


	private void createComponent() {
		backgroundLabel = new BackGroundLabelLudo(ROWS, COLS);
		
		topPanel = new JPanel(new GridLayout(0, 4));
		topPanelPlayers = new JPanel(new GridLayout(0, 2));
		topPanelFinished = new JPanel(new GridLayout(0, 2));
		topPanelWhoPlay = new JPanel(new GridLayout(4, 0));
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

		topPanelDice.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Roll The Dice",
				TitledBorder.LEFT, TitledBorder.TOP));
//		topPanelDice.add(diceButton);
//		topPanelDice.add(diceCheckBox);

		topPanel.add(topPanelPlayers);
		topPanel.add(topPanelFinished);
		topPanel.add(topPanelWhoPlay);
		topPanel.add(topPanelDice);
		
		
		midPanel.add(backgroundLabel);

		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);

		
	}
	
	@Override
	public void publish(GameState gameState) {

		//
		// backgroundLabel.removeAll();
		//
		// List<BoardLocation> locations = gameState.getBoard().getLocations();
		//
		// button = new GUIGridButtonID[ROWS][COLS];
		//

	}

}
