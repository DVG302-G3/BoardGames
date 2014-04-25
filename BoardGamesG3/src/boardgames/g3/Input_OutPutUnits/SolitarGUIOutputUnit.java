package boardgames.g3.Input_OutPutUnits;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.io.OutputUnit;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;

import boardgames.g3.BGForLabelsButtons.BackGroundButtonID;
import boardgames.g3.BGForLabelsButtons.BackGroundLabelSolitaire;

public class SolitarGUIOutputUnit extends JPanel implements OutputUnit {

 BackGroundLabelSolitaire backgroundLabel;

 private SolitarGUIInputUnit inputUnit;

 private BackGroundButtonID button[][];

 int ROWS = 7;
 int COLS = 7;

 public SolitarGUIOutputUnit(SolitarGUIInputUnit inputUnit) {
  this.inputUnit = inputUnit;
  backgroundLabel = new BackGroundLabelSolitaire(ROWS, COLS);
  setLayout(new BorderLayout());
  add(backgroundLabel);
 }

 @Override
 public void publish(GameState gameState) {

  backgroundLabel.removeAll();

  List<BoardLocation> locations = gameState.getBoard().getLocations();

  button = new BackGroundButtonID[ROWS][COLS];


   int index = 0;
   for (int rows = 0; rows < ROWS; rows++) {
    for (int cols = 0; cols < COLS; cols++) {
     button[rows][cols] = new BackGroundButtonID(Integer.toString((rows + 1))
       + Integer.toString((cols + 1)));

     button[rows][cols].addActionListener(inputUnit);

     backgroundLabel.add(button[rows][cols]);

     String col = locations.get(index).getId();
     GamePiece piece = locations.get(index++).getPiece();

     if (col == null) {
      button[rows][cols].setVisible(false);
     } else {
      if (piece == null)
       button[rows][cols].setButtonEmptyBead();
      else
       button[rows][cols].setButtonWithBead();
     }
    }
   }

   backgroundLabel.revalidate();
  
 }

}
