package boardgames.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boardgames.g3.GUI.BoardGamesNewGamewsGUI;

public class BoardGamesListenersNewGames implements ActionListener {

 @Override
 public void actionPerformed(ActionEvent arg0) {
  new BoardGamesNewGamewsGUI();
  
 }
}
