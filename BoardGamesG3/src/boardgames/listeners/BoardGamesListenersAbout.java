package boardgames.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class BoardGamesListenersAbout implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		JOptionPane.showMessageDialog(null, "This is a school project for our course "
									      + "to create a platform with board games on top");
		
	}

}
