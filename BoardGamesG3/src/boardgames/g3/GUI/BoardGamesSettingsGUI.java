package boardgames.g3.GUI;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import boardgames.g3.BGForLabelsButtons.BackGroundLabel;

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
