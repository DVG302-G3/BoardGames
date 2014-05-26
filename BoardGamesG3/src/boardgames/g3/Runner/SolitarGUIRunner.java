package boardgames.g3.Runner;

import game.init.Runner;

import java.awt.Color;

import javax.swing.JPanel;

import boardgames.g3.Input_OutPutUnits.SolitarGUIIOFactory;
import boardgames.g3.Input_OutPutUnits.SolitarGUIInputUnit;
import boardgames.g3.Input_OutPutUnits.SolitarGUIOutputUnit;
import boardgames.g3.core.Solitaire.SolitarGameState;

public class SolitarGUIRunner extends JPanel {

	private JPanel mainPanel;

	SolitarGUIInputUnit in;
	SolitarGUIOutputUnit out;

	public SolitarGUIRunner() {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLACK);

		in = new SolitarGUIInputUnit();
		out = new SolitarGUIOutputUnit();
		out.registerListener(in);
		new Runner(new SolitarGameState(), new SolitarGUIIOFactory(in, out))
				.run();

		setNewMidPanel(out);	

		this.add(mainPanel);
	}

	private void setNewMidPanel(JPanel newMidPanel) {
		this.mainPanel.removeAll();
		this.mainPanel.invalidate();
		this.mainPanel.add(newMidPanel);
		this.revalidate();

	}

}