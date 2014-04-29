package boardgames.g3.core;

import java.util.Arrays;
import java.util.List;

public class LudoStaticValues {

	public static final String REDSTART = "EA";
	public static final String BLUESTART = "AG";
	public static final String GREENSTART = "KE";
	public static final String YELLOWSTART = "GK";

	public static final List<String> REDFINISHLINE = Arrays.asList("FB", "FC", "FD", "FE");
	public static final List<String> BLUEFINISHLINE = Arrays.asList("BF", "CF", "DF", "EF");
	public static final List<String> GREENFINISHLINE = Arrays.asList("JF", "IF", "GF", "HF");
	public static final List<String> YELLOWFINISHLINE = Arrays.asList("FJ", "FI", "FH", "FG" );

	public static final List<String> REDHOME = Arrays.asList("BB", "BC", "CB", "CC");
	public static final List<String> BLUEHOME =Arrays.asList("BI", "BJ", "CI", "CJ" );
	public static final List<String> GREENHOME = Arrays.asList("IB", "IC", "JB", "JC");
	public static final List<String> YELLOWHOME = Arrays.asList("II", "IJ", "JI", "JJ");
	
}
