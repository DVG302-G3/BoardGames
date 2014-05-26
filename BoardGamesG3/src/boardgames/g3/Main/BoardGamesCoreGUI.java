package boardgames.g3.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import boardgames.g3.Runner.LudoGUIRunner;
import boardgames.g3.Runner.SolitarGUIRunner;
import boardgames.g3.SwingComponents.BackGroundLabel;
import boardgames.listeners.BoardGamesListenersAbout;
import boardgames.listeners.BoardGamesListenersQuit;

public class BoardGamesCoreGUI extends JFrame {

	private JFrame mainFrame;
	private JPanel mainPanel;

	private JMenuBar menuBar;

	private JMenu menuMenu, menuItemNewGame, menuAbout;

	private JMenuItem menuItemNewGameFMK, menuItemNewGameSolitar,
			menuItemNewSettings, menuItemQuit, menuItemAbout;

	BackGroundLabel backgroundPanel;

	
	public BoardGamesCoreGUI() {

		createAllComponents();
		settingsUpTheJFrameAndPanel();
		makesButtons();
	}

	private void createAllComponents() {

		backgroundPanel = new BackGroundLabel(new ImageIcon(
				"src\\boardgames\\img\\menubackground.png").getImage());

		menuBar = new JMenuBar();
		menuMenu = new JMenu("Menu");
		menuAbout = new JMenu("About");
		
		menuItemAbout = new JMenuItem("About");
		menuItemNewGame = new JMenu("Choose Game");
		menuItemNewGameFMK = new JMenuItem("Ludo");
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

		menuBar.add(menuMenu);
		menuBar.add(menuAbout);

		menuAbout.add(menuItemAbout);
		
		menuMenu.add(menuItemNewGame);
		menuMenu.add(menuItemNewSettings);
		menuMenu.add(menuItemQuit);

		mainFrame.setJMenuBar(menuBar);
		mainFrame.setContentPane(mainPanel);
		mainFrame.setSize(800, 700);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);

	}

	private void makesButtons() {

		menuItemQuit.addActionListener(new BoardGamesListenersQuit());

		menuItemAbout.addActionListener(new BoardGamesListenersAbout());

		menuItemNewGameFMK
				.addActionListener(new newGamesListenersFiaMedKnuff());

		menuItemNewGameSolitar
				.addActionListener(new newGamesListenersSolitar());

		menuItemNewSettings
				.addActionListener(new newSettingsListenersSolitar());

	}

	private void setNewMainPanelContent(JPanel newMainContent) {
		this.mainPanel.removeAll();
		this.mainPanel.invalidate();
		this.mainPanel.add(newMainContent);
		this.mainFrame.revalidate();
//		mainFrame.pack();
		
		
	}

	class newGamesListenersFiaMedKnuff implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setNewMainPanelContent(new LudoGUIRunner());
		}
	}

	class newGamesListenersSolitar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setNewMainPanelContent(new SolitarGUIRunner());
		}
	}

	class newSettingsListenersSolitar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setNewMainPanelContent(new BoardGamesSettingsGUI());
		}
	}

}