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
	
	public void setButtonIcon(String pathURL){
		setIcon(new ImageIcon(stubPath + pathURL));
	}

	private void setButtonEmptyPiece() {
		setIcon(new ImageIcon("src\\boardgames\\img\\emptyPiece.png"));
	}

	public void setButtonWithRed() {
		setIcon(new ImageIcon("src\\boardgames\\img\\redPiece.png"));
	}

	public void setButtonWithBlue() {
		setIcon(new ImageIcon("src\\boardgames\\img\\bluePiece.png"));
	}

	public void setButtonWithYellow() {
		setIcon(new ImageIcon("src\\boardgames\\img\\yellowPiece.png"));
	}

	public void setButtonWithGreen() {
		setIcon(new ImageIcon("src\\boardgames\\img\\greenPiece.png"));
	}

	public void setButtonWithRedMarked() {
		setIcon(new ImageIcon("src\\boardgames\\img\\redPieceMarked.png"));
	}

	public void setButtonWithTwoRed() {
		setIcon(new ImageIcon("src\\boardgames\\img\\redPieceTwo.png"));
	}

	public void setButtonWithTwoBlue() {
		setIcon(new ImageIcon("src\\boardgames\\img\\bluePieceTwo.png"));
	}

	public void setButtonWithTwoYellow() {
		setIcon(new ImageIcon("src\\boardgames\\img\\yellowPieceTwo.png"));
	}

	public void setButtonWithTwoGreen() {
		setIcon(new ImageIcon("src\\boardgames\\img\\greenPieceTwo.png"));
	}

	public void setButtonWithBlueMarked() {
		setIcon(new ImageIcon("src\\boardgames\\img\\bluePieceMarked.png"));
	}

	public void setButtonWithYellowMarked() {
		setIcon(new ImageIcon("src\\boardgames\\img\\yellowPieceMarked.png"));
	}

	public void setButtonWithGreenMarked() {
		setIcon(new ImageIcon("src\\boardgames\\img\\greenPieceMarked.png"));
	}

	public void setButtonWithTwoRedMarked() {
		setIcon(new ImageIcon("src\\boardgames\\img\\redPieceTwoMarked.png"));
	}

	public void setButtonWithTwoBlueMarked() {
		setIcon(new ImageIcon("src\\boardgames\\img\\bluePieceTwoMarked.png"));
	}

	public void setButtonWithTwoYellowMarked() {
		setIcon(new ImageIcon("src\\boardgames\\img\\yellowPieceTwoMarked.png"));
	}

	public void setButtonWithTwoGreenMarked() {
		setIcon(new ImageIcon("src\\boardgames\\img\\greenPieceTwoMarked.png"));
	}

	private void setButtonWithRedPieceFinishLine() {
		setIcon(new ImageIcon("src\\boardgames\\img\\redPieceFinishLine.png"));
	}

	private void setButtonWithBluePieceFinishLine() {
		setIcon(new ImageIcon("src\\boardgames\\img\\bluePieceFinishLine.png"));
	}

	private void setButtonWithYellowPieceFinishLine() {
		setIcon(new ImageIcon("src\\boardgames\\img\\yellowPieceFinishLine.png"));
	}

	private void setButtonWithGreenPieceFinishLine() {
		setIcon(new ImageIcon("src\\boardgames\\img\\greenPieceFinishLine.png"));
	}
	
	private void setButtonWithTwoRedPieceFinishLine() {
		setIcon(new ImageIcon("src\\boardgames\\img\\redTwoPieceFinishLine.png"));
	}

	private void setButtonWithTwoBluePieceFinishLine() {
		setIcon(new ImageIcon("src\\boardgames\\img\\blueTwoPieceFinishLine.png"));
	}

	private void setButtonWithTwoYellowPieceFinishLine() {
		setIcon(new ImageIcon("src\\boardgames\\img\\yellowTwoPieceFinishLine.png"));
	}

	private void setButtonWithTwoGreenPieceFinishLine() {
		setIcon(new ImageIcon("src\\boardgames\\img\\greenTwoPieceFinishLine.png"));
	}

	private void setButtonWithRedFinishLine() {
		setIcon(new ImageIcon("src\\boardgames\\img\\finishlineRed.png"));
	}

	private void setButtonWithBlueFinishLine() {
		setIcon(new ImageIcon("src\\boardgames\\img\\finishlineBlue.png"));
	}

	private void setButtonWithYellowFinishLine() {
		setIcon(new ImageIcon("src\\boardgames\\img\\finishlineYellow.png"));
	}

	private void setButtonWithGreenFinishLine() {
		setIcon(new ImageIcon("src\\boardgames\\img\\finishlineGreen.png"));
	}

	private void setButtonWithGOAL() {
		setIcon(new ImageIcon("src\\boardgames\\img\\goalPiece.png"));
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
			this.setButtonWithGOAL();

		} else if (location.getId() != null) {
			this.setButtonEmptyPiece();
			this.checkFinishLineColor();
		}
	}

	private void checkFinishLineColor() {

		if (checkFinishLineColor(location, LudoStaticValues.REDFINISHLINE)) {
			this.setButtonWithRedFinishLine();

		} else if (checkFinishLineColor(location,
				LudoStaticValues.BLUEFINISHLINE)) {
			this.setButtonWithBlueFinishLine();

		} else if (checkFinishLineColor(location,
				LudoStaticValues.YELLOWFINISHLINE)) {
			this.setButtonWithYellowFinishLine();

		} else if (checkFinishLineColor(location,
				LudoStaticValues.GREENFINISHLINE)) {
			this.setButtonWithGreenFinishLine();
		}
	}

	private void checkColorOnThePiece() {
		if (checkColorOfPieceBoardlocation(location, LudoStaticValues.REDPIECES)) {
			if (location.getPieces().size() == 1) {
				if(checkFinishLineColor(location, LudoStaticValues.REDFINISHLINE))
					this.setButtonWithRedPieceFinishLine();
				else
					this.setButtonWithRed();
			} else {
				if(checkFinishLineColor(location, LudoStaticValues.REDFINISHLINE))
					this.setButtonWithTwoRedPieceFinishLine();
				else
					this.setButtonWithTwoRed();
			}
			
		} else if (checkColorOfPieceBoardlocation(location,
				LudoStaticValues.BLUEPIECES)) {
			if (location.getPieces().size() == 1){
				if(checkFinishLineColor(location, LudoStaticValues.BLUEFINISHLINE))
					this.setButtonWithBluePieceFinishLine();
				else
					this.setButtonWithBlue();
			} else {
				if(checkFinishLineColor(location, LudoStaticValues.BLUEFINISHLINE))
					this.setButtonWithTwoBluePieceFinishLine();
				else
					this.setButtonWithTwoBlue();
			}

		} else if (checkColorOfPieceBoardlocation(location,
				LudoStaticValues.YELLOWPIECES)) {
			if (location.getPieces().size() == 1){
				if(checkFinishLineColor(location, LudoStaticValues.YELLOWFINISHLINE))
					this.setButtonWithYellowPieceFinishLine();
				else
					this.setButtonWithYellow();
			} else {
				if(checkFinishLineColor(location, LudoStaticValues.YELLOWFINISHLINE))
					this.setButtonWithTwoYellowPieceFinishLine();
				else
					this.setButtonWithTwoYellow();
			}

		} else if (checkColorOfPieceBoardlocation(location,
				LudoStaticValues.GREENPIECES))
			if (location.getPieces().size() == 1){
				if(checkFinishLineColor(location, LudoStaticValues.GREENFINISHLINE))
					this.setButtonWithGreenPieceFinishLine();
				else
					this.setButtonWithGreen();
			} else {
				if(checkFinishLineColor(location, LudoStaticValues.GREENFINISHLINE))
					this.setButtonWithTwoGreenPieceFinishLine();
				else
					this.setButtonWithTwoGreen();
			}
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
