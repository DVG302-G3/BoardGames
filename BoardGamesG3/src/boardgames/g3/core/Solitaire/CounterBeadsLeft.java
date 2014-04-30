package boardgames.g3.core.Solitaire;

import javax.swing.JTextArea;

public class CounterBeadsLeft extends JTextArea {

 int beadsLeft;

 public CounterBeadsLeft(int beadsLeft) {
  this.beadsLeft = beadsLeft;
  
  setText("Beads Left: " + Integer.toString(this.beadsLeft));
  setEditable(false);
  setOpaque(false);
  setFocusable(false);
  }

 public void update(){
  this.removeAll();
  setText("Beads Left: " + Integer.toString(beadsLeft--));  
  this.revalidate();
 
 }
}
