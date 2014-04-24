package boardgames.g3.core.FiaMedKnuff;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GUIGridButtonID extends JButton {

 private String cordinate;
 
 public GUIGridButtonID(String cordinate){
  this.cordinate = cordinate;
  
	setBorderPainted(false);
	setContentAreaFilled(false);
    setFocusPainted(false);
 }
 
 public String getStringId(){
  return cordinate;
 }
 
 public void setButtonEmptyBead(){
	 setIcon(new ImageIcon("src\\boardgames\\img\\emptybeadsolitaire.png"));
 }
 
 public void setButtonWithBead(){
	 setIcon(new ImageIcon("src\\boardgames\\img\\beadsolitaire.png"));
 }
 
 public void setButtonMarked(){
	 setIcon(new ImageIcon("src\\boardgames\\img\\beadsolitaireMarked.png"));
	 
 }
}
