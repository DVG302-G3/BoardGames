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
	private JPanel mainBorderTitlePanel;

	private String title;

	private JMenuBar menuBar;
	private JMenu menuMenu;
	private JMenu menuAbout;

	private JMenuItem menuItemNewGame;
	private JMenuItem menuItemNewSettings;
	private JMenuItem menuItemQuit;

	private ImageIcon backgroundImage;
	private JLabel backgroundLabel;

	public BoardGamesCoreGUI() {

		createAllComponents();
		settingsUpTheJFrameAndPanel();
		setMainTitle("Menu");
		
		makesButtons();
	}

	private void createAllComponents() {
		backgroundImage = new ImageIcon("src\\boardgames\\img\\menubackground.png");
		backgroundLabel = new JLabel(backgroundImage);
		
		menuBar = new JMenuBar();
		menuMenu = new JMenu("Menu");
		menuAbout = new JMenu("About");
		
		menuItemNewGame = new JMenuItem("Choose Game");
		menuItemNewSettings = new JMenuItem("Settings");
		menuItemQuit = new JMenuItem("Quit");
		
		mainFrame = new JFrame("BoardGames");
		mainBorderTitlePanel = new JPanel();
		
		mainPanel = new JPanel();
	}

	private void settingsUpTheJFrameAndPanel() {
	
		menuMenu.setMnemonic(KeyEvent.VK_1);
		
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

		// quitButton.addActionListener(new BoardGamesListenersQuit());

		menuAbout.addActionListener(new BoardGamesListenersAbout());

		menuItemNewGame.addActionListener(new newGamesListeners());

		menuItemNewSettings
				.addActionListener(new BoardGamesListenersNewSettings());

	}
	public void setMainTitle(String title){
		this.title = title;
		mainBorderTitlePanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), title,
				TitledBorder.LEFT, TitledBorder.TOP));
		mainFrame.revalidate();
	}
	
	public void setMainPanelContent(JPanel mainConcent){
		this.mainPanel.removeAll();
		mainPanel.add(mainConcent);
		mainFrame.revalidate();
		}

	
	class newGamesListeners implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			setMainPanelContent(new BoardGamesNewGamewsGUI());
			setMainTitle("GAMES");
			
		}
		
		
		
		
	}
	
}


