package boardgames.g3.GUI;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import boardgames.listeners.BoardGamesListenersNewGames;
import boardgames.listeners.BoardGamesListenersNewSettings;
import boardgames.listeners.BoardGamesListenersQuit;

public class BoardGamesCoreGUI extends JFrame {

 private String mainTitle = "";
 
 private JFrame mainFrame; 
 private JPanel mainPanel;
 private JPanel mainBorderTitlePanel;
// private JPanel mainMenuPanel;
 
 // Buttons
// private JButton quitButton = new JButton("Avsluta");
// private JButton gamesButton = new JButton("Games");
// private JButton settingButton = new JButton("Settings");
 
 
 private JMenuBar menuBar = new JMenuBar();
 private JMenu menuMenu = new JMenu("Menu");
 private JMenu menuAbout = new JMenu("About");
 
 private JMenuItem menuItemNewGame = new JMenuItem("New Game");
 private JMenuItem menuItemNewSettings = new JMenuItem("Settings");
 private JMenuItem menuItemQuit = new JMenuItem("Quit");
 
 private ImageIcon backgroundImage; 
 private JLabel backgroundLabel;


 public BoardGamesCoreGUI(String mainTitle, ImageIcon backgroundImage){
  this.mainTitle = mainTitle;
  this.backgroundImage = backgroundImage;
  
  settingsUpTheJFrameAndPanel();
  makesButtons();
 }

 public void settingsUpTheJFrameAndPanel(){
  backgroundLabel = new JLabel(backgroundImage);
  mainFrame = new JFrame("BoardGames");
  mainBorderTitlePanel = new JPanel();
  mainPanel = new JPanel();
//  mainMenuPanel = new JPanel();

  mainBorderTitlePanel.setBorder(BorderFactory.createTitledBorder(
    BorderFactory.createEtchedBorder(), mainTitle,
    TitledBorder.LEFT, TitledBorder.TOP));

  
//  mainMenuPanel.add(gamesButton);
//  mainMenuPanel.add(settingButton);
//  mainMenuPanel.add(quitButton);
//  
//  mainPanel.add(mainMenuPanel);
  
  mainPanel.add(backgroundLabel);
  
  mainBorderTitlePanel.add(mainPanel);
  
  // Setting up the frame and menubar. 
  menuBar.add(menuMenu);
  menuBar.add(menuAbout);
  
  menuMenu.add(menuItemNewGame);
  menuMenu.add(menuItemNewSettings);  
  menuMenu.add(menuItemQuit);
    
  mainFrame.setJMenuBar(menuBar);
  mainFrame.setContentPane(mainBorderTitlePanel);
  mainFrame.setBounds(200, 50, 900, 700);
  mainFrame.setVisible(true);
  mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  mainFrame.setResizable(false);
  
  
 }
 
 private void makesButtons() {

  menuItemQuit.addActionListener(new BoardGamesListenersQuit());
  
//  quitButton.addActionListener(new BoardGamesListenersQuit()); 

  menuItemNewGame.addActionListener(new BoardGamesListenersNewGames());

  menuItemNewSettings.addActionListener(new BoardGamesListenersNewSettings());

}
 
 
 
}
