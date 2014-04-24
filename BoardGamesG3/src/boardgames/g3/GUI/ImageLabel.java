package boardgames.g3.GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel extends JLabel {

	private Image img;
	
	ImageIcon solitaire, fmk, settings, mainmenu;
	
	private int ROWS;
	private int COLS;

	public ImageLabel(String img) {
		this(new ImageIcon(img).getImage());
	}
	

	public ImageLabel(int rows, int cols) {
		this.ROWS = rows;
		this.COLS = cols;
		setLayout(new GridLayout(ROWS, COLS));
	}

	public ImageLabel(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}


	public void setMainMenuBackground() {
		new ImageLabel(
				new ImageIcon("src\\boardgames\\img\\menubackground.png")
						.getImage());
	}

	public ImageIcon setSolitarBackground() {
	 return	new ImageIcon(
				"src\\boardgames\\img\\backgroundSolitaire.png");
	}

	public void setLUDOBackground() {
		new ImageLabel(new ImageIcon(
				"src\\boardgames\\img\\menubackgroundFMK.png").getImage());
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}
