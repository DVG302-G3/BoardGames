package boardgames.g3.Main;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import boardgames.g3.SwingComponents.BackGroundLabel;

public class BoardGamesSettingsGUI extends JPanel {

 BackGroundLabel backgroundPanel;

 public BoardGamesSettingsGUI() {

  createAllComponents();
  settingsUpTheJFrameAndPanel();

 }

 private void createAllComponents() {

  backgroundPanel = new BackGroundLabel(new ImageIcon(
    "src\\boardgames\\img\\menubackgroundSettings.png").getImage());

 }

 private void settingsUpTheJFrameAndPanel() {

  add(backgroundPanel);

 }
 
}
