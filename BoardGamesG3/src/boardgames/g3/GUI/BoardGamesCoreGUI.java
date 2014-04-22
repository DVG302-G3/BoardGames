package boardgames.g3.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import boardgames.listeners.BoardGamesListenersAbout;
import boardgames.listeners.BoardGamesListenersQuit;

public class BoardGamesCoreGUI extends JFrame {

 private JFrame mainFrame;
 private JPanel mainPanel;

 private JMenuBar menuBar;

 private JMenu menuMenu, menuAbout, menuItemNewGame;

 private JMenuItem menuItemNewGameFMK, menuItemNewGameSolitar,
   menuItemNewSettings, menuItemQuit;

 ImagePanel backgroundPanel;

 public BoardGamesCoreGUI() {

  createAllComponents();
  settingsUpTheJFrameAndPanel();

  makesButtons();
 }

 private void createAllComponents() {

  backgroundPanel = new ImagePanel(new ImageIcon(
    "src\\boardgames\\img\\menubackground.png").getImage());

  menuBar = new JMenuBar();
  menuMenu = new JMenu("Menu");
  menuAbout = new JMenu("About");

  menuItemNewGame = new JMenu("Choose Game");
  menuItemNewGameFMK = new JMenuItem("Fia Med Knuff");
  menuItemNewGameSolitar = new JMenuItem("Solitär");

  menuItemNewSettings = new JMenuItem("Settings");
  menuItemQuit = new JMenuItem("Quit");

  mainFrame = new JFrame("BoardGames");
  mainPanel = new JPanel();
 }

 private void settingsUpTheJFrameAndPanel() {

  menuItemNewGame.add(menuItemNewGameFMK);
  menuItemNewGame.add(menuItemNewGameSolitar);

  mainPanel.add(backgroundPanel);

  // Setting up the frame and menubar.
  menuBar.add(menuMenu);
  menuBar.add(menuAbout);

  menuMenu.add(menuItemNewGame);
  menuMenu.add(menuItemNewSettings);
  menuMenu.add(menuItemQuit);

  mainFrame.setJMenuBar(menuBar);
  mainFrame.getContentPane().add(mainPanel);
  // mainFrame.setContentPane(mainPanel);
  mainFrame.setBounds(200, 50, 900, 700);
  mainFrame.setVisible(true);
  mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  mainFrame.setResizable(false);

 }

 private void makesButtons() {

  menuItemQuit.addActionListener(new BoardGamesListenersQuit());

  menuAbout.addActionListener(new BoardGamesListenersAbout());

  menuItemNewGameFMK.addActionListener(new newGamesListenersFiaMedKnuff());
  
  menuItemNewGameSolitar.addActionListener(new newGamesListenersSolitar());

  menuItemNewSettings.addActionListener(new newSettingsListenersSolitar());

 }

 public void setMainPanelContent(JPanel mainContent) {
  this.mainPanel.removeAll();
  this.mainPanel.add(mainContent);
  this.mainPanel.revalidate();
 }
 

 class newGamesListenersFiaMedKnuff implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
   setMainPanelContent(new BoardGamesFiaMedKnuffGUI());
  }
 }

 class newGamesListenersSolitar implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
   setMainPanelContent(new BoardGamesSolitarGUI());
  }
 }

 class newSettingsListenersSolitar implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
   setMainPanelContent(new BoardGamesSettingsGUI());
  }
 }
 
}
