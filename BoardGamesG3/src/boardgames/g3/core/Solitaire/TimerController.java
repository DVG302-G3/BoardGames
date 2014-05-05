package boardgames.g3.core.Solitaire;

import javax.swing.JPanel;

public class TimerController{
	private long startTime;
	private long stopTime;

	
	
	public TimerController() {
		startTime();
	}

	private void startTime() {
		startTime = System.currentTimeMillis();
	}

	public void getStopTime() {
		stopTime = System.currentTimeMillis() - startTime;
	}

	public String getTime() {
	
		
			long time = System.currentTimeMillis() - startTime;
			double mSec = time % 1000 / 10;
			double seconds = (double) time / 1000;
			double minutes = seconds / 60;
			seconds = seconds % 60;

			return String.format("%02d:%02d:%02d", (int) minutes,
					(int) seconds, (int) mSec);


	}
}
