package boardgames.g3.Input_OutPutUnits;

import boardgames.g3.GUI.BoardGamesSolitarGUI;
import game.io.InputUnit;
import game.io.IoFactory;
import game.io.OutputUnit;

public class SolitarGUIIOFactory implements IoFactory{
	SolitarGUIInputUnit inputUnit = new SolitarGUIInputUnit();
	SolitarGUIOutputUnit outputUnit;
	
	@Override
	public InputUnit getInputUnit() {
		return inputUnit;
	}

	@Override
	public OutputUnit getOutputUnit() {
		if(outputUnit == null){
			outputUnit = new SolitarGUIOutputUnit();
		}
		return outputUnit;
	}
}
