package boardgames.g3.BGForLabelsButtons;

import game.api.GameState;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooserSaveAndOpen extends JPanel implements ActionListener {

	JButton openButton, saveButton;
	JFileChooser fc;

	GameState gameState;

	public FileChooserSaveAndOpen(GameState state) {
		super(new GridLayout(2, 0));

		this.gameState = state;

		fc = new JFileChooser();

		openButton = new JButton("Load Gamestate..");
		openButton.addActionListener(this);

		saveButton = new JButton("Save Gametate..");
		saveButton.addActionListener(this);

		add(openButton);
		add(saveButton);

	}

	public void actionPerformed(ActionEvent e) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt",
				"txt", "text");

		// Handle open button action.
		if (e.getSource() == openButton) {
			int returnVal = fc.showOpenDialog(FileChooserSaveAndOpen.this);
			fc.setFileFilter(filter);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					File selectedFile = fc.getSelectedFile();
					BufferedReader br = new BufferedReader(new FileReader(
							selectedFile));

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}

			// Handle save button action.
		} else if (e.getSource() == saveButton) {
			int returnVal = fc.showSaveDialog(FileChooserSaveAndOpen.this);
			fc.setFileFilter(filter);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					File fileCreated = fc.getSelectedFile();
					PrintWriter printWriter = new PrintWriter(fileCreated);

					printWriter.print("");

					printWriter.close();

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
