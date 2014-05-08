package boardgames.g3.BGForLabelsButtons;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BackGroundButtonIDSolitaire extends JButton {

	private String cordinate;

	public BackGroundButtonIDSolitaire(String coordinate) {
		this.cordinate = coordinate;

		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
	}

	public String getStringId() {
		return cordinate;
	}

	public void setButtonEmpty() {
		setIcon(new ImageIcon("src\\boardgames\\img\\EmptyBead.png"));
	}

	public void setButtonWithBead() {
		setIcon(new ImageIcon("src\\boardgames\\img\\Bead.png"));
	}

	public void setButtonMarked() {
		setIcon(new ImageIcon("src\\boardgames\\img\\MarkedBead.png"));
	}

}
