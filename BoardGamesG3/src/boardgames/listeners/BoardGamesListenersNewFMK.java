package boardgames.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import boardgames.g3.GUI.BoardGamesGUI;

public class BoardGamesListenersNewFMK implements ActionListener {

	BoardGamesGUI boardGames;
	private ImageIcon background;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		background = new ImageIcon(
				"src\\boardgames\\img\\gameboardfmk.png");
		boardGames.setBackGround(background);
	}

}
