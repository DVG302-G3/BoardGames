package boardgames.g3.core.Ludo;

import game.impl.Board;
import game.impl.BoardLocation;
import game.impl.GamePiece;
import game.impl.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardAndPlayerFactory {

	Board board;
	List<Player> players;

	public void execute() {
		board = createAndReturnBoard();
		players = createAndReturnPlayers();
		addPlayersPiecesToTheBoard();
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Board getBoard() {
		return board;
	}

	private List<Player> createAndReturnPlayers() {

		List<Player> players = new ArrayList<>();
		players.add(createAndReturnPlayer(LudoStaticValues.REDPLAYER,
				LudoStaticValues.REDPIECES));
		players.add(createAndReturnPlayer(LudoStaticValues.BLUEPLAYER,
				LudoStaticValues.BLUEPIECES));
		players.add(createAndReturnPlayer(LudoStaticValues.YELLOWPLAYER,
				LudoStaticValues.YELLOWPIECES));
		players.add(createAndReturnPlayer(LudoStaticValues.GREENPLAYER,
				LudoStaticValues.GREENPIECES));
		return players;

	}

	private Player createAndReturnPlayer(String color,
			List<String> gamePieceNames) {

		List<GamePiece> gamePieces = new ArrayList<GamePiece>();
		gamePieces.add(new GamePiece(gamePieceNames.get(0)));
		gamePieces.add(new GamePiece(gamePieceNames.get(1)));
		gamePieces.add(new GamePiece(gamePieceNames.get(2)));
		gamePieces.add(new GamePiece(gamePieceNames.get(3)));

		return new Player(color, gamePieces);
	}

	private Board createAndReturnBoard() {
		return new Board(createBoardLocations());
	}

	private List<BoardLocation> createBoardLocations() {
		List<BoardLocation> boardLocations = new ArrayList<BoardLocation>();
		try {
			List<String> listOfPositions = FileHandlerFiaMedKnuff
					.readCoordinate();
			for (String s : listOfPositions) {
				boardLocations.add(new BoardLocation(s));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return boardLocations;

	}

	private void addPlayersPiecesToTheBoard() {
		for (Player p : players) {
			List<GamePiece> pieces = p.getPieces();
			List<String> homePositions;
			if (p.getName().equals(LudoStaticValues.GREENPLAYER))
				homePositions = LudoStaticValues.GREENHOME;
			else if (p.getName().equals(LudoStaticValues.BLUEPLAYER))
				homePositions = LudoStaticValues.BLUEHOME;
			else if (p.getName().equals(LudoStaticValues.REDPLAYER))
				homePositions = LudoStaticValues.REDHOME;
			else
				homePositions = LudoStaticValues.YELLOWHOME;

			int listIndex = 0;
			for (GamePiece gp : pieces) {
				int index = HelpMethodsFinaMedKnuff
						.getFlatListIndexFromCoordinate(
								homePositions.get(listIndex++), board);
				board.getLocations().get(index).setPiece(gp);

			}

		}
	}
}
