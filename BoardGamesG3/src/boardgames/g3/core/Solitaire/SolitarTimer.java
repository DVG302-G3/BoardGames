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

	private static Timer ourClock;
	private static TimerTask ourTask;
	
	
	private int count;

	JTextField textFieldTimer;

	public SolitarTimer() {

		ourClock = new Timer();
		ourTask = new Task();

		ourClock.scheduleAtFixedRate(ourTask, 1000, 1000);

		setLayout(new FlowLayout());

		textFieldTimer = new JTextField(5);
		textFieldTimer.setEditable(false);
		textFieldTimer.setBorder(BorderFactory.createEtchedBorder(Color.WHITE,
				Color.BLACK));
		textFieldTimer.setHorizontalAlignment(JLabel.CENTER);
		textFieldTimer.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));

		this.add(textFieldTimer);
		
	}
	
	public void setCountTime(int count){
		this.count = count;
	}
	
	public int getCountTime(){
		return count;
	}
	
	
	public void pause(){
		ourClock.cancel();
		ourTask.cancel();
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

			textFieldTimer.setText(timeInMinutes + ":" + timeInSecounds);

			setCountTime(timeInSecounds);
		}


	}

}
