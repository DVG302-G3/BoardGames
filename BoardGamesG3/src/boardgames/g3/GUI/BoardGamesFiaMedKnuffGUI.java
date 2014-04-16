package boardgames.g3.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import boardgames.listeners.diceChooser;

public class BoardGamesFiaMedKnuffGUI extends JPanel {

 private Random rand = new Random();
 
 private ImageIcon mainBackground;
 private JLabel backgroundLabel;

 private JButton diceButton;
 
 private JPanel topPanel,
                topPanelPlayers,
                topPanelFinished,
                topPanelWhoPlay,
                topPanelDice,
                midPanel; 
 
 private JCheckBox redC, 
                   blueC, 
                   yellowC, 
                   greenC; 
 
 private JCheckBox diceCheckBox;
 
 private ButtonGroup buttonGroup;

 public BoardGamesFiaMedKnuffGUI() {
  
  createComponents();
  setUpPanels();
  setUpButtonGroupAndCheckBoxes();
 }

 private void createComponents(){
  mainBackground    = new ImageIcon("src\\boardgames\\img\\menubackgroundFMK.png");
  backgroundLabel   = new JLabel(mainBackground);

  topPanel          = new JPanel(new GridLayout(0, 4));
  topPanelPlayers   = new JPanel(new GridLayout(0, 2));
  topPanelFinished  = new JPanel(new GridLayout(0, 2));
  topPanelWhoPlay   = new JPanel(new GridLayout(4, 0));
  topPanelDice      = new JPanel(new GridLayout(0, 2));
  
  midPanel          = new JPanel();
  
  buttonGroup       = new ButtonGroup();
  
  redC              = new JCheckBox();
  blueC             = new JCheckBox();
  yellowC           = new JCheckBox();
  greenC            = new JCheckBox();
  diceCheckBox      = new JCheckBox();
  
  diceButton        = new JButton("Roll Dice");
 }
 
 private void setUpButtonGroupAndCheckBoxes(){
  greenC.setIcon(new ImageIcon("src\\boardgames\\img\\green.jpg"));
  redC.setIcon(new ImageIcon("src\\boardgames\\img\\red.jpg"));
  yellowC.setIcon(new ImageIcon("src\\boardgames\\img\\yellow.jpg"));
  blueC.setIcon(new ImageIcon("src\\boardgames\\img\\blue.jpg"));
  
  greenC.setText("Mattias");
  redC.setText("Anders");
  yellowC.setText("Maya");
  blueC.setText("Cissan");
  
  
  buttonGroup.add(greenC);
  buttonGroup.add(redC);
  buttonGroup.add(yellowC);
  buttonGroup.add(blueC);
  
  diceCheckBox.setIcon(new ImageIcon("src\\boardgames\\img\\no_dice.png"));  
 }
 
 private void setUpPanels() {

  
  topPanel.setBorder(BorderFactory.createTitledBorder(
    BorderFactory.createEtchedBorder(), "Fia Med Knuff",
    TitledBorder.LEFT, 
    TitledBorder.TOP));
  
  
  topPanelPlayers.setBorder(BorderFactory.createTitledBorder(
    BorderFactory.createEtchedBorder(), "Players",
    TitledBorder.LEFT, 
    TitledBorder.TOP));
  topPanelPlayers.add(greenC);
  topPanelPlayers.add(redC);
  topPanelPlayers.add(yellowC);
  topPanelPlayers.add(blueC);
  
  
  topPanelFinished.setBorder(BorderFactory.createTitledBorder(
    BorderFactory.createEtchedBorder(), "Finished Pieces", 
    TitledBorder.LEFT,
    TitledBorder.TOP));
//  topPanelFinished.add(piecesGreen);
//  topPanelFinished.add(piecesRed);
//  topPanelFinished.add(piecesYellow);
//  topPanelFinished.add(piecesBlue);
  
  
  
  topPanelWhoPlay.setBorder(BorderFactory.createTitledBorder(
    BorderFactory.createEtchedBorder(), "Whos Turn", 
    TitledBorder.LEFT,
    TitledBorder.TOP));

  
  
  topPanelDice.setBorder(BorderFactory.createTitledBorder(
    BorderFactory.createEtchedBorder(), "Roll The Dice", 
    TitledBorder.LEFT,
    TitledBorder.TOP)); 
  topPanelDice.add(diceButton);
  topPanelDice.add(diceCheckBox);
  
  topPanel.add(topPanelPlayers);
  topPanel.add(topPanelFinished);
  topPanel.add(topPanelWhoPlay);
  topPanel.add(topPanelDice);
  
  midPanel.setLayout(new GridLayout(1, 1));
  midPanel.add(backgroundLabel);
  

  setLayout(new BorderLayout());
  this.add(topPanel, BorderLayout.NORTH);
  this.add(midPanel, BorderLayout.CENTER);
  
  diceButton.addActionListener(new BoardGamesListenersDice());
  
 }
 
 public void setDiceCheckBox(JCheckBox diceCheckBoxNew){
  topPanelDice.removeAll();
  topPanelDice.add(diceButton);
  topPanelDice.add(diceCheckBoxNew);
  this.revalidate();
 }

 class BoardGamesListenersDice implements ActionListener{

  @Override
  public void actionPerformed(ActionEvent e) {
   setDiceCheckBox(new diceChooser());
   
  } 
 } 
}
