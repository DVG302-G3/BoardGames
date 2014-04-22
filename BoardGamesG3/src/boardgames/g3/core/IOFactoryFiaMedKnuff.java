
package boardgames.g3.core;

import game.io.InputUnit;
import game.io.IoFactory;
import game.io.OutputUnit;

public class IOFactoryFiaMedKnuff implements IoFactory{
    
    InputUnitFiaMedKnuff inputUnit = new InputUnitFiaMedKnuff();
    ConsoleOutputFiaMedKnuff outputUnit = new ConsoleOutputFiaMedKnuff();

    @Override
    public InputUnit getInputUnit() {
        return inputUnit;    
    }

    @Override
    public OutputUnit getOutputUnit() {
    return outputUnit;
    }
    
}
