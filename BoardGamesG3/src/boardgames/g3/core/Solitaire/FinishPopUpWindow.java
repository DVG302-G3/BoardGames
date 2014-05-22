package boardgames.g3.core.Solitaire;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FinishPopUpWindow {

	SolitarTimer solidTimer;

	private int value = -1;

	private JPanel mainPanel, westPanel, eastPanel;
	private JLabel westLabel;
	private JTextArea eastTextArea;
	private String[] choice = { "Ok! Thanks!", "Ok! Reset Game!" };
	private ImageIcon image;

	private String textSolitar;

	private String textLudo = "Game is over!! Good job soliders!";

	private String solitarURL = "src\\boardgames\\img\\peggy.gif";
	private String ludoURL = "";

	public void displayWindowSolitar() {
		value = JOptionPane.showOptionDialog(null,
				createAndGetPanelSolitar(solitarURL, textSolitar),
				"Game's Finished!", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, choice, "default");

		setReturnValue(value);
	}

	public void displayWindowLudo() {
		value = JOptionPane.showOptionDialog(null,
				createAndGetPanelSolitar(ludoURL, textLudo),
				"Game's Finished!", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, choice, "default");

		setReturnValue(value);

	}

	public void dataTakerSolitar(int timeInMin, int timeInSec,
			String beadsTaken, String beadsLeft) {

		textSolitar = "No more moves are available! \nBeads taken: "
				+ beadsTaken + "\nBeads left: " + beadsLeft + "\nTime: "
				+ String.format("%02d:%02d", timeInMin, timeInSec);
	}

	private JPanel createAndGetPanelSolitar(String textURL, String text) {
		mainPanel = new JPanel();
		westPanel = new JPanel();
		eastPanel = new JPanel();
		westLabel = new JLabel();
		eastTextArea = new JTextArea();

		eastTextArea.setText(text);
		eastTextArea.setFocusable(false);
		eastTextArea.setEditable(false);
		eastTextArea.setOpaque(false);

		image = new ImageIcon(textURL);

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
