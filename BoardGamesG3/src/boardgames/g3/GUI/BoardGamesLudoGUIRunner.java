package boardgames.g3.GUI;

import game.init.Runner;

import java.awt.Color;

import javax.swing.JPanel;

import boardgames.g3.Input_OutPutUnits.LudoGUIIOFactory;
import boardgames.g3.Input_OutPutUnits.LudoGUIInputUnit;
import boardgames.g3.Input_OutPutUnits.LudoGUIOutputUnit;
import boardgames.g3.core.Ludo.LudoGameState;

public class BoardGamesLudoGUIRunner extends JPanel {


	private JPanel mainPanel;

	LudoGUIInputUnit inputUnit;
	LudoGUIOutputUnit outputUnit;

	public BoardGamesLudoGUIRunner() {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLACK);
		
		inputUnit = new LudoGUIInputUnit();
		outputUnit = new LudoGUIOutputUnit(inputUnit);
		new Runner(new LudoGameState(2), new LudoGUIIOFactory(inputUnit, outputUnit)).run();
		
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

