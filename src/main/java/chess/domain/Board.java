package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.ChessState;
import java.util.Map;
import java.util.Set;

public class Board {
    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public ChessState getChessState(Position position) {
        Piece piece = board.get(position);
        return piece.state(board);
    }

    public Piece getPiece(Position position) {
        return board.get(position);
    }

    public boolean isAllBlank(Set<Position> positions) {
        return positions.stream()
                .map(board::get)
                .allMatch(Piece::isBlank);
    }
}
