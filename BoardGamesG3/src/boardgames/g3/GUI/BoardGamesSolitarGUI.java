package boardgames.g3.GUI;

import game.init.Runner;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import boardgames.g3.Input_OutPutUnits.SolitarGUIIOFactory;
import boardgames.g3.Input_OutPutUnits.SolitarGUIInputUnit;
import boardgames.g3.Input_OutPutUnits.SolitarGUIOutputUnit;
import boardgames.g3.core.Solitaire.CounterBeadsLeft;
import boardgames.g3.core.Solitaire.SolitarGameState;
import boardgames.g3.core.Solitaire.SolitarTimer;

public class BoardGamesSolitarGUI extends JPanel {

	private JPanel topPanel, topPanelBeadsLeft, topPanelBeadsTaken, topPanelTimer ,midPanel;

	int beadsLeft = 32; 
	int beadsTaken = 0;
	
	SolitarGUIInputUnit in;
	SolitarGUIOutputUnit out;
	SolitarTimer timer;

	public BoardGamesSolitarGUI() {
		createComponents();
		setUpPanels();

		in = new SolitarGUIInputUnit();
		out = new SolitarGUIOutputUnit(in);
		new Runner(new SolitarGameState(), new SolitarGUIIOFactory(in, out))
				.run();
		
		setNewMidPanel(out);
		setNewTopPanels(new CounterBeadsLeft(beadsLeft), new CounterBeadsTaken(beadsTaken)); 
		
		
	}

	private void createComponents() {
		
		topPanel = new JPanel(new GridLayout(0, 3));
		topPanelBeadsLeft = new JPanel();
		topPanelBeadsTaken = new JPanel();
		topPanelTimer = new JPanel();

		timer = new SolitarTimer();
		
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
		
    topPanelTimer.setBorder(BorderFactory.createTitledBorder(
      BorderFactory.createEtchedBorder(), "Timer",
      TitledBorder.LEFT, TitledBorder.TOP));
		

		topPanel.add(topPanelBeadsLeft);
		topPanel.add(topPanelBeadsTaken);
		topPanel.add(topPanelTimer);

		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);

	}

	private void setNewTopPanels(JTextArea beadsLeft, JTextArea beadsTaken){
	 topPanelBeadsLeft.removeAll();
	 topPanelBeadsTaken.removeAll();
	 
	 topPanelTimer.add(timer);
	 topPanelBeadsLeft.add(beadsLeft);
	 topPanelBeadsTaken.add(beadsTaken);
	 
	 topPanelBeadsLeft.revalidate();
	 topPanelBeadsTaken.revalidate();
	}

	private void setNewMidPanel(JPanel newMidPanel) {
		this.midPanel.removeAll();
		this.midPanel.invalidate();
		this.midPanel.add(newMidPanel);
	
		this.revalidate();

	}

}
