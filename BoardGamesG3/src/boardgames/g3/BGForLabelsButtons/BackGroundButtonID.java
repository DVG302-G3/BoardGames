package boardgames.g3.BGForLabelsButtons;

import game.impl.BoardLocation;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BackGroundButtonID extends JButton {

 private String cordinate;
 
 public BackGroundButtonID(String cordinate){
  this.cordinate = cordinate;
  
	setBorderPainted(false);
	setContentAreaFilled(false);
    setFocusPainted(false);
 }
 
 public String getStringId(){
  return cordinate;
 }
 
 public void setButtonEmpty(){
	 setIcon(new ImageIcon("src\\boardgames\\img\\EmptyBead.png"));
 }
 
 public void setButtonWithBead(){
	 setIcon(new ImageIcon("src\\boardgames\\img\\Bead.png"));
 }
 
 public void setButtonMarked(){
	 setIcon(new ImageIcon("src\\boardgames\\img\\MarkedBead.png"));
 }
 
 public void setButtonEmptyPiece(){
  setIcon(new ImageIcon("src\\boardgames\\img\\emptyPiece.png"));
 }
 
 public void setButtonWithRed(){
  setIcon(new ImageIcon("src\\boardgames\\img\\redPiece.png"));
 }
 
 public void setButtonWithBlue(){
  setIcon(new ImageIcon("src\\boardgames\\img\\bluePiece.png"));
 }
 
 public void setButtonWithYellow(){
  setIcon(new ImageIcon("src\\boardgames\\img\\yellowPiece.png"));
 }
 
 public void setButtonWithGreen(){
  setIcon(new ImageIcon("src\\boardgames\\img\\greenPiece.png"));
 }
 
 public boolean checkIfitsRed(BoardLocation location) {
  return (location.getPiece().getId() == "R1" || location.getPiece().getId() == "R2" || location.getPiece().getId() == "R3" || location.getPiece().getId() == "R4") ;
 }
 
 public boolean checkIfitsBlue(BoardLocation location) {
  return (location.getPiece().getId() == "B1" || location.getPiece().getId() == "B2" || location.getPiece().getId() == "B3" || location.getPiece().getId() == "B4") ;
 }
 
 public boolean checkIfitsYellow(BoardLocation location) {
  return (location.getPiece().getId() == "Y1" || location.getPiece().getId() == "Y2" || location.getPiece().getId() == "Y3" || location.getPiece().getId() == "Y4") ;
 }
 
 public boolean checkIfitsGreen(BoardLocation location) {
  return (location.getPiece().getId() == "G1" || location.getPiece().getId() == "G2" || location.getPiece().getId() == "G3" || location.getPiece().getId() == "G4") ;
 }
 
}
