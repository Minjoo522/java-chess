package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.ChessState;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public ChessState getChessState(Position position) {
        Piece piece = board.get(position);
        return piece.state(board);
    }
}
