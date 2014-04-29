package boardgames.g3.core.Solitaire;

import game.api.GameState;
import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Move;

public class SolitarRuleController {

	public final int ROWS;
	public final int COLS;

	public SolitarRuleController(int rows, int cols) {
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
		BoardLocation[][] tmpBoard = SolitarHelpMethods.get2DBoard(ROWS, COLS,
				gamestate.getBoard().getLocations());
		return !iterateBoardForMoves(tmpBoard);
	}

	private boolean iterateBoardForMoves(BoardLocation[][] tmpBoard) {
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				if (tmpBoard[r][c] == null)
					continue;

				GamePiece currentPiece = tmpBoard[r][c].getPiece();
				if (currentPiece != null) {

					if (withinBoundsBeleow(r)) {
						if (boardPieceExistBelow(tmpBoard, r, c)) {
							if(movePossibleToDoDownwards(tmpBoard, r, c))
								return true;
						}
					}

					if (withinBoundsRight(c)) {
						if (boardPieceExistRight(tmpBoard, r, c)) {
							if(movePossibleToDoRight(tmpBoard, r, c))
								return true;
						}
					}
					if (withinBoundsAbove(r)) {
						if (boardPieceExistAbove(tmpBoard, r, c)) {
							if(movePossibleToDoUpwards(tmpBoard, r,c))
								return true;
						}
					}

					if (withinBoundsLeft(c)) {
						if (boardPieceExistLeft(tmpBoard, r, c)) {
							if(movePossibleToDoLeft(tmpBoard, r, c))
								return true;
						}
					}

				}

			}
		}
		return false;
	}

	private boolean movePossibleToDoLeft(BoardLocation[][] tmpBoard, int r,
			int c) {
		GamePiece leftPiece = tmpBoard[r][(c - 1)].getPiece();
		GamePiece secondLeftPiece = tmpBoard[r][(c - 2)].getPiece();
		return movePossibleAboveAndLeft(leftPiece,secondLeftPiece);
	}

	private boolean movePossibleToDoUpwards(BoardLocation[][] tmpBoard, int r,
			int c) {
		GamePiece pieceAbove = tmpBoard[(r - 1)][c].getPiece();
		GamePiece twoAbove = tmpBoard[(r - 2)][c].getPiece();
		return movePossibleAboveAndLeft(pieceAbove,twoAbove);
	}

	private boolean movePossibleToDoRight(BoardLocation[][] tmpBoard, int r,
			int c) {
		GamePiece rightPiece = tmpBoard[r][(c + 1)].getPiece();
		GamePiece secondRightPiece = tmpBoard[r][(c + 2)].getPiece();
		return movePossibleBelowAndRight(rightPiece, secondRightPiece);
	}

	private boolean movePossibleToDoDownwards(BoardLocation[][] tmpBoard,
			int r, int c) {
		GamePiece pieceBelow = tmpBoard[(r + 1)][c].getPiece();
		GamePiece twoBelow = tmpBoard[(r + 2)][c].getPiece();
		return movePossibleBelowAndRight(pieceBelow, twoBelow);
	}

	private boolean boardPieceExistBelow(BoardLocation[][] tmpBoard, int r,
			int c) {
		return (tmpBoard[r][c] != null && tmpBoard[(r + 1)][c] != null && tmpBoard[(r + 2)][c] != null);
	}

	private boolean boardPieceExistRight(BoardLocation[][] tmpBoard, int r,
			int c) {
		return (tmpBoard[r][c] != null && tmpBoard[r][(c + 1)] != null && tmpBoard[r][(c + 2)] != null);
	}

	private boolean boardPieceExistAbove(BoardLocation[][] tmpBoard, int r,
			int c) {
		return (tmpBoard[r][c] != null && tmpBoard[(r - 1)][c] != null && tmpBoard[(r - 2)][c] != null);
	} 

	private boolean boardPieceExistLeft(BoardLocation[][] tmpBoard, int r, int c) {
		return (tmpBoard[r][c] != null && tmpBoard[r][(c - 1)] != null && tmpBoard[r][(c - 2)] != null);
	}

	private boolean withinBoundsBeleow(int r) {
		return r < (ROWS - 2);
	}

	private boolean withinBoundsRight(int c) {
		return c < (COLS - 2);
	}

	private boolean withinBoundsAbove(int r) {
		return (r - 2) > 0;
	}

	private boolean withinBoundsLeft(int c) {
		return (c - 2) > 0;
	}

	private boolean movePossibleBelowAndRight(GamePiece firstPiece,
			GamePiece secondPiece) {
		return firstPiece != null && secondPiece == null;
	}

	private boolean movePossibleAboveAndLeft(GamePiece firstPiece,
			GamePiece secondPiece) {
		return firstPiece != null && secondPiece == null;
	}
}
