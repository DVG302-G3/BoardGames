package boardgames.g3.core.Solitaire;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import boardgames.g3.GUI.BoardGamesCoreGUI;

public class SolitarJOptionFinish {

	SolitarTimer solidTimer;
	
	int value= -1;
	
	String[] choice = { "Ok! Back to main menu", "Ok! Play again! " };

	public void displayGUI() {
		value = JOptionPane.showOptionDialog(null, getPanel(), "Game's Finished!",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, choice, "default");

		setReturnValue(value);	
			
	}


	private JPanel getPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("No more moves are available!");
		ImageIcon image = null;

		image = new ImageIcon("src\\boardgames\\img\\peggy.gif");

		label.setIcon(image);
		panel.add(label);

		return panel;
	}

	
	private void setReturnValue(int value){
		this.value = value;
	}
	
	public int getReturnValue(){
		return value;
	}
	
}
