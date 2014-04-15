package boardgames.g3.GUI;

import javax.swing.ImageIcon;

public class BoardGamesMain {

 static String title = "Menu";
 static ImageIcon mainTitle = new ImageIcon(
   "src\\boardgames\\img\\menubackground.png");
 
 public static void main(String[] args) {
  new BoardGamesCoreGUI(title, mainTitle);
 }

}
