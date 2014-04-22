package boardgames.g3.GUI;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class BoardGamesSettingsGUI extends JPanel {

 ImagePanel backgroundPanel;

 public BoardGamesSettingsGUI() {

  createAllComponents();
  settingsUpTheJFrameAndPanel();

 }

 private void createAllComponents() {

  backgroundPanel = new ImagePanel(new ImageIcon(
    "src\\boardgames\\img\\menubackground.png").getImage());

 }

 private void settingsUpTheJFrameAndPanel() {

  setBorder(BorderFactory.createTitledBorder(
    BorderFactory.createEtchedBorder(), "Settings", TitledBorder.LEFT,
    TitledBorder.TOP));
  
  add(backgroundPanel);

 }
 
}
