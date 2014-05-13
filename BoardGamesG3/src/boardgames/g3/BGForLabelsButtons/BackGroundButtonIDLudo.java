package boardgames.g3.BGForLabelsButtons;

import game.impl.BoardLocation;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import boardgames.g3.core.Ludo.LudoStaticValues;

public class BackGroundButtonIDLudo extends JButton {

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
	
	private void setButtonWithRedFinishLinePiece() {
    setIcon(new ImageIcon("src\\boardgames\\img\\redPieceFinishLine.png"));
  }

  private void setButtonWithBlueFinishLinePiece() {
    setIcon(new ImageIcon("src\\boardgames\\img\\bluePieceFinishLine.png"));
  }

  private void setButtonWithYellowFinishLinePiece() {
    setIcon(new ImageIcon("src\\boardgames\\img\\yellowPieceFinishLine.png"));
  }

  private void setButtonWithGreenFinishLinePiece() {
    setIcon(new ImageIcon("src\\boardgames\\img\\greenPieceFinishLine.png"));
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

		} else if (location.getId() != null) {
			this.setButtonEmptyPiece();
			this.checkFinishLineColor();
		}
	}

	private void checkFinishLineColor() {

		if (checkRedFinishLineColor(location)) {
			this.setButtonWithRedFinishLine();

		} else if (checkBlueFinishLineColor(location)) {
			this.setButtonWithBlueFinishLine();

		} else if (checkYellowFinishLineColor(location)) {
			this.setButtonWithYellowFinishLine();

		} else if (checkGreenFinishLineColor(location)) {
			this.setButtonWithGreenFinishLine();
		}
	}

	private void checkColorOnThePiece() {
		if (checkIfitsRed(location)) {
			if(location.getPieces().size() == 1)
				this.setButtonWithRed();
			else
				this.setButtonWithTwoRed();
			
		}else if (checkIfitsBlue(location)) {
			if(location.getPieces().size() == 1)
				this.setButtonWithBlue();
			else
				this.setButtonWithTwoBlue();
			
		} else if (checkIfitsYellow(location)) {
			if(location.getPieces().size() == 1)
				this.setButtonWithYellow();
			else
				this.setButtonWithTwoYellow();
			
		} else if (checkIfitsGreen(location))
			if(location.getPieces().size() == 1)
				this.setButtonWithGreen();
			else
				this.setButtonWithTwoGreen();
	}
	

	private boolean checkRedFinishLineColor(BoardLocation location) {

		for (String s : LudoStaticValues.REDFINISHLINE)
			if (s.equals(location.getId()))
				return true;
		return false;
	}

	private boolean checkBlueFinishLineColor(BoardLocation location) {
		for (String s : LudoStaticValues.BLUEFINISHLINE)
			if (s.equals(location.getId()))
				return true;
		return false;
	}

	private boolean checkYellowFinishLineColor(BoardLocation location) {
		for (String s : LudoStaticValues.YELLOWFINISHLINE)
			if (s.equals(location.getId()))
				return true;
		return false;
	}

	private boolean checkGreenFinishLineColor(BoardLocation location) {
		for (String s : LudoStaticValues.GREENFINISHLINE)
			if (s.equals(location.getId()))
				return true;
		return false;
	}

	private boolean checkIfitsRed(BoardLocation location) {
		return (location.getPiece().getId() == "R1"
				|| location.getPiece().getId() == "R2"
				|| location.getPiece().getId() == "R3" || location.getPiece()
				.getId() == "R4");
	}

	private boolean checkIfitsBlue(BoardLocation location) {
		return (location.getPiece().getId() == "B1"
				|| location.getPiece().getId() == "B2"
				|| location.getPiece().getId() == "B3" || location.getPiece()
				.getId() == "B4");
	}

	private boolean checkIfitsYellow(BoardLocation location) {
		return (location.getPiece().getId() == "Y1"
				|| location.getPiece().getId() == "Y2"
				|| location.getPiece().getId() == "Y3" || location.getPiece()
				.getId() == "Y4");
	}

	private boolean checkIfitsGreen(BoardLocation location) {
		return (location.getPiece().getId() == "G1"
				|| location.getPiece().getId() == "G2"
				|| location.getPiece().getId() == "G3" || location.getPiece()
				.getId() == "G4");
	}

}
