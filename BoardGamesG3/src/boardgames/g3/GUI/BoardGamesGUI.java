package boardgames.g3.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import boardgames.listeners.BoardGamesListenersMouse;
import boardgames.listeners.BoardGamesListenersNewFMK;
import boardgames.listeners.BoardGamesListenersNewSolitar;
import boardgames.listeners.BoardGamesListenersQuit;

public class BoardGamesGUI {

	private JFrame frameR = new JFrame("BoarGames Of Eve");

	public ImageIcon background; 

	private JLabel setBackground = new JLabel(background);

	// Top Panels
	private JPanel topPanel = new JPanel(new GridLayout(0, 4));
	private JPanel top1Panel = new JPanel(new GridLayout(2, 0));
	private JPanel top2Panel = new JPanel(new GridLayout(4, 0));

	// Mid-Bot-Main Panels
	private JPanel midPanel = new JPanel();
	private JPanel botPanel = new JPanel();
	private JPanel mainPanel = new JPanel();

	// Buttons
	private JButton quitButton = new JButton("Avsluta");
	private JButton fiaMedKnuffButton = new JButton("Fia Med Knuff");
	private JButton solitarButton = new JButton("Solitär");

	private JRadioButton player = new JRadioButton("1 Player"); 
	private JRadioButton player2 = new JRadioButton("2 Player");
	private JRadioButton player3 = new JRadioButton("3 Player");
	private JRadioButton player4 = new JRadioButton("4 Player");

	
	private ButtonGroup playerButtonGroup = new ButtonGroup();
	
	public static void main(String[] args) {

		new BoardGamesGUI();
	}

	public BoardGamesGUI() {
		background = new ImageIcon(
				"src\\boardgames\\img\\background.jpeg");
		setBackground = new JLabel(background);
		
		makesPanels();
		makesButtons();

	}
	
	public void setBackGround(ImageIcon bg){
		this.background = bg;
	}

	public ImageIcon getBackGround(){
		return background;
	}
	
	// Creates the panels, the button group and sets up the GUI.
	private void makesPanels() {
		
		playerButtonGroup.add(player);
		playerButtonGroup.add(player2);
		playerButtonGroup.add(player3);
		playerButtonGroup.add(player4);
		
		// Setting up for the topPanel
		topPanel.setBorder(new EmptyBorder(5, 10, 5, 50));

		topPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Menu", TitledBorder.LEFT,
				TitledBorder.TOP));

		topPanel.add(top1Panel);
		topPanel.add(top2Panel);

		
		
		// Setting up for the Top1Panel
		top1Panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Choose Game",
				TitledBorder.LEFT, TitledBorder.TOP));

		top1Panel.add(fiaMedKnuffButton);
		top1Panel.add(solitarButton);

		
		
		// Setting up for the top2Panel
		top2Panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Antal spelare",
				TitledBorder.LEFT, TitledBorder.TOP));

		top2Panel.add(player);
		top2Panel.add(player2);
		top2Panel.add(player3);
		top2Panel.add(player4);
		
		player.setSelected(true);
		
		// Setting up for the midPanel
		midPanel.setLayout(new GridLayout(1, 1));
		
		midPanel.add(setBackground);
		
//		midPanel.addMouseListener(new BoardGamesListenersMouse());

		// Setting up for the botPanel
		botPanel.add(quitButton);

		// Setting up for the mainPanel
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(midPanel, BorderLayout.CENTER);
		mainPanel.add(botPanel, BorderLayout.SOUTH);

		// Setting up the frame. (frameR)
		frameR.setContentPane(mainPanel);
		frameR.setBounds(250, 5, 850, 730);
		frameR.setVisible(true);
		frameR.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameR.setResizable(false);
	}

	private void makesButtons() {

		quitButton.addActionListener(new BoardGamesListenersQuit()); 

		fiaMedKnuffButton.addActionListener(new BoardGamesListenersNewFMK());

		solitarButton.addActionListener(new BoardGamesListenersNewSolitar());

	}
}
