package boardgames.g3.GUI;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BoardGamesSettingsGUI extends JPanel {

 ImageLabel backgroundPanel;

 public BoardGamesSettingsGUI() {

  createAllComponents();
  settingsUpTheJFrameAndPanel();

 }

 private void createAllComponents() {

  backgroundPanel = new ImageLabel(new ImageIcon(
    "src\\boardgames\\img\\menubackgroundSettings.png").getImage());

 }

 private void settingsUpTheJFrameAndPanel() {

  add(backgroundPanel);

 }
 
}
