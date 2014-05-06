package boardgames.g3.Input_OutPutUnits;

import game.io.InputUnit;
import game.io.IoFactory;
import game.io.OutputUnit;

public class LudoGUIIOFactory implements IoFactory{
	LudoGUIInputUnit inputUnit;
	LudoGUIOutputUnit outputUnit;
	
	public LudoGUIIOFactory(LudoGUIInputUnit in, LudoGUIOutputUnit out) {
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
