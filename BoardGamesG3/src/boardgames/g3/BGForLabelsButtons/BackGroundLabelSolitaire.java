package boardgames.g3.BGForLabelsButtons;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BackGroundLabelSolitaire extends JLabel {

 private Image img;
 
 private int ROWS;
 private int COLS;
 
 public BackGroundLabelSolitaire(int rows, int cols) {
  this("src\\boardgames\\img\\SolitaireBackground.png");
  this.ROWS = rows;
  this.COLS = cols;
  setLayout(new GridLayout(ROWS, COLS));
}

public BackGroundLabelSolitaire(String img) {
  this(new ImageIcon(img).getImage());
}

public BackGroundLabelSolitaire(Image img) {
  this.img = img;
  Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
  setPreferredSize(size);
  setMinimumSize(size);
  setMaximumSize(size);
  setSize(size);
  setLayout(null);
}

public void paintComponent(Graphics g) {
  g.drawImage(img, 0, 0, null);
}

}