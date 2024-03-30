package chess.domain;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.state.ChessState;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    public boolean hasTwoKing() {
        return board.values().stream()
                .filter(piece -> piece.isTypeOf(PieceType.kings()))
                .count() == 2;
    }

    public Color getRemainKingColor() {
        if (hasTwoKing()) {
            throw new IllegalStateException("아직 게임이 끝나지 않아 남은 킹의 색상을 확인할 수 없습니다.");
        }
        return board.values().stream()
                .filter(piece -> piece.isTypeOf(PieceType.kings()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("왕이 모두 잡혔습니다."))
                .color( );
    }

    public Map<Position, PieceType> collectBoard() {
        return board.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().pieceType()
                ));
    }
}
