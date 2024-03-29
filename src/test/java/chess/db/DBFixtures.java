package chess.db;

public class DBFixtures {

    public static ChessGameDBConnector createChessGameDBConnector() {
        return new ChessGameDBConnector("chess_test");
    }
}
