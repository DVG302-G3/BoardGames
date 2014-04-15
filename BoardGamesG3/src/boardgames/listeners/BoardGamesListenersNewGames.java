package boardgames.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boardgames.g3.GUI.BoardGamesSolitarGUI;

public class BoardGamesListenersNewGames implements ActionListener {

 @Override
 public void actionPerformed(ActionEvent arg0) {
  new BoardGamesSolitarGUI();
  
 }
}
