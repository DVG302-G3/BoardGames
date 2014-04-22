package boardgames.g3.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import boardgames.g3.Input_OutPutUnits.SolitarGUIOutputUnit;

public class BoardGamesSolitarGUI extends JPanel{

	private JPanel topPanel, topPanelBeadsLeft, topPanelBeadsTaken, midPanel;
 
	
	public BoardGamesSolitarGUI() {
		createComponents();
		setUpPanels();

		
		setNewMidPanel(new SolitarGUIOutputUnit());
	
	}

	private void createComponents() {
	  

		topPanel = new JPanel(new GridLayout(0, 2));
		topPanelBeadsLeft = new JPanel();
		topPanelBeadsTaken = new JPanel();

		midPanel = new JPanel();
		
	}

	private void setUpPanels() {

		topPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Solitär",
				TitledBorder.LEFT, TitledBorder.TOP));

		topPanelBeadsLeft.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Beads Left",
				TitledBorder.LEFT, TitledBorder.TOP));

		topPanelBeadsTaken.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Beads Taken",
				TitledBorder.LEFT, TitledBorder.TOP));

		topPanel.add(topPanelBeadsLeft);
		topPanel.add(topPanelBeadsTaken);

		
		this.setLayout(new BorderLayout());
	  this.add(topPanel, BorderLayout.NORTH);
	  this.add(midPanel, BorderLayout.CENTER);

	}
	
	private void setNewMidPanel(JPanel newMidPanel){
	 this.midPanel.removeAll();
	 this.midPanel.invalidate();
	 this.midPanel.add(newMidPanel);
	 
//	 newMidPanel.add(backgroundLabel);
//   this.add(newMidPanel, BorderLayout.CENTER);
   this.revalidate();
	 
	}

}

