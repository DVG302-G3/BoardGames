package boardgames.g3.core;

import game.io.InputUnit;
import game.io.IoFactory;
import game.io.OutputUnit;

public class SolitarConsoleIOFactory implements IoFactory{
	SolitarConsoleInputUnit inputUnit = new SolitarConsoleInputUnit();
	SolitairConsoleOutput outputUnit = new SolitairConsoleOutput();
	
	
	@Override
	public InputUnit getInputUnit() {
		return inputUnit;
	}

	@Override
	public OutputUnit getOutputUnit() {
		return outputUnit;
	}
}
