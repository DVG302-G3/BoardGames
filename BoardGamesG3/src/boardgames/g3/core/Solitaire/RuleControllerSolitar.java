package boardgames.g3.core.Solitaire;

import game.api.GameState;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Move;

public class RuleControllerSolitar {

	public final int ROWS;
	public final int COLS;

	public RuleControllerSolitar(int rows, int cols) {
		this.ROWS = rows;
		this.COLS = cols;
	}

	public Boolean isValidMove(Move move) {
		if (move.getSource().getPiece() == null) {
			return false;
		} else {

			int sourceRow, sourceCol, destRow, destCol;
			BoardLocation destination = move.getDestination();
			BoardLocation source = move.getSource();

			if (destination.getPiece() != null) {
				return false;
			}

			sourceRow = Integer.parseInt(source.getId().substring(0, 1));
			sourceCol = Integer.parseInt(source.getId().substring(1, 2));

			destRow = Integer.parseInt(destination.getId().substring(0, 1));
			destCol = Integer.parseInt(destination.getId().substring(1, 2));

			int deltaRow = Math.abs((sourceRow - destRow));
			int deltaCol = Math.abs((sourceCol - destCol));
			if (deltaRow == 2 && deltaCol == 0)
				return true;
			else if (deltaCol == 2 && deltaRow == 0)
				return true;

			else
				return false;
		}

	}

	public Boolean isGameFinished(GameState gamestate) {
		boolean movePossible = false;
		BoardLocation[][] tmpBoard = SolitarHelpMethods.get2DBoard(ROWS, COLS,
				gamestate.getBoard().getLocations());
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				GamePiece currentPiece = tmpBoard[r][c].getPiece();
				if (currentPiece != null) {
					if (c < (COLS - 2)) {
						GamePiece rightPiece = tmpBoard[r][c + 1].getPiece();
						GamePiece secondRightPiece = tmpBoard[r][c + 2]
								.getPiece();
						if (rightPiece != null && secondRightPiece == null) {
							movePossible = true;
							break;
						}
					}

					else {
						if (r < (ROWS - 2)) {
							GamePiece pieceBelow = tmpBoard[r + 1][c]
									.getPiece();
							GamePiece twoBelow = tmpBoard[r + 2][c].getPiece();
							if (pieceBelow != null && twoBelow == null) {
								movePossible = true;
								break;
							}
						}
					}

				}
			}
		}

		return !movePossible;

	}
}
