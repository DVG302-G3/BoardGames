package boardgames.g3.Input_OutPutUnits;

import game.io.InputUnit;
import game.io.IoFactory;
import game.io.OutputUnit;

public class SolitarConsoleIOFactory implements IoFactory{
	SolitarConsoleInputUnit inputUnit = new SolitarConsoleInputUnit();
	SolitarConsoleOutput outputUnit = new SolitarConsoleOutput();
	
	
	@Override
	public InputUnit getInputUnit() {
		return inputUnit;
	}

	@Override
	public OutputUnit getOutputUnit() {
		return outputUnit;
	}
}
