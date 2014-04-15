package boardgames.g3.GUI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BoardGamesSolitarGUI extends JPanel {

 
 private ImageIcon mainBackground = new ImageIcon(
   "src\\boardgames\\img\\gameboardfmk.png");
 private JLabel backgroundLabel; 
 
 
 public BoardGamesSolitarGUI(){
  backgroundLabel = new JLabel(mainBackground);
  this.add(backgroundLabel);
 }
 
 
}
