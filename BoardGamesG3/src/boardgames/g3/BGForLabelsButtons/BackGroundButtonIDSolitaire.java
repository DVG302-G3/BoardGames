package boardgames.g3.BGForLabelsButtons;

import game.impl.BoardLocation;
import game.impl.GamePiece;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import boardgames.g3.core.Solitaire.SolitarCounterBeads;

public class BackGroundButtonIDSolitaire extends JButton {
	List<BoardLocation> locations;
	SolitarCounterBeads counterBeads;

	private String cordinate;

	private int index;

	public BackGroundButtonIDSolitaire(String coordinate,
			List<BoardLocation> locations, SolitarCounterBeads counterBeads,
			int index) {
		this.cordinate = coordinate;
		this.locations = locations;
		this.counterBeads = counterBeads;
		this.index = index;

		checkLocationContent();

		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
	}

	public String getStringId() {
		return cordinate;
	}

	private void setButtonEmpty() {
		setIcon(new ImageIcon("src\\boardgames\\img\\EmptyBead.png"));
	}

	public void setButtonWithBead() {
		setIcon(new ImageIcon("src\\boardgames\\img\\Bead.png"));
	}

	public void setButtonMarked() {
		setIcon(new ImageIcon("src\\boardgames\\img\\MarkedBead.png"));
	}

	private void checkLocationContent() {

		GamePiece piece = null;

		if (locations.get(index) != null) {
			piece = locations.get(index).getPiece();

			if (piece == null) {
				this.setButtonEmpty();
				counterBeads.update();
			} else
				this.setButtonWithBead();

		} else {
			this.setVisible(false);
		}

	}

}
