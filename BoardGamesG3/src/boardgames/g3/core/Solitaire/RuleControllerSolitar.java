package boardgames.g3.core.Solitaire;

import game.api.GameState;
import game.impl.Board;
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

	private boolean thereIsNotABeadInBetween(int destRow, int destCol,
			int sourceRow, int sourceCol, Board board) {
		int deltaRow = (sourceRow - destRow);
		int deltaCol = (sourceCol - destCol);
		int middlePieceRow = destRow + deltaRow / 2;
		int middlePieceCol = destCol + deltaCol / 2;

		String middleLoc = Integer.toString(middlePieceRow)
				+ Integer.toString(middlePieceCol);

		BoardLocation middle = SolitarHelpMethods
				.getBoardLocationFromCoordinate(middleLoc, board);

		return middle.getPiece() == null;
	}

	public Boolean isValidMove(Move move, Board board) {
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

			if (thereIsNotABeadInBetween(destRow, destCol, sourceRow,
					sourceCol, board))
				return false;

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
		movePossible = iterateBoardForMoves(tmpBoard);
		return !movePossible;
	}

	private boolean iterateBoardForMoves(BoardLocation[][] tmpBoard) {
		boolean movePossible = false;
		for (int r = 0; r < ROWS && !movePossible; r++) {
			for (int c = 0; c < COLS && !movePossible; c++) {
				GamePiece currentPiece = tmpBoard[r][c].getPiece();
				if (currentPiece != null) {
					if (withinBoundsRows(r)) {
						GamePiece pieceBelow = tmpBoard[r + 1][c].getPiece();
						GamePiece twoBelow = tmpBoard[r + 2][c].getPiece();
						movePossible = movePossible(pieceBelow, twoBelow);
						if (movePossible) {
							return true;
						}

					}

					if (withinBoundsCols(c)) {
						GamePiece rightPiece = tmpBoard[r][c + 1].getPiece();
						GamePiece secondRightPiece = tmpBoard[r][c + 2].getPiece();
						movePossible = movePossible(rightPiece,secondRightPiece);
						if (movePossible)
							return true;
					}
				}
			}
		}
		return movePossible;
	}

	private boolean withinBoundsRows(int r) {
		return r < (ROWS - 2);
	}

	private boolean withinBoundsCols(int c) {
		return c < (COLS - 2);
	}

	private boolean movePossible(GamePiece firstPiece, GamePiece secondPiece) {
		return firstPiece != null && secondPiece == null;
	}
}
