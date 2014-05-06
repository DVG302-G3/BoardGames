package boardgames.g3.GUI;

import game.init.Runner;

import javax.swing.JPanel;

import boardgames.g3.Input_OutPutUnits.SolitarGUIIOFactory;
import boardgames.g3.Input_OutPutUnits.SolitarGUIInputUnit;
import boardgames.g3.Input_OutPutUnits.SolitarGUIOutputUnit;
import boardgames.g3.core.Solitaire.SolitarGameState;

public class BoardGamesSolitarGUIRunner extends JPanel {

	private JPanel mainPanel;

	SolitarGUIInputUnit in;
	SolitarGUIOutputUnit out;

	public BoardGamesSolitarGUIRunner() {
		mainPanel = new JPanel();
		
		in = new SolitarGUIInputUnit();
		out = new SolitarGUIOutputUnit(in);
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
