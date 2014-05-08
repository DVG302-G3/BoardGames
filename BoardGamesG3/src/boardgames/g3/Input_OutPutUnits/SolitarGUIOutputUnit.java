package boardgames.g3.Input_OutPutUnits;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.io.OutputUnit;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import boardgames.g3.BGForLabelsButtons.BackGroundButtonIDLudo;
import boardgames.g3.BGForLabelsButtons.BackGroundButtonIDSolitaire;
import boardgames.g3.BGForLabelsButtons.BackGroundLabelSolitaire;
import boardgames.g3.BGForLabelsButtons.FileChooserSaveAndOpen;
import boardgames.g3.core.Solitaire.SolitarCounterBeads;
import boardgames.g3.core.Solitaire.SolitarJOptionFinish;
import boardgames.g3.core.Solitaire.SolitarStaticValue;
import boardgames.g3.core.Solitaire.SolitarTimer;

public class SolitarGUIOutputUnit extends JPanel implements OutputUnit {

	BackGroundLabelSolitaire backgroundLabel;

	SolitarTimer timer;
	SolitarCounterBeads counterBeads;
	FileChooserSaveAndOpen fileChooser;

	private JTextArea textAreaBeadsLeftAndTaken;
	private SolitarJOptionFinish optionLabel;
	private ActionListener inputUnit;
	private JPanel topPanel, topPanelFileChooser, topPanelBeadsLeftAndTaken,
			topPanelTimer, mainPanel;
	
	private BackGroundButtonIDSolitaire button[][];

	public SolitarGUIOutputUnit(SolitarGUIInputUnit inputUnit) {
		this.inputUnit = inputUnit;
		createComponent();
		settingUpComponents();
	}

	private void createComponent() {
		backgroundLabel = new BackGroundLabelSolitaire(SolitarStaticValue.ROW,
				SolitarStaticValue.COL);

		timer = new SolitarTimer();
		counterBeads = new SolitarCounterBeads(SolitarStaticValue.BEADS_TOTAL);
		optionLabel = new SolitarJOptionFinish();

		textAreaBeadsLeftAndTaken = new JTextArea();

		topPanel = new JPanel(new GridLayout(0, 3));
		topPanelFileChooser = new JPanel();
		topPanelBeadsLeftAndTaken = new JPanel();
		topPanelTimer = new JPanel();

		mainPanel = new JPanel();

	}

	private void settingUpComponents() {

		topPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Solitär",
				TitledBorder.LEFT, TitledBorder.TOP));

		topPanelFileChooser.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Load & Save",
				TitledBorder.LEFT, TitledBorder.TOP));

		
		topPanelBeadsLeftAndTaken.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Beads Taken",
				TitledBorder.LEFT, TitledBorder.TOP));
		textAreaBeadsLeftAndTaken.setOpaque(false);
		textAreaBeadsLeftAndTaken.setFocusable(false);
		textAreaBeadsLeftAndTaken.setText("Beads left:\t"
				+ counterBeads.getBeadsLeft() + "\nBeads Taken:\t"
				+ counterBeads.getBeadsTaken());

		topPanelBeadsLeftAndTaken.add(textAreaBeadsLeftAndTaken);

		topPanelTimer.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Timer", TitledBorder.LEFT,
				TitledBorder.TOP));
		topPanelTimer.add(timer);

		topPanel.add(topPanelFileChooser);
		topPanel.add(topPanelBeadsLeftAndTaken);
		topPanel.add(topPanelTimer);

		mainPanel.add(backgroundLabel);

		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);

	}

	@Override
	public void publish(GameState gameState) {

		backgroundLabel.removeAll();

		List<BoardLocation> locations = gameState.getBoard().getLocations();

		fileChooser = new FileChooserSaveAndOpen(gameState);
		button = new BackGroundButtonIDSolitaire[SolitarStaticValue.ROW][SolitarStaticValue.COL];
		counterBeads = new SolitarCounterBeads(SolitarStaticValue.BEADS_TOTAL);

		int index = 0;
		for (int rows = 0; rows < SolitarStaticValue.ROW; rows++) {
			for (int cols = 0; cols < SolitarStaticValue.COL; cols++) {
				button[rows][cols] = new BackGroundButtonIDSolitaire(
						Integer.toString((rows + 1))
								+ Integer.toString((cols + 1)), locations, counterBeads, index);

				button[rows][cols].addActionListener(inputUnit);

				backgroundLabel.add(button[rows][cols]);

				textAreaBeadsLeftAndTaken.setText("Beads left:\t"
						+ counterBeads.getBeadsLeft() + "\nBeads Taken:\t"
						+ counterBeads.getBeadsTaken());
				topPanelFileChooser.removeAll();
				topPanelFileChooser.add(fileChooser);
				topPanelFileChooser.revalidate();
				
        index++;
			}
		}

		if (gameState.hasEnded()) {
			timer.StopTimeAndTask();

			optionLabel.dataTaker(timer.getCountTimeMin(),
					timer.getCountTimeSec(), counterBeads.getBeadsTaken(),
					counterBeads.getBeadsLeft());

			optionLabel.displayGUI();

			// if (optionLabel.getReturnValue() == 0) {
			// new BoardGamesCoreGUI();
			// }
			if (optionLabel.getReturnValue() == 1) {
				gameState.reset();
				publish(gameState);
				timer = new SolitarTimer();
				topPanelTimer.removeAll();
				topPanelTimer.add(timer);
				topPanelTimer.revalidate();
			}
		}

		backgroundLabel.revalidate();
	}
}
