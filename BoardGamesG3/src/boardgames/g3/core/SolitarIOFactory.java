package boardgames.g3.core;

import game.io.InputUnit;
import game.io.IoFactory;
import game.io.OutputUnit;
import boardgames.g3.core.Solitaire.SolitarInputUnit;

public class SolitarIOFactory implements IoFactory{
	SolitarInputUnit inputUnit = new SolitarInputUnit();
	SolitairConsoleOutput outputUnit = new SolitairConsoleOutput();
	
	
	@Override
	public InputUnit getInputUnit() {
		return inputUnit;
	}

	@Override
	public OutputUnit getOutputUnit() {
		return outputUnit;
	}

	public IoFactory run() {
		// TODO Auto-generated method stub
		return null;
	}	
	


}
