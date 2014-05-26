package boardgames.g3.Runner;

import game.init.Runner;

import java.awt.Color;

import javax.swing.JPanel;

import boardgames.g3.Input_OutPutUnits.LudoGUIIOFactory;
import boardgames.g3.Input_OutPutUnits.LudoGUIInputUnit;
import boardgames.g3.Input_OutPutUnits.LudoGUIOutputUnit;
import boardgames.g3.core.Ludo.LudoGameState;

public class LudoGUIRunner extends JPanel {

	private JPanel mainPanel;

	LudoGUIInputUnit inputUnit;
	LudoGUIOutputUnit outputUnit;

	public LudoGUIRunner() {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLACK);

		inputUnit = new LudoGUIInputUnit();
		outputUnit = new LudoGUIOutputUnit();
		outputUnit.registerListener(inputUnit);
		new Runner(new LudoGameState(), new LudoGUIIOFactory(inputUnit,
				outputUnit)).run();

		setNewMidPanel(outputUnit);

		this.add(mainPanel);
	}

	private void setNewMidPanel(JPanel newMidPanel) {
		this.mainPanel.removeAll();
		this.mainPanel.invalidate();
		this.mainPanel.add(newMidPanel);

		this.revalidate();
	}

}