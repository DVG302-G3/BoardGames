package boardgames.g3.BGForLabelsButtons;

import game.impl.BoardLocation;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import boardgames.g3.core.Ludo.LudoStaticValues;

public class BackGroundButtonIDLudo extends JButton {

	final String stubPath = "src\\boardgames\\img\\";

	private String coordinate;
	BoardLocation location;

	public BackGroundButtonIDLudo(BoardLocation location, String coordinate) {
		this.coordinate = coordinate;
		this.location = location;

		checkLocationContent();

		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
	}

	public String getStringId() {
		return coordinate;
	}

	public void setButtonIcon(String pathURL) {
		setIcon(new ImageIcon(stubPath + pathURL));
	}

	private void checkLocationContent() {
		String col;
		if (location == null)
			col = null;
		else
			col = location.getId();

		if (col == null) {
			this.setVisible(false);

		} else if (location.getPiece() != null) {
			this.checkColorOnThePiece();

		} else if (location.getId().equals(LudoStaticValues.GOAL)) {
			this.setButtonIcon("goalPiece.png");

		} else if (location.getId() != null) {
			this.setButtonIcon("emptyPiece.png");
			this.checkFinishLineColor();
		}
	}

	private void checkFinishLineColor() {

		if (checkFinishLineColor(location, LudoStaticValues.REDFINISHLINE)) {
			this.setButtonIcon("finishlineRed.png");

		} else if (checkFinishLineColor(location,
				LudoStaticValues.BLUEFINISHLINE)) {
			this.setButtonIcon("finishlineBlue.png");

		} else if (checkFinishLineColor(location,
				LudoStaticValues.YELLOWFINISHLINE)) {
			this.setButtonIcon("finishlineYellow.png");

		} else if (checkFinishLineColor(location,
				LudoStaticValues.GREENFINISHLINE)) {
			this.setButtonIcon("finishlineGreen.png");
		}
	}

	private void checkColorOnThePiece() {
		if (checkColorOfPieceBoardlocation(location, LudoStaticValues.REDPIECES)) {
			setButtonIconOneOrTwo(LudoStaticValues.REDPLAYER,
					LudoStaticValues.REDFINISHLINE);

		} else if (checkColorOfPieceBoardlocation(location,
				LudoStaticValues.BLUEPIECES)) {
			setButtonIconOneOrTwo(LudoStaticValues.BLUEPLAYER,
					LudoStaticValues.BLUEFINISHLINE);

		} else if (checkColorOfPieceBoardlocation(location,
				LudoStaticValues.YELLOWPIECES)) {
			setButtonIconOneOrTwo(LudoStaticValues.YELLOWPLAYER,
					LudoStaticValues.YELLOWFINISHLINE);

		} else if (checkColorOfPieceBoardlocation(location,
				LudoStaticValues.GREENPIECES)) {
			setButtonIconOneOrTwo(LudoStaticValues.GREENPLAYER,
					LudoStaticValues.GREENFINISHLINE);
		}
	}

	public void setButtonIconOneOrTwo(String pathColorURL, List<String> colorFinishLine) {

		if (location.getPieces().size() == 1) {
			if (checkFinishLineColor(location, colorFinishLine))
				this.setButtonIcon(pathColorURL.toLowerCase() + "PieceFinishLine.png");
			else
				this.setButtonIcon(pathColorURL.toLowerCase() + "Piece.png");
		} else if (checkFinishLineColor(location, colorFinishLine))
			this.setButtonIcon(pathColorURL.toLowerCase() + "TwoPieceFinishLine.png");
		else
			this.setButtonIcon(pathColorURL.toLowerCase() + "PieceTwo.png");
	}

	private boolean checkFinishLineColor(BoardLocation location,
			List<String> finishLine) {
		for (String s : finishLine)
			if (s.equals(location.getId()))
				return true;
		return false;
	}

	private boolean checkColorOfPieceBoardlocation(BoardLocation location,
			List<String> pieces) {
		for (String s : pieces)
			if (s.equals(location.getPiece().getId()))
				return true;
		return false;
	}

}
