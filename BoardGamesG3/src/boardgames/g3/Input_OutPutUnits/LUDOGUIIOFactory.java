package boardgames.g3.Input_OutPutUnits;

import game.io.InputUnit;
import game.io.IoFactory;
import game.io.OutputUnit;

public class LUDOGUIIOFactory implements IoFactory{
	LUDOGUIInputUnit inputUnit;
	LUDOGUIOutputUnit outputUnit;
	
	public LUDOGUIIOFactory(LUDOGUIInputUnit in, LUDOGUIOutputUnit out) {
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
