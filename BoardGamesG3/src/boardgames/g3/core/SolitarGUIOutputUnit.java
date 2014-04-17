package boardgames.g3.core;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.io.OutputUnit;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.xml.crypto.KeySelector.Purpose;

public class SolitarGUIOutputUnit extends JPanel implements OutputUnit {

 SolitarGUIInputUnit inputUnit;

 private JButton button[][];

 int ROWS = 7;
 int COLS = 7;

 public SolitarGUIOutputUnit() {
  this.setLayout(new GridLayout(ROWS, COLS));
  inputUnit = new SolitarGUIInputUnit();
  publish(inputUnit.state);
 }

 @Override
 public void publish(GameState gameState) {

  this.removeAll();
  button = new JButton[ROWS][COLS];

  List<BoardLocation> locations = gameState.getBoard().getLocations();

  int index = 0;
  for (int rows = 0; rows < ROWS; rows++) {
   for (int cols = 0; cols < COLS; cols++) {
    button[rows][cols] = new JButton();
    button[rows][cols].addActionListener(new ButtonBeadslisterners());
    this.add(button[rows][cols]);

    String col = locations.get(index).getId();
    GamePiece piece = locations.get(index++).getPiece();

    if (col == null) {
     button[rows][cols].setVisible(false);
    } else {
     if (piece == null)
      button[rows][cols].setText(" ☻ ");
     else
      button[rows][cols].setText(" ☺ ");
    }
   }
  }

  List<BoardLocation> locations2 = gameState.getBoard().getLocations();
  System.out.println("");
  System.out.print("  1 2 3 4 5 6 7");
  int rowCounter = 1;
  for (int i = 0; i < locations2.size(); i++) {
   String col = locations2.get(i).getId();
   GamePiece piece = locations2.get(i).getPiece();

   if (i % COLS == 0) {
    System.out.println();
    System.out.print(rowCounter++ + " ");
   }

   if (col == null) {
    System.out.print("  ");
   } else {
    if (piece == null)
     System.out.print(". ");
    else
     System.out.print("O ");
   }
  }
  this.revalidate();
 }

 class ButtonBeadslisterners implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
   inputUnit.onClick("");
   publish(inputUnit.state);
  }
 }
}
