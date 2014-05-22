package boardgames.g3.core.Ludo;

import java.util.Arrays;
import java.util.List;

public class LudoStaticValues {
	
	public static final int TOTALSTEPSAROUNDTHEBOARD = 40;

	public static final int ROWS = 11;
	public static final int COLS = 11;
	
	public static final String REDPLAYER = "Red";
	public static final String BLUEPLAYER = "Blue";
	public static final String YELLOWPLAYER = "Yellow";
	public static final String GREENPLAYER = "Green";
  
	public static final String REDSTART = "EA";
	public static final String BLUESTART = "AG";
	public static final String GREENSTART = "KE";
	public static final String YELLOWSTART = "GK";
	
	public static final String REDSTARTSIXES = "DE";
	public static final String BLUESTARTSIXES = "EH";
	public static final String GREENSTARTSIXES = "GD";
	public static final String YELLOWSTARTSIXES = "HG";

	public static final String MESSAGE_INCORRECTNUMBEROFSTEPS = "You can't move to this position. Please try again.";
	public static final String MESSAGE_NOGAMEPIECE = "No game piece located in source.";
	public static final String MESSAGE_IN_BASE_CANTMOVEOUT = "You need to get 1 or 6 in order to move out of base.";
	public static final String MESSAGE_INVALID_CANT_LAPSE_YOUR_OWN_PIECE = "You are not allowed to pass your own piece, mate.";
	public static final String MESSAGE_NO_MOVE_AVAILABLE = "No move available.";
	public static final String MESSAGE_INVALIDA_BOARDLOCATION_ALREADY_OCCUPIED = "Boardlocation can't hold that many pieces!";
	public static final String MESSAGE_INVALID_CANT_PASS_A_BLOCK = "You are not allowed to pass a block, mate.";
	
	
	public static final List<String> REDFINISHLINE = Arrays.asList("FB", "FC", "FD", "FE");
	public static final List<String> BLUEFINISHLINE = Arrays.asList("BF", "CF", "DF", "EF");
	public static final List<String> GREENFINISHLINE = Arrays.asList("JF", "IF", "HF", "GF");
	public static final List<String> YELLOWFINISHLINE = Arrays.asList("FJ", "FI", "FH", "FG" );

	public static final List<String> REDHOME = Arrays.asList("BB", "BC", "CB", "CC");
	public static final List<String> BLUEHOME =Arrays.asList("BI", "BJ", "CI", "CJ" );
	public static final List<String> GREENHOME = Arrays.asList("IB", "IC", "JB", "JC");
	public static final List<String> YELLOWHOME = Arrays.asList("II", "IJ", "JI", "JJ");
	
	public static final List<String> REDPIECES = Arrays.asList("R1", "R2", "R3", "R4");
	public static final List<String> BLUEPIECES = Arrays.asList("B1", "B2", "B3", "B4");
	public static final List<String> YELLOWPIECES = Arrays.asList("Y1", "Y2", "Y3", "Y4");
	public static final List<String> GREENPIECES = Arrays.asList("G1", "G2", "G3", "G4");
	
	public static final String GOAL = "FF";

	
}
