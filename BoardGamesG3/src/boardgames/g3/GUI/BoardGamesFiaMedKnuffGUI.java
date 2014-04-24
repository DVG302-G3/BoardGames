package boardgames.g3.GUI;

import game.init.Runner;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import boardgames.g3.Input_OutPutUnits.LUDOGUIIOFactory;
import boardgames.g3.Input_OutPutUnits.LUDOGUIInputUnit;
import boardgames.g3.Input_OutPutUnits.LUDOGUIOutputUnit;
import boardgames.g3.core.FiaMedKnuff.diceChooser;
import boardgames.g3.core.Solitaire.SolitarGameState;

public class BoardGamesFiaMedKnuffGUI extends JPanel {

	ImageLabel backgroundLabel;

	private JButton diceButton;

	private JPanel topPanel, topPanelPlayers, topPanelFinished,
			topPanelWhoPlay, topPanelDice, midPanel;

	private JCheckBox redC, blueC, yellowC, greenC;

	private JCheckBox diceCheckBox;

	private ButtonGroup buttonGroup;

	public BoardGamesFiaMedKnuffGUI() {

		createComponents();
		setUpPanels();
		setUpButtonGroupAndCheckBoxes();
		buttons();
		
		
		LUDOGUIInputUnit in = new LUDOGUIInputUnit();
		LUDOGUIOutputUnit out = new LUDOGUIOutputUnit(in);
		new Runner(new SolitarGameState(), new LUDOGUIIOFactory(in, out)).run();
		setNewMidPanel(out);

	}

	private void createComponents() {

		topPanel = new JPanel(new GridLayout(0, 4));
		topPanelPlayers = new JPanel(new GridLayout(0, 2));
		topPanelFinished = new JPanel(new GridLayout(0, 2));
		topPanelWhoPlay = new JPanel(new GridLayout(4, 0));
		topPanelDice = new JPanel(new GridLayout(0, 2));

		midPanel = new JPanel();

		buttonGroup = new ButtonGroup();

		redC = new JCheckBox();
		blueC = new JCheckBox();
		yellowC = new JCheckBox();
		greenC = new JCheckBox();
		diceCheckBox = new JCheckBox();

		diceButton = new JButton("Roll Dice");
	}

	private void setUpButtonGroupAndCheckBoxes() {
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
	}

	private void setUpPanels() {

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
		topPanelDice.add(diceButton);
		topPanelDice.add(diceCheckBox);

		topPanel.add(topPanelPlayers);
		topPanel.add(topPanelFinished);
		topPanel.add(topPanelWhoPlay);
		topPanel.add(topPanelDice);


		setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);

	}

	private void buttons() {
		diceButton.addActionListener(new BoardGamesListenersDice());
	}

	private void setNewMidPanel(JPanel newMidPanel) {
		this.midPanel.removeAll();
		this.midPanel.invalidate();
		this.midPanel.add(newMidPanel);

		this.revalidate();
	}

	private void setDiceCheckBox(JCheckBox diceCheckBoxNew) {
		topPanelDice.removeAll();
		topPanelDice.add(diceButton);
		topPanelDice.add(diceCheckBoxNew);
		this.revalidate();
	}

	class BoardGamesListenersDice implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setDiceCheckBox(new diceChooser());

		}
	}
}
