package boardgames.g3.GUI;

import game.init.Runner;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import boardgames.g3.Input_OutPutUnits.SolitarGUIIOFactory;
import boardgames.g3.Input_OutPutUnits.SolitarGUIInputUnit;
import boardgames.g3.Input_OutPutUnits.SolitarGUIOutputUnit;
import boardgames.g3.core.Solitaire.SolitarGameState;

public class BoardGamesSolitarGUI extends JPanel {

	private JPanel topPanel, topPanelBeadsLeft, topPanelBeadsTaken, midPanel;

	public BoardGamesSolitarGUI() {
		createComponents();
		setUpPanels();

		SolitarGUIInputUnit in = new SolitarGUIInputUnit();
		SolitarGUIOutputUnit out = new SolitarGUIOutputUnit(in);
		new Runner(new SolitarGameState(), new SolitarGUIIOFactory(in, out))
				.run();
		setNewMidPanel(out);

	}

	private void createComponents() {

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

		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);

	}

	private void setNewMidPanel(JPanel newMidPanel) {
		this.midPanel.removeAll();
		this.midPanel.invalidate();
		this.midPanel.add(newMidPanel);

		this.revalidate();

	}

}
