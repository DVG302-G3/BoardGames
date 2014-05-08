package boardgames.g3.BGForLabelsButtons;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BackGroundLabelLudo extends JLabel {

	private Image img;

	private int ROWS;
	private int COLS;

	public BackGroundLabelLudo(int rows, int cols) {
		this("src\\boardgames\\img\\ludoBackground.png");
		this.ROWS = rows;
		this.COLS = cols;
		setLayout(new GridLayout(ROWS, COLS));
	}

	public BackGroundLabelLudo(String img) {
		this(new ImageIcon(img).getImage());
	}

	public BackGroundLabelLudo(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}