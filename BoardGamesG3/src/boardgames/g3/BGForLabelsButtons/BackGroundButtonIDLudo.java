package boardgames.g3.BGForLabelsButtons;

import game.impl.BoardLocation;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BackGroundButtonIDLudo extends JButton {

 private String coordinate;
 BoardLocation location;
 
 public BackGroundButtonIDLudo(BoardLocation location, String coordinate){
  this.coordinate = coordinate;
  this.location = location;
  
  checkLocationContent(); 
  
	setBorderPainted(false);
	setContentAreaFilled(false);
    setFocusPainted(false);
 }
 
 public String getStringId(){
  return coordinate;
 }
 
 private void setButtonEmptyPiece(){
  setIcon(new ImageIcon("src\\boardgames\\img\\emptyPiece.png"));
 }
 
 private void setButtonWithRed(){
  setIcon(new ImageIcon("src\\boardgames\\img\\redPiece.png"));
 }
 
 private void setButtonWithBlue(){
  setIcon(new ImageIcon("src\\boardgames\\img\\bluePiece.png"));
 }
 
 private void setButtonWithYellow(){
  setIcon(new ImageIcon("src\\boardgames\\img\\yellowPiece.png"));
 }
 
 private void setButtonWithGreen(){
  setIcon(new ImageIcon("src\\boardgames\\img\\greenPiece.png"));
 }
 
 private void checkLocationContent(){
	 String col;
		if (location == null)
			col = null;
		else
			col = location.getId();

		if (col == null) {
			this.setVisible(false);

		} else if (location.getPiece() != null) {
			this.checkColorOnThePiece();
		} else {
			this.setButtonEmptyPiece();
		}
 }
 
 
 private void checkColorOnThePiece(){
	 if (checkIfitsRed(location))
			this.setButtonWithRed();

		else if (checkIfitsBlue(location))
			this.setButtonWithBlue();

		else if (checkIfitsYellow(location))
			this.setButtonWithYellow();

		else if (checkIfitsGreen(location))
			this.setButtonWithGreen();
 }
 
 private boolean checkIfitsRed(BoardLocation location) {
  return (location.getPiece().getId() == "R1" || location.getPiece().getId() == "R2" || location.getPiece().getId() == "R3" || location.getPiece().getId() == "R4") ;
 }
 
 private boolean checkIfitsBlue(BoardLocation location) {
  return (location.getPiece().getId() == "B1" || location.getPiece().getId() == "B2" || location.getPiece().getId() == "B3" || location.getPiece().getId() == "B4") ;
 }
 
 private boolean checkIfitsYellow(BoardLocation location) {
  return (location.getPiece().getId() == "Y1" || location.getPiece().getId() == "Y2" || location.getPiece().getId() == "Y3" || location.getPiece().getId() == "Y4") ;
 }
 
 private boolean checkIfitsGreen(BoardLocation location) {
  return (location.getPiece().getId() == "G1" || location.getPiece().getId() == "G2" || location.getPiece().getId() == "G3" || location.getPiece().getId() == "G4") ;
 }
 
}
