
package boardgames.g3.Input_OutPutUnits;

import game.io.InputUnit;
import game.io.IoFactory;
import game.io.OutputUnit;

public class LudoConsoleIOFactory implements IoFactory{
    
    LudoConsoleInputUnit inputUnit = new LudoConsoleInputUnit();
    LudoConsoleOutput outputUnit = new LudoConsoleOutput();

    @Override
    public InputUnit getInputUnit() {
        return inputUnit;    
    }

    @Override
    public OutputUnit getOutputUnit() {
    return outputUnit;
    }
    
}
