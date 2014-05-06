package boardgames.g3.Input_OutPutUnits;

import game.io.InputUnit;
import game.io.IoFactory;
import game.io.OutputUnit;

public class SolitarGUIIOFactory implements IoFactory{
	SolitarGUIInputUnit inputUnit;
	SolitarGUIOutputUnit outputUnit;
	
	public SolitarGUIIOFactory(SolitarGUIInputUnit in, SolitarGUIOutputUnit out) {
		inputUnit = in;
		outputUnit = out;
	}
	
	@Override
	public InputUnit getInputUnit() {
		return inputUnit;
	}

	@Override
	public OutputUnit getOutputUnit() {
		return outputUnit;
	}
}
