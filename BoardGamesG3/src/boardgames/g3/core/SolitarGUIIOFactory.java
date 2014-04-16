package boardgames.g3.core;

import boardgames.g3.GUI.BoardGamesSolitarGUI;
import game.io.InputUnit;
import game.io.IoFactory;
import game.io.OutputUnit;

public class SolitarGUIIOFactory implements IoFactory{
	SolitarGUIInputUnit inputUnit = new SolitarGUIInputUnit();
	BoardGamesSolitarGUI outputUnit;
	
	@Override
	public InputUnit getInputUnit() {
		return inputUnit;
	}

	@Override
	public OutputUnit getOutputUnit() {
		if(outputUnit == null){
			outputUnit = new BoardGamesSolitarGUI();
		}
		return outputUnit;
	}
}
