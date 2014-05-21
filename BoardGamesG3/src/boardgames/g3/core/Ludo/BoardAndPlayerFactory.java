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
	List<LudoPlayer> players;

	public void execute() {
		board = createAndReturnBoard();
		players = createAndReturnLudoPlayers();
		addPlayersPiecesToTheBoard();
		
	}

	public List<LudoPlayer> getPlayers() {
		return players;
	}

	public Board getBoard() {
		return board;
	}

	private List<LudoPlayer> createAndReturnLudoPlayers() {
		List<Player> plainPlayers = createAndReturnPlayers();
		List<LudoPlayer> ludoPlayers = new ArrayList<>();
		for (Player p : plainPlayers) {
			ludoPlayers.add(new LudoPlayer(p, board));
		}
		return ludoPlayers;
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
		for (LudoPlayer p : players) {
			List<GamePiece> pieces = p.getPlayerObject().getPieces();
			List<String> homePositions = p.getHomePositions();

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
