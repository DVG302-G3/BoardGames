package boardgames.g3.core.Solitaire;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SolitarJOptionFinish {

	SolitarTimer solidTimer;

	private int value = -1;
	private int timeInMin, timeInSec;
	private String beadsTaken, beadsLeft;
	private JPanel mainPanel, westPanel, eastPanel;
	private JLabel westLabel;
	private JTextArea eastTextArea;
	private String[] choice = { "Ok! Back to main menu", "Ok! Reset Game!" };
	private ImageIcon image;

	public void displayGUI() {
		value = JOptionPane.showOptionDialog(null, createAndGetPanel(),
				"Game's Finished!", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, choice, "default");

		setReturnValue(value);

	}

	public void dataTaker(int timeInMin, int timeInSec, String beadsTaken,
			String beadsLeft) {
		this.timeInMin = timeInMin;
		this.timeInSec = timeInSec;
		this.beadsTaken = beadsTaken;
		this.beadsLeft = beadsLeft;
	}

	private JPanel createAndGetPanel() {
		mainPanel = new JPanel();
		westPanel = new JPanel();
		eastPanel = new JPanel();
		westLabel = new JLabel();
		eastTextArea = new JTextArea();

		eastTextArea.setText("No more moves are available! \nBeads taken: "
				+ beadsTaken + "\nBeads left: " + beadsLeft + "\nTime: "
				+ String.format("%02d:%02d", timeInMin, timeInSec));
		eastTextArea.setFocusable(false);
		eastTextArea.setEditable(false);
		eastTextArea.setOpaque(false);

		image = new ImageIcon("src\\boardgames\\img\\peggy.gif");

		westLabel.setIcon(image);
		westPanel.add(westLabel);
		eastPanel.add(eastTextArea);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(westPanel, BorderLayout.WEST);
		mainPanel.add(eastPanel, BorderLayout.EAST);

		return mainPanel;
	}

	private void setReturnValue(int value) {
		this.value = value;
	}

	public int getReturnValue() {
		return value;
	}

}
