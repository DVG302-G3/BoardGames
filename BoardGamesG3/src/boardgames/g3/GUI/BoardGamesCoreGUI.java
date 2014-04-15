package boardgames.g3.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import boardgames.listeners.BoardGamesListenersAbout;
import boardgames.listeners.BoardGamesListenersNewSettings;
import boardgames.listeners.BoardGamesListenersQuit;

public class BoardGamesCoreGUI extends JFrame {

	private JFrame mainFrame;
	private JPanel mainPanel;

	private String title;

	private JMenuBar menuBar;
	 
	private JMenu menuMenu, 
	              menuAbout, 
	              menuItemNewGame;
	
	private JMenuItem menuItemNewGameFMK,
	                  menuItemNewGameSolitar,
	                  menuItemNewSettings,
	                  menuItemQuit;

	private ImageIcon backgroundImage;
	private JLabel backgroundLabel;

	public BoardGamesCoreGUI() {

		createAllComponents();
		settingsUpTheJFrameAndPanel();

		
		makesButtons();
	}

	private void createAllComponents() {
		backgroundImage = new ImageIcon("src\\boardgames\\img\\menubackground.png");
		backgroundLabel = new JLabel(backgroundImage);
		
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
		
		mainPanel.add(backgroundLabel);

		// Setting up the frame and menubar.
		menuBar.add(menuMenu);
		menuBar.add(menuAbout);

		menuMenu.add(menuItemNewGame);
		menuMenu.add(menuItemNewSettings);
		menuMenu.add(menuItemQuit);

		mainFrame.setJMenuBar(menuBar);
		mainFrame.setContentPane(mainPanel);
		mainFrame.setBounds(200, 50, 900, 700);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);

	}

	
	private void makesButtons() {

		menuItemQuit.addActionListener(new BoardGamesListenersQuit());

		// quitButton.addActionListener(new BoardGamesListenersQuit());

		menuAbout.addActionListener(new BoardGamesListenersAbout());

		menuItemNewGameFMK.addActionListener(new newGamesListenersFiaMedKnuff());
		menuItemNewGameSolitar.addActionListener(new newGamesListenersSolitar());

		menuItemNewSettings
				.addActionListener(new BoardGamesListenersNewSettings());

	}
	
	public void setMainPanelContent(JPanel mainContent){
		this.mainPanel.removeAll();
		mainPanel.add(mainContent);
		mainFrame.revalidate();
		}


	
	class newGamesListenersFiaMedKnuff implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			setMainPanelContent(new BoardGamesFiaMedKnuffGUI());
	
			
		}
	}	
	
 class newGamesListenersSolitar implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent e) {
      setMainPanelContent(new BoardGamesSolitarGUI());   
    }
  } 
	
}


