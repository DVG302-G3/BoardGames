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

	public static final List<String> REDFINISHLINE = Arrays.asList("FB", "FC", "FD", "FE");
	public static final List<String> BLUEFINISHLINE = Arrays.asList("BF", "CF", "DF", "EF");
	public static final List<String> GREENFINISHLINE = Arrays.asList("JF", "IF", "GF", "HF");
	public static final List<String> YELLOWFINISHLINE = Arrays.asList("FJ", "FI", "FH", "FG" );

	public static final List<String> REDHOME = Arrays.asList("BB", "BC", "CB", "CC");
	public static final List<String> BLUEHOME =Arrays.asList("BI", "BJ", "CI", "CJ" );
	public static final List<String> GREENHOME = Arrays.asList("IB", "IC", "JB", "JC");
	public static final List<String> YELLOWHOME = Arrays.asList("II", "IJ", "JI", "JJ");
	
	public static final String GOAL = "FF";
	
}
