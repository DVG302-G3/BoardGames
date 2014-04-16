package boardgames.g3.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import boardgames.g3.GUI.BoardGamesFiaMedKnuffGUI.BoardGamesListenersDice;

public class BoardGamesSolitarGUI extends JPanel {

	private ImageIcon mainBackground;
	private JLabel backgroundLabel;
	
	private JPanel topPanel,
     			   topPanelBeadsLeft,
     			   topPanelBeadsTaken,
     			   midPanel;

	public BoardGamesSolitarGUI() {
		createComponents();
	}

	private void createComponents() {
		mainBackground = new ImageIcon("src\\boardgames\\img\\menubackgroundFMK.png");
		backgroundLabel = new JLabel(mainBackground);
	
		topPanel = new JPanel(new GridLayout(0, 2));
		topPanelBeadsLeft = new JPanel();
		topPanelBeadsTaken = new JPanel();
		
		midPanel = new JPanel();
		
		
	}

	private void setUpPanels() {

		  
		  topPanel.setBorder(BorderFactory.createTitledBorder(
		    BorderFactory.createEtchedBorder(), "Fia Med Knuff",
		    TitledBorder.LEFT, 
		    TitledBorder.TOP));
		  
		  
		  topPanelBeadsLeft.setBorder(BorderFactory.createTitledBorder(
		    BorderFactory.createEtchedBorder(), "Players",
		    TitledBorder.LEFT, 
		    TitledBorder.TOP));
		  
		  
		  topPanelBeadsTaken.setBorder(BorderFactory.createTitledBorder(
		    BorderFactory.createEtchedBorder(), "Finished Pieces", 
		    TitledBorder.LEFT,
		    TitledBorder.TOP));
		  
		  
		  topPanel.add(topPanelBeadsLeft);
		  topPanel.add(topPanelBeadsTaken);
		  
		  midPanel.setLayout(new GridLayout(1, 1));
		  midPanel.add(backgroundLabel);
		  

		  setLayout(new BorderLayout());
		  this.add(topPanel, BorderLayout.NORTH);
		  this.add(midPanel, BorderLayout.CENTER);
		  
		 }
	
}
