package boardgames.g3.Input_OutPutUnits;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.io.OutputUnit;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import boardgames.g3.BGForLabelsButtons.BackGroundButtonID;
import boardgames.g3.BGForLabelsButtons.BackGroundLabelSolitaire;
import boardgames.g3.core.Solitaire.SolitarCounterBeads;
import boardgames.g3.core.Solitaire.SolitarJOptionFinish;
import boardgames.g3.core.Solitaire.SolitarStaticValue;
import boardgames.g3.core.Solitaire.SolitarTimer;

public class SolitarGUIOutputUnit extends JPanel implements OutputUnit {

 BackGroundLabelSolitaire backgroundLabel;

 SolitarTimer timer;

 SolitarCounterBeads counterBeads;

 private JTextArea textAreaBeadsLeft, textAreaBeadsTaken;

 private SolitarJOptionFinish optionLabel;

 private ActionListener inputUnit;

 private JPanel topPanel, topPanelBeadsLeft, topPanelBeadsTaken, topPanelTimer,
   mainPanel;

 private BackGroundButtonID button[][];

 public SolitarGUIOutputUnit(SolitarGUIInputUnit inputUnit) {
  this.inputUnit = inputUnit;
  createComponent();
  settingUpComponents();
 }

 private void createComponent() {
  backgroundLabel = new BackGroundLabelSolitaire(SolitarStaticValue.ROW,
    SolitarStaticValue.COL);
  timer = new SolitarTimer();

  textAreaBeadsLeft = new JTextArea();
  textAreaBeadsTaken = new JTextArea();

  counterBeads = new SolitarCounterBeads(SolitarStaticValue.BEADS_TOTAL);

  optionLabel = new SolitarJOptionFinish();

  topPanel = new JPanel(new GridLayout(0, 3));
  topPanelBeadsLeft = new JPanel();
  topPanelBeadsTaken = new JPanel();
  topPanelTimer = new JPanel();

  mainPanel = new JPanel();

 }

 private void settingUpComponents() {

  topPanel.setBorder(BorderFactory.createTitledBorder(
    BorderFactory.createEtchedBorder(), "Solitär", TitledBorder.LEFT,
    TitledBorder.TOP));

  topPanelBeadsLeft.setBorder(BorderFactory.createTitledBorder(
    BorderFactory.createEtchedBorder(), "Beads Left", TitledBorder.LEFT,
    TitledBorder.TOP));

  textAreaBeadsLeft.setOpaque(false);
  textAreaBeadsLeft.setFocusable(false);
  textAreaBeadsLeft.setText(counterBeads.getBeadsLeft());

  topPanelBeadsLeft.add(textAreaBeadsLeft);

  topPanelBeadsTaken.setBorder(BorderFactory.createTitledBorder(
    BorderFactory.createEtchedBorder(), "Beads Taken", TitledBorder.LEFT,
    TitledBorder.TOP));
  textAreaBeadsTaken.setOpaque(false);
  textAreaBeadsTaken.setFocusable(false);
  textAreaBeadsTaken.setText(counterBeads.getBeadsTaken());

  topPanelBeadsTaken.add(textAreaBeadsTaken);

  topPanelTimer.setBorder(BorderFactory.createTitledBorder(
    BorderFactory.createEtchedBorder(), "Timer", TitledBorder.LEFT,
    TitledBorder.TOP));
  topPanelTimer.add(timer);

  topPanel.add(topPanelBeadsLeft);
  topPanel.add(topPanelBeadsTaken);
  topPanel.add(topPanelTimer);

  mainPanel.add(backgroundLabel);

  this.setLayout(new BorderLayout());
  this.add(topPanel, BorderLayout.NORTH);
  this.add(mainPanel, BorderLayout.CENTER);

 }

 @Override
 public void publish(GameState gameState) {

  backgroundLabel.removeAll();

  List<BoardLocation> locations = gameState.getBoard().getLocations();

  button = new BackGroundButtonID[SolitarStaticValue.ROW][SolitarStaticValue.COL];
  counterBeads = new SolitarCounterBeads(SolitarStaticValue.BEADS_TOTAL);

  int index = 0;
  for (int rows = 0; rows < SolitarStaticValue.ROW; rows++) {
   for (int cols = 0; cols < SolitarStaticValue.COL; cols++) {
    button[rows][cols] = new BackGroundButtonID(Integer.toString((rows + 1))
      + Integer.toString((cols + 1)));

    button[rows][cols].addActionListener(inputUnit);

    backgroundLabel.add(button[rows][cols]);

    GamePiece piece = null;

    if (locations.get(index) != null) {
     piece = locations.get(index).getPiece();

     if (piece == null) {
      button[rows][cols].setButtonEmptyBead();
      counterBeads.update();
     } else
      button[rows][cols].setButtonWithBead();

    } else {
     button[rows][cols].setVisible(false);

    }
    index++;

    textAreaBeadsLeft.setText(counterBeads.getBeadsLeft());
    textAreaBeadsTaken.setText(counterBeads.getBeadsTaken());

   }
  }

  while (gameState.hasEnded()) {
   timer.pause();

   optionLabel.dataTaker(timer.getCountTime(), counterBeads.getBeadsTaken(),
                         counterBeads.getBeadsLeft());
   
   optionLabel.displayGUI();

   
   if (optionLabel.getReturnValue() == 0) {

   }
   if (optionLabel.getReturnValue() == 1) {
    gameState.reset();
   }
  }

  topPanelBeadsLeft.revalidate();
  topPanelBeadsTaken.revalidate();
  topPanelTimer.revalidate();
  backgroundLabel.revalidate();

 }

}
