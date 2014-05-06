package boardgames.g3.core.Solitaire;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SolitarTimer extends JPanel {

	private static Timer timer;
	private static TimerTask task;
	
	
	private int counterInSec;
	private int counterInMin;

	JTextField textFieldTimer;

	public SolitarTimer() {

		timer = new Timer();
		task = new Task();

		timer.scheduleAtFixedRate(task, 1000, 1000);
		

		setLayout(new FlowLayout());

		textFieldTimer = new JTextField(5);
		textFieldTimer.setEditable(false);
		textFieldTimer.setBorder(BorderFactory.createEtchedBorder(Color.WHITE,
				Color.BLACK));
		textFieldTimer.setHorizontalAlignment(JLabel.CENTER);
		textFieldTimer.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));

		this.add(textFieldTimer);	
	}
	
	private void setCountTimeMin(int timeInMinutes){
		this.counterInMin = timeInMinutes;		
	}
	
	private void setCountTimeSec( int timeInSecounds){
		this.counterInSec = timeInSecounds;
	}
	
	public int getCountTimeMin(){
		return counterInMin;
	}
	
	public int getCountTimeSec(){
		return counterInSec;
	}
	
	public void StopTimeAndTask(){
		timer.cancel();
		task.cancel();
	}

	class Task extends TimerTask {

		private int timeInSecounds = 0;
		private int timeInMinutes = 0;

		@Override
		public void run() {

			timeInSecounds++;

			if (timeInSecounds == 60) {

				timeInSecounds = 0;
				timeInMinutes++;

			}

			textFieldTimer.setText(String.format("%02d:%02d",timeInMinutes ,timeInSecounds ));
			
			setCountTimeMin(timeInMinutes);
			setCountTimeSec(timeInSecounds);
		}


	}

}
