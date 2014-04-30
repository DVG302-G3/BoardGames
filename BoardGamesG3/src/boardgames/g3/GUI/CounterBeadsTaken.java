package boardgames.g3.GUI;

import javax.swing.JTextArea;

public class CounterBeadsTaken extends JTextArea {

 int beadsTaken;

 public CounterBeadsTaken(int beadsTaken) {
  this.beadsTaken = beadsTaken;
  
  setText("Beads Taken: " + Integer.toString(this.beadsTaken));  
  setEditable(false);
  setOpaque(false);
  setFocusable(false);
  }

 public void update(){
  this.removeAll();
  setText("Beads Taken: " + Integer.toString(beadsTaken++));  
  this.revalidate();
 }
}